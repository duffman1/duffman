package com.nbcuni.test.publisher.contentbuildscripts.MPXPerformanceUpgrade.ProperAssetsIngested;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXDataClient;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class VerifyAllAssetsIngested extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
    	//login to mpx media client
    	MPXDataClient mpxDataClient = new MPXDataClient(webDriver);
    	mpxDataClient.SignInToMPXDataClient("media", config.getConfigValue("MPXUsername"), config.getConfigValue("MPXPassword"));
    	mpxDataClient.ChooseMPXAccount("NBC.com Stage");
    	List<String>allNBCComAssets = mpxDataClient.GetAllMPXObjectFields("Media", "title", "0", "30000");
        
    	//open pub 7 mpx search page
    	applib.openSitePage("/admin/content/file/mpxmedia");
    	
    	//search for each asset in the list
    	for (String assetTitle : allNBCComAssets) {
    		SearchFor searchFor = new SearchFor(webDriver, applib);
    		searchFor.EnterTitle(assetTitle);
    		searchFor.ClickApplyBtn();
    		Thread.sleep(500);
    		if (!searchFor.GetFirstMPXMediaSearchResult().equals(assetTitle)) {
    			String assetIngestionFilePath = System.getProperty("user.dir") + "/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/MPXPerformanceUpgrade/ProperAssetsIngested/AssetsIngested.txt";
                assetIngestionFilePath = assetIngestionFilePath.replace("/", File.separator);	    	
                File assetIngestionFile = new File(assetIngestionFilePath); 
                if(!assetIngestionFile.exists()){
             	   assetIngestionFile.createNewFile();
                }
                    
                FileWriter fileWritter = new FileWriter(assetIngestionFile.getAbsolutePath(),true);
                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                bufferWritter.write("Asset titled '" + assetTitle + "' is not present in search." + System.lineSeparator());
                bufferWritter.close();
    		}
    	}
        
    }
}
