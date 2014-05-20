package com.nbcuni.test.publisher.contentbuildscripts.MPXPerformanceUpgrade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class ValidateIngestionTimePub7 extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void Test() throws Exception {
    	
    	//login to pub7
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
    	
    	//navigate to mpx media page
    	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
	    overlay.SwitchToActiveFrame();
	    
    	Thread.sleep(60000);
    	
    	List<String> entriesProcessed = new ArrayList<String>();
    	int I = 1;
    	while (I == 1) {
    		
    		//read all the entries in the created asset file
    		String mediaTitle = null;
    		long creationTime = 0;
        	String filePath = System.getProperty("user.dir") + "/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/MPXPerformanceUpgrade/AssetsCreated.txt";
    	    filePath = filePath.replace("/", File.separator);
    	    File file = new File(filePath);
        	BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        	List<String> allAssets = new ArrayList<String>();
        	String line;
        	while ((line = bufferedReader.readLine()) != null) {
        		
        	   allAssets.add(line);
        	}
        	bufferedReader.close();
        	
        	
           for (String asset : allAssets) {
        	   if (!entriesProcessed.contains(asset)) {
            		   String[] data = asset.split(",");
            		   mediaTitle = data[0];
            		   creationTime = Long.valueOf(data[1]).longValue();
            		   entriesProcessed.add(asset);
            		   
            		   
            		 //search for the asset
               	    SearchFor searchFor = new SearchFor(webDriver, applib);
               	    System.out.println(mediaTitle);
               	    searchFor.EnterTitle(mediaTitle);
               	    searchFor.ClickApplyBtn();
               	    overlay.switchToDefaultContent();
               	    while (!searchFor.GetFirstMPXMediaSearchResult().equals(mediaTitle)) {
               	    	webDriver.navigate().refresh();
               	    }
                   	
                   	//log the ingestion time
                       long ingestionTime = System.nanoTime() - creationTime;
                       Reporter.log("Ingestion time for video '" + mediaTitle + "' was " + ingestionTime);
                   	String filePath2 = System.getProperty("user.dir") + "/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/MPXPerformanceUpgrade/AssetsIngested.txt";
                   	    	
                   	filePath = filePath.replace("/", File.separator);
                   	System.out.println(filePath);
                   	File file2 = new File(filePath2); 
                       if(!file2.exists()){
                       	file2.createNewFile();
                       }
                       FileWriter fileWritter = new FileWriter(file2.getAbsolutePath(),true);
                       BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                       bufferWritter.write("Ingestion time for video '" + mediaTitle + "' was " + TimeUnit.SECONDS.convert(ingestionTime, TimeUnit.NANOSECONDS) + " seconds"+ System.lineSeparator());
                       bufferWritter.close();
            	   }
        	}
        	
        	
        	
    	}
    	
    	    
    	  
    }
}