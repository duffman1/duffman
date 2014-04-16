package com.nbcuni.test.publisher.contentbuildscripts.NBCcomIssue;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import org.testng.annotations.Test;

public class OpenHomePage1 extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void OpenHomePage_Test() throws Exception {

    	//Continuously open the home page indefinitely
    	int I = 1;
    	while (I == 1) {
    		applib.openApplication();
    	}
    	
    }
}
