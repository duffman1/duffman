package com.nbcuni.test.publisher.contentbuildscripts.GetEnabledModules;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class GetAllModules extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class)
    public void Test() throws Exception {
    	
    	//login to pub7
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
		
    	
    		String filePath = System.getProperty("user.dir") + "/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/GetEnabledModules/GetAllModules.txt";
    	    filePath = filePath.replace("/", File.separator);
    	    File file = new File(filePath); 
        	if(!file.exists()){
        		file.createNewFile();
        	}
        	FileWriter fileWritter = new FileWriter(file.getAbsolutePath(),true);
        	BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        	
    		//open the modules page
    			applib.openSitePage("/admin/modules");
    			Thread.sleep(10000);
    			
    			//get the label of every enabled module
    			List<String> allEnabledModules = new ArrayList<String>();
    			for (WebElement el : webDriver.findElements(By.xpath("//div[contains(@class, 'item-modules')]//input[@checked='checked']/../../../td[2]//strong"))) {
    				allEnabledModules.add(el.getText());
    			}
    			
    			//log the title
    			for(String module : allEnabledModules) {
    				bufferWritter.write(module + System.lineSeparator());
    			}
                
            	bufferWritter.close();
    	
    }
}