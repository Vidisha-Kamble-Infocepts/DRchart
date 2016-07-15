package com.ge.predix.service.csvreader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVReader;


public class ReadCsv_Split {
	//public static void main(String[] args) {
	String csvFile="D:/ReadCSV/samplesitelevel.csv";
	List<HashMap<String, List<String>>> arrList=readCSVFile(csvFile);
	//System.out.println(arrList);
	//static void writeCsvFile(arrList,"D:/writeToCsvFile.csv");
	//}
	public static List<HashMap<String, List<String>>> readCSVFile(String path){
		String strArr=null;
		String[] nextHeader=null;
		String[] nextHeaders=null;
		int indx=0;
		String[] nxtline=null;
		int lineNumber=0;
		final HashMap<String,List<String>> colNameVsValues=new HashMap<>();
		List<HashMap<String,List<String>>> allCols=new ArrayList<>();

		try {
			final CSVReader reader=new CSVReader(new FileReader(path));
			final String[] header=reader.readNext();
			final String[] line=reader.readNext();
			nextHeader=reader.readNext();
			nextHeaders=reader.readNext();
			while((nxtline=reader.readNext())!=null)
			{
				for(final String str:nextHeaders)
				{

					indx= Arrays.asList(nextHeaders).indexOf(str);
					lineNumber++;
					strArr=nxtline[indx];
					if(str.equals("")){
						if(allCols.size()==0 && !colNameVsValues.containsKey("Timestamp")){
							final List<String> list = new ArrayList<>();
							list.add(strArr);
							colNameVsValues.put("Timestamp", list);
							allCols.add(colNameVsValues);
						}
						else{
							colNameVsValues.get("Timestamp").add(strArr);
						}
					}else{

						if(allCols.size()>0 && !colNameVsValues.containsKey(str)){
							allCols=new ArrayList<>();
							final List<String> list = new ArrayList<>();
							list.add(strArr);
							colNameVsValues.put(str, list);
							allCols.add(colNameVsValues);
						}
						else{
							colNameVsValues.get(str).add(strArr);
						}
					}
				}
			}
		}catch(final Exception e){
			e.printStackTrace();
		}
		return allCols;
	}

	public static void writeCsvFile(List<HashMap<String, List<String>>> arrList,String fileName) {
		final String DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		final String FILE_HEADER = "Timestamp";
		//  	Site siteObj = new Site("","131734","");
		//  	List<Site> siteList = new ArrayList<>();
		//  	siteList.add(siteObj);
		final SampleBldg sampleBldg=new SampleBldg();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			for (int i=0;i<arrList.size();i++) {
				//				fileWriter.append(sampleBldg.getDateTime());
				//				fileWriter.append(DELIMITER);
				final HashMap<String, List<String>> h=arrList.get(0);
				final List<String> timestampValues=h.get("Timestamp");
				fileWriter.append(timestampValues.get(0));
				fileWriter.append(DELIMITER);
				//				fileWriter.append(sampleBldg.getSiteElectricalDemand());
				//				fileWriter.append(DELIMITER);
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			System.out.println("CSV file was created successfully !!!");
		} catch (final Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (final IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}
}

