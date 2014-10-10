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
	
	private List<String> getXPathValuesFromFile(String fileLocation, String xpathQuery) {
		
		List<String> allValues = new ArrayList<String>();
    	try {
    		DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder docBuilder = xmlFactory.newDocumentBuilder();
    	
    		XPathFactory xpathFact = XPathFactory.newInstance();
    		XPath xpath = xpathFact.newXPath();
    	
    		File configFile = new File(fileLocation);
    		
    		Document configXmlDoc = docBuilder.parse(configFile);
    		NodeList nodeList = (NodeList) xpath.evaluate(xpathQuery, configXmlDoc, XPathConstants.NODESET);
    	
    		int length = nodeList.getLength();
    		for( int i=0; i<length; i++) {
    			Attr attr = (Attr) nodeList.item(i);
    			allValues.add(attr.toString());
    		}
    	}
    	catch (Exception e) {
    		Assert.fail("Failed to get value from test config");
    	}
    		return allValues;
    }
	
	public String getConfigValueString(String parameterName) {

		String parameterValue = "";
    	if (System.getProperty("system.test." + parameterName.toLowerCase()) != null) {
    		parameterValue = System.getProperty("system.test." + parameterName.toLowerCase());
    	}
    	else {
    		parameterValue = this.getXPathValueFromFile(this.getConfigFileLocation(), "//parameter[@name='" + parameterName + "']/@value");
    	}
        
    	return parameterValue;
    }
	
	public String getConfigValueFilePath(String parameterName) {

		String parameterValue = "";
    	if (System.getProperty("system.test." + parameterName.toLowerCase()) != null) {
    		parameterValue = System.getProperty("system.test." + parameterName.toLowerCase());
    	}
    	else {
    		parameterValue = this.getXPathValueFromFile(this.getConfigFileLocation(), "//parameter[@name='" + parameterName + "']/@value");
    	}
        
    	parameterValue = System.getProperty("user.dir") + parameterValue;
    	return parameterValue.replace("/", File.separator);
    }
	
	public Integer getConfigValueInt(String parameterName) {

		String parameterValue = "";
    	if (System.getProperty("system.test." + parameterName.toLowerCase()) != null) {
    		parameterValue = System.getProperty("system.test." + parameterName.toLowerCase());
    	}
    	else {
    		parameterValue = this.getXPathValueFromFile(this.getConfigFileLocation(), "//parameter[@name='" + parameterName + "']/@value");
    	}
        
    	return Integer.parseInt(parameterValue);
    }
	
	public boolean IsErrorCheckingEnabled() {
    	
    	boolean checksEnabled = true;
    	if (this.getConfigValueString("ErrorCheckingEnabled").equals("true")) {
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
    
    public List<String> getIncludedGroups() {

        List<String> allIncludedGroups = this.getXPathValuesFromFile(this.getConfigFileLocation(), "//groups/run/include/@name");
    	List<String> allIncudedGroupsCleaned = new ArrayList<String>();
    	
    	for (String group: allIncludedGroups) {
    		allIncudedGroupsCleaned.add(group.replace("name=", "").replace("\"", ""));
    	}
    	
    	return allIncudedGroupsCleaned;
    }
    
    public List<String> getExcludedGroups() {

        List<String> allExcludedGroups = this.getXPathValuesFromFile(this.getConfigFileLocation(), "//groups/run/exclude/@name");
    	List<String> allExcludedGroupsCleaned = new ArrayList<String>();
    	
    	for (String group: allExcludedGroups) {
    		allExcludedGroupsCleaned.add(group.replace("name=", "").replace("\"", ""));
    	}
    	
    	return allExcludedGroupsCleaned;
    }
    
    public List<String> getAllLocalTests() {
    	
    	List<String> allLocalTests = this.getXPathValuesFromFile(this.getConfigFileLocation(), "//class[@runslocal='true']/@name");
    	List<String> allLocalTestsCleaned = new ArrayList<String>();
    	
    	for (String localTest: allLocalTests) {
    		allLocalTestsCleaned.add(localTest.replace("name=", "").replace("\"", ""));
    	}
    	
    	return allLocalTestsCleaned;
    }
    
    
}