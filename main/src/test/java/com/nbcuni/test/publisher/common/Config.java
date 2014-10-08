package com.nbcuni.test.publisher.common;

import org.testng.Assert;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Config {

	private String getConfigFileLocation() {
		
		String fileLoc = System.getProperty("user.dir") + "/src/test/resources/TestNGSuiteConfig.xml";
		return fileLoc.replace("/", File.separator);
	}
	
	private String getConcurrentSuiteFileLocation() {
		
		String fileLoc = System.getProperty("user.dir") + "/src/test/resources/TestNGConcurrentSuite.xml";
		return fileLoc.replace("/", File.separator);
	}
	
	private String getConsecutiveSuiteFileLocation() {
		String fileLoc = System.getProperty("user.dir") + "/src/test/resources/TestNGConsecutiveSuite.xml";
		return fileLoc.replace("/", File.separator);
	}
	
	private String getXPathValueFromFile(String fileLocation, String xpathQuery) {
		
		String value = null;

        try {

        	File file = new File(fileLocation);
            DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = xmlFactory.newDocumentBuilder();
            Document xmlDoc = docBuilder.parse(file);
            XPathFactory xpathFact = XPathFactory.newInstance();
            XPath xpath = xpathFact.newXPath();

            value = (String) xpath.evaluate(xpathQuery, xmlDoc, XPathConstants.STRING);
        }
        catch (Exception e) {

            Assert.fail("Failed to get value from file");
        }

        return value;
	}
	
    public String getConfigValue(String parameterName) {

    	return this.getXPathValueFromFile(this.getConfigFileLocation(), "//parameter[@name='" + parameterName + "']/@value");
     
    }
    
    public String getPathToReports() {
    	String fileLoc = System.getProperty("user.dir") + this.getConfigValue("PathToReports");
    	return fileLoc.replace("/", File.separator);
    }
    
    public String getPathToHarReports() {
    	
    	String fileLoc = System.getProperty("user.dir") + this.getConfigValue("PathToHarReports");
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
    
    public int getMPXVideaUploadPause() {
    	
    	return Integer.parseInt(this.getConfigValue("MPXVideaUploadPause"));
    	
    }
    
    public int getMPXAssetBufferPause() {
    	
    	return Integer.parseInt(this.getConfigValue("MPXAssetBufferPause"));
    	
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

    	String value = this.getXPathValueFromFile(this.getConcurrentSuiteFileLocation(), "//suite[@name='Pub 7 Concurrent Suite']/@thread-count");
        return Integer.parseInt(value);

    }
    
    public String getIncludedGroups() {

        return this.getXPathValueFromFile(this.getConcurrentSuiteFileLocation(), "//groups/run/include/@name");

    }
    
    public String getExcludedGroups() {

        return this.getXPathValueFromFile(this.getConcurrentSuiteFileLocation(), "//groups/run/exclude/@name");

    }

    public List<String> getAllLocalTests() {
    	//TODO - clean this up, some redundant code.
    	List<String> allLocalTests = new ArrayList<String>();
    	try {
    		DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder docBuilder = xmlFactory.newDocumentBuilder();
    	
    		XPathFactory xpathFact = XPathFactory.newInstance();
    		XPath xpath = xpathFact.newXPath();
    	
    		File consecutiveFile = new File(this.getConsecutiveSuiteFileLocation());
    		File concurrentFile = new File(this.getConcurrentSuiteFileLocation());
    	
    		Document consecutiveXmlDoc = docBuilder.parse(consecutiveFile);
    		NodeList consecutiveNodeList = (NodeList) xpath.evaluate("//class[@runslocal='true']/@name", consecutiveXmlDoc, XPathConstants.NODESET);
    	
    		int length = consecutiveNodeList.getLength();
    		for( int i=0; i<length; i++) {
    			Attr attr = (Attr) consecutiveNodeList.item(i);
    			allLocalTests.add(attr.toString().replace("name=", "").replace("\"", ""));
    		}
    	
    		Document concurrentXmlDoc = docBuilder.parse(concurrentFile);
    		NodeList concurrentNodeList = (NodeList) xpath.evaluate("//class[@runslocal='true']/@name", concurrentXmlDoc, XPathConstants.NODESET);
    	
    		int length2 = concurrentNodeList.getLength();
    		for( int i=0; i<length2; i++) {
    			Attr attr = (Attr) concurrentNodeList.item(i);
    			allLocalTests.add(attr.toString().replace("name=", "").replace("\"", ""));
    		}
    	}
    	catch (Exception e) {
    		Assert.fail("Failed to get value from test config");
    	}
    		return allLocalTests;
    	}
}