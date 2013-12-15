package com.nbcuni.test.publisher.tests.homepage;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.publisher.Homepage;
import com.nbcuni.test.webdriver.CaptureNetworkTraffic;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

//import net.lightbody.bmp.core.har.Har;


public class TC3_CaptureTraffic {
	
	private CustomWebDriver cs;
    private AppLib applib;
    private Util ul;

    /**
     * Instantiate the TestNG Before Class Method.
     * 
     * @param sEnv - environment
     * @throws Exception - error
     */
    @BeforeClass(alwaysRun = true)
    @Parameters("Environment")
    public void startSelenium(String sEnv) {
        try {
            cs = WebDriverClientExecution.getInstance().getDriver(true);
            applib = new AppLib(cs);
            applib.setEnvironmentInfo(sEnv);
            ul = new Util(cs);
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

    /**
     * Instantiate the TestNG After Class Method.
     * 
     * @throws Exception - error
     */
    @AfterClass(alwaysRun = true)
    public void stopSelenium() {
        try {
            cs.quit();
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

    /*************************************************************************************
     * Test 1 - Launch the browser and go to the NBC.com home page & Capture Traffic and show results<<br>
     * Test 2 -  Search for the term 'shows' & Capture Traffic and show results, just capture network traffic from a specific domain (oimg.nbcuni.com) and show results
     * Test 3 - Same Page, just capture network traffic from a specific domain (oimg.nbcuni.com), parse out parameters and show results
     * Test 4 - Same page.  Retrieve all offending response code (404, -999, 500, 503, etc....)
     * Test 5 - Validate correct no. of pixels firing
     * @throws Exception 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
//    @Test(groups = {"full", "smoke" })
//    public void Test1() throws Exception {
//        
//        applib.openApplication();
//
//        Har tempHar = cs.getHarInfo();
//        
//        for (HarEntry x:tempHar.getLog().getEntries()) {
//	    	System.out.println(
//	    			"\nRequest URL: " + x.getRequest().getUrl() +
//	    			"\nResponse Code "+ x.getResponse().getStatus() +"(" + x.getResponse().getStatusText() +")" +
//	    			"\nResponse Time (ms): " + String.valueOf(x.getTime()));
//	    			
//	    }
//        
//    }
    
    
    @Test(groups = {"full", "smoke" })
    public void Test2() throws Exception {
        System.out.println(" *******   Go to Search ******* ");
        System.out.println(" *******   Just get HAR information based on Domain ******* ");
        
        Homepage homepage = applib.openApplication();
        homepage.performHeaderSearch("shows");
        
        ArrayList<HashMap<String, String>> tempDomainHar = cs.getHarInfoByDomain("oimg.nbcuni.com");
        
        for (HashMap<String, String> har:tempDomainHar){
        		CaptureNetworkTraffic.getInstance();
				System.out.println(
    	    			"\nRequest URL: " + har.get(CaptureNetworkTraffic.REQUEST_URL) +
    	    			"\nResponse Code "+ har.get(CaptureNetworkTraffic.REQUEST_HTTPSTATUS) +
    	    			"\nResponse Time :" + har.get(CaptureNetworkTraffic.REQUEST_RESPONSETIME) + "\n\n");
        }
    }      
    
    @Test(groups = {"full", "smoke" })
    public void Test3() throws Exception {
    	System.out.println(" *******   Just get Parsed HAR information based on Domain ******* ");
            
        Homepage homepage = applib.openApplication();
        homepage.performHeaderSearch("shows");
        
        ArrayList<HashMap<String, String>> tempParsedDomainHar = cs.getParsedParamsFromHarInfoByDomain("oimg.nbcuni.com");
        
        int i=1;
        for (HashMap<String, String> har:tempParsedDomainHar){
        	System.out.println("************ Item #"+i +" **************");
        	
        	for (Entry<String, String> parsedHar:har.entrySet()){
        		
        		System.out.println(
    	    			"\nKey: " + parsedHar.getKey() +
    	    			"\nValue: "+ parsedHar.getValue());
        	}
        	
    		i++;
        }
    }
    
    
    @Test(groups = {"full", "smoke" })
    public void Test4() throws Exception {
    	System.out.println(" *******   Check for 404 and non-responsive requests ******* ");
            
        applib.openApplication();
        
        ArrayList<String> temp = cs.getListOfInvalidHTTPResponse();
        
        if (!temp.isEmpty()){
        	for (String x:temp){
        		System.out.println("Returned a 404 or non-responsive request - " + x);
        	}
        } else {
        	System.out.println("All Requests returned a valid response.");
        }
    }
    
    @Test(groups = {"full", "smoke" })
    public void Test5() throws Exception {
        System.out.println(" *******   Validate correct number of pixels firing ******* ");
        
        applib.openApplication();
        
        ArrayList<HashMap<String, String>> tempDomainHar = cs.getHarInfoByDomain("oimg.nbcuni.com");
        
        ul.verifyAndCompareTextValues(String.valueOf(tempDomainHar.size()), "1");
    }      
    
    
}
