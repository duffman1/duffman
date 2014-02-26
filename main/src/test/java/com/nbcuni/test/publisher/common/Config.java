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
		
		String fileLoc = System.getProperty("user.dir") + "/src/test/resources/testng.xml";
		return fileLoc.replace("/", File.separator);
	}
	
    public String getConfigValue(String configValue) {

        String value = null;

        try {

        	File file = new File(this.getConfigFileLocation());
            DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = xmlFactory.newDocumentBuilder();
            Document xmlDoc = docBuilder.parse(file);
            XPathFactory xpathFact = XPathFactory.newInstance();
            XPath xpath = xpathFact.newXPath();

            value = (String) xpath.evaluate("//parameter[@name='" + configValue + "']/@value", xmlDoc, XPathConstants.STRING);
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
    
    public int getReRunOnFailureCount() {
    	
    	return Integer.parseInt(this.getConfigValue("ReRunOnFailureCount"));
    	
    }


}