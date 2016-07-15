/*package com.ge.predix.service.csvreader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

 * Uses Java 7 NIO to read all the lines from the csv file
 * and returns row by row data

public class ReadCsv_NIO {
	public static void main(String[] args) throws IOException{
		File file=new File("src/main/resources/sampleSite.csv");
		List<String> lines=Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
		for(String line:lines){
			String[] strArr=line.split(",");
			System.out.println(" "+ strArr[0] +" "+ strArr[1] +" "+ strArr[2]);
		}
	}
}
 */