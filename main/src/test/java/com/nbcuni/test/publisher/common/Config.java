package com.nbcuni.test.publisher.common;

import org.testng.Assert;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import java.io.File;

public class Config {

	private String getConfigFileLocation() {
		
		String fileLoc = System.getProperty("user.dir") + "/src/test/resources/ConfigTestNG.xml";
		return fileLoc.replace("/", File.separator);
	}
	
    public String getConfigValue(String parameterName) {

        String value = null;

        try {

        	File file = new File(this.getConfigFileLocation());
            DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = xmlFactory.newDocumentBuilder();
            Document xmlDoc = docBuilder.parse(file);
            XPathFactory xpathFact = XPathFactory.newInstance();
            XPath xpath = xpathFact.newXPath();

            value = (String) xpath.evaluate("//parameter[@name='" + parameterName + "']/@value", xmlDoc, XPathConstants.STRING);
        }
        catch (Exception e) {

            Assert.fail("Failed to get value from test config");
        }

        return value;

    }
    
    public String getPathToReports() {
    	String fileLoc = System.getProperty("user.dir") + this.getConfigValue("PathToReports");
    	return fileLoc.replace("/", File.separator);
    }
    
    public String getPathToScreenshots() {
    	String fileLoc = System.getProperty("user.dir") + this.getConfigValue("PathToScreenshots");
    	return fileLoc.replace("/", File.separator);
    }
    
    public String getPathToSikuliImages() {
    	String fileLoc = System.getProperty("user.dir") + this.getConfigValue("PathToSikuliImages");
    	return fileLoc.replace("/", File.separator);
    }
    
    public int getReRunOnFailureCount() {
    	
    	return Integer.parseInt(this.getConfigValue("ReRunOnFailureCount"));
    	
    }
    
    public double getSikuliImageWaitTime() {
    	
    	return (double) Integer.parseInt(this.getConfigValue("SikuliImageWaitTime"));
    	
    }
    
    public int getImplicitWaitTime() {
    	
    	return Integer.parseInt(this.getConfigValue("ImplicitWaitTime"));
    	
    }
    
    public int getPageLoadWaitTime() {
    	
    	return Integer.parseInt(this.getConfigValue("PageLoadWaitTime"));
    	
    }
    
    public String getPathToMedia() {
    	
    	String filePath = System.getProperty("user.dir") + this.getConfigValue("PathToMediaContent");
        return filePath.replace("/", File.separator);
    }
    
    public boolean IsErrorCheckingEnabled() {
    	
    	boolean checksEnabled = true;
    	if (this.getConfigValue("ErrorCheckingEnabled").equals("true")) {
    		checksEnabled = true;
    	}
    	else {
    		checksEnabled = false;
    	}
    	
    	return checksEnabled;
    }
    
    public int getParallelCount() {

        String value = null;

        try {

        	File file = new File(this.getConfigFileLocation());
            DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = xmlFactory.newDocumentBuilder();
            Document xmlDoc = docBuilder.parse(file);
            XPathFactory xpathFact = XPathFactory.newInstance();
            XPath xpath = xpathFact.newXPath();

            value = (String) xpath.evaluate("//suite[@name='Test Suite for Publisher 7']/@thread-count", xmlDoc, XPathConstants.STRING);
        }
        catch (Exception e) {

            Assert.fail("Failed to get parallel thread count from test config");
        }

        return Integer.parseInt(value);

    }


}