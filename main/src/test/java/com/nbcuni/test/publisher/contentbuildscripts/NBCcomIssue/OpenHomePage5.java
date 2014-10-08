package com.nbcuni.test.publisher.contentbuildscripts.NBCcomIssue;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import org.testng.annotations.Test;

public class OpenHomePage5 extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void OpenHomePage_Test() throws Exception {

    	//Continuously open the home page indefinitely
    	for(int CCount=0;CCount<2000;CCount++){
    		applib.openApplication();
    		
    		webDriver.navigate().to(config.getConfigValueString("AppURL") + "/content/characterprofile1");
    		webDriver.navigate().to(config.getConfigValueString("AppURL") + "/mediagallery1");
    		webDriver.navigate().to(config.getConfigValueString("AppURL") + "/content/movie1");
    		webDriver.navigate().to(config.getConfigValueString("AppURL") + "/content/person1");
    		webDriver.navigate().to(config.getConfigValueString("AppURL") + "/content/post1");
    		webDriver.navigate().to(config.getConfigValueString("AppURL") + "/content/episode1");
    		webDriver.navigate().to(config.getConfigValueString("AppURL") + "/content/season1");
    		webDriver.navigate().to(config.getConfigValueString("AppURL") + "/content/show1");
    	}
    	
    }
}
