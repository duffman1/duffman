package com.nbcuni.test.publisher.common;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListeners extends ParentTest implements ISuiteListener {

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite suite) {
		if (config.getConfigValueString("SendReportAutoEmails").equals("true")) {
	  	  	try {
	  	  		SendTestRunStartEmail sendTestRunStartEmail = new SendTestRunStartEmail();
	  	  		sendTestRunStartEmail.SendEmail();
	  	  	} catch (Exception e) {
	  	  		System.out.println("Failed to send test run start email.");
	  	  	}
	  	  }
	  	  else {
	  	  	System.out.println("Test run start email not sent per configuration setting.");
	  	  }
	}
}