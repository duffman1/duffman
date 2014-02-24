package com.nbcuni.test.publisher.common;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.reporters.EmailableReporter;
import org.testng.xml.XmlSuite;

public class CustomReport extends EmailableReporter {

	@Override
	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String arg2) {
		
		Config config = new Config();
		
		super.generateReport(arg0, arg1, arg2);

		File resultsFile = new File(arg2 + File.separator + "emailable-report.html");
    
		String storeReportsTo = null;
		try {
			storeReportsTo = config.getPathToReports();
		} catch (Exception e) { System.out.println("Failed to get configuration path to reports directory."); }
		
		System.out.println(storeReportsTo);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String fileExtension = dateFormat.format(date).replace("/", "");
		fileExtension = fileExtension.replace(" ", "");
		fileExtension = fileExtension.replace(":", "") + ".html";
		String filePath = storeReportsTo + fileExtension;

		try {
			FileUtils.copyFile(resultsFile, new File(filePath));
		} catch (IOException e) { System.out.println("Failed to copy emailable-report.html to reports directory."); }
    
    }
}