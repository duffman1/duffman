package com.nbcuni.test.publisher.contentbuildscripts.MPXPerformanceUpgrade.CreatePub7Content;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import org.testng.annotations.Test;

public class OpenHomePage5 extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void OpenHomePage_Test() throws Exception {

    	//Continuously open the home page indefinitely
    	for(int CCount=0;CCount<500;CCount++){
    		applib.openApplication();
    		
    		webDriver.navigate().to(config.getConfigValueString("AppURL"));
    		
    	}
    	
    }
}
