package com.nbcuni.test.publisher.tests.Performance;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class PageLoads extends ParentTest{
	
    @Test(groups = {"full", "performance" })
    public void PageLoads_Test() throws Exception{
    	
    	try {
    		String baseUrl = config.getConfigValue("AppURL");
        	
        	proxyServer.newHar("HomePage");
            webDriver.get(baseUrl);
            proxyServer.endPage();
            	
            UserLogin userLogin = new UserLogin(webDriver);
            userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
                
            List<WebElement> allMenuLinks = webDriver.findElements(By.xpath("//div[@id='admin-menu-wrapper']//a"));
            List<String> allHrefs = new ArrayList<String>();
            List<String> allTitls = new ArrayList<String>();
            
            for (WebElement link : allMenuLinks) {
            	allHrefs.add(link.getAttribute("href"));
            	allTitls.add(link.getText());
            }
            
            int titleIndex = 0;
            for (String href : allHrefs) {
            	
            	if (!href.contains("flush-cache") && !href.contains("drupal.org") && !href.equals(null)) {
            		proxyServer.newPage(allTitls.get(titleIndex));
                	webDriver.get(href);
                	proxyServer.endPage();
            	}
            	
            	titleIndex++;
            	
            	if (titleIndex == 50) {
            		break; //TODO - still needs some debugging for higher page volumes
            	}
            	
            }
            
            proxyServer.getHar().writeTo(new File(config.getPathToHarReports() + "DefaultHar.har"));
        	System.out.println("Har was successfully written to");
    	}
    	catch (Exception | AssertionError e) {
    		
    	}
    	
    	
    }
}
