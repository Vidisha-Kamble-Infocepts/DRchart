/*package com.ge.predix.service.csvreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import com.univocity.parsers.common.processor.ColumnProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.common.processor.ObjectColumnProcessor;
import com.univocity.parsers.common.*;
import com.univocity.parsers.conversions.*;

import java.util.*;

public class UnivocityParserDemo {
	public static void main(String args[]){
		//Reading all the column values from a csv file
		File file=new File("src/main/resources/sampleSite.csv");
		CsvParserSettings parserSettings=new CsvParserSettings();
		parserSettings.getFormat().setLineSeparator("\n");
		parserSettings.setHeaderExtractionEnabled(true);

		ColumnProcessor rowProcessor=new ColumnProcessor();
		parserSettings.setRowProcessor(rowProcessor);

		CsvParser csvParser=new CsvParser(parserSettings);
		csvParser.parse(file);

		Map<String,List<String>> columnValues=rowProcessor.getColumnValuesAsMapOfNames();
		System.out.println(columnValues);


		File file=new File("src/main/resources/sampleSite.csv");
		FixedWidthParserSettings parserSettings=new FixedWidthParserSettings();
	    FixedWidthParser parser = new FixedWidthParser(parserSettings);
	    parser.parse(file);
		parserSettings.setRowProcessor(new BatchedColumnProcessor(3) {

	        @Override
	        public void batchProcessed(int rowsInThisBatch) {
	            List<List<String>> columnValues = getColumnValuesAsList();

	            System.out.println("Batch " + getBatchesProcessed() + ":");
	            int i = 0;
	            for (List<String> column : columnValues) {
	            	System.out.println("Column " + (i++) + ":" + column);
	            }
	        }
	    });

		File file=new File("src/main/resources/sampleSite.csv");
		CsvParserSettings parserSettings=new CsvParserSettings();

		ColumnProcessor rowProcessor=new ColumnProcessor();
		parserSettings.setRowProcessor(rowProcessor);

		CsvParser csvParser=new CsvParser(parserSettings);
		csvParser.parse(file);

		List<List<String>> parsedRows=rowProcessor.getColumnValuesAsList();
		System.out.println(parsedRows);

		File file=new File("src/main/resources/sampleSite.csv");
		RowListProcessor rowProcessor = new RowListProcessor();
	    CsvParserSettings parserSettings = new CsvParserSettings();

	    parserSettings.setRowProcessor(rowProcessor);
	    parserSettings.setHeaderExtractionEnabled(true);
	    parserSettings.getFormat().setLineSeparator("\n");
	    //parserSettings.setNumberOfRowsToSkip(1);

	    // Here we select only the columns "SiteElectricalConsumption", "SiteElectricalDemand"
	    // The parser just skips the other fields
	    parserSettings.selectFields("SiteElectricalConsumption", "SiteElectricalDemand");

	    CsvParser parser = new CsvParser(parserSettings);
	    parser.parse(file);

	    List<String[]> rows= rowProcessor.getRows();

	    for(int i=0;i<rows.size();i++){
	    String[] strings = rows.get(i);

	    System.out.println(strings[i]);

		File file=new File("src/main/resources/sampleSite.csv");
		CsvParserSettings parserSettings=new CsvParserSettings();

		ObjectColumnProcessor processor=new  ObjectColumnProcessor();
		parserSettings.setRowProcessor(processor);

		CsvParser parser = new CsvParser(parserSettings);
	    parser.parse(file);

		List<Object> list=processor.getColumn(0);
		System.out.println(list);

		CsvParserSettings settings = new CsvParserSettings();
		settings.selectFields("DateTime","SiteElectricalConsumption");
		// or if your file does not have a row with column headers, you can use indexes

		//settings.selectIndexes(0, 1, 2);
		CsvParser parser = new CsvParser(settings);
		String file="D:/ReadCSV/sampleSite.csv";
		try {
			List<String[]> allRows = parser.parseAll(new FileReader(file));
			for(int i=0;i<allRows.size();i++){
				String[] atrArr=allRows.get(0);
				System.out.println(atrArr);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }

}

 */