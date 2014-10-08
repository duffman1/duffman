package com.nbcuni.test.publisher.contentbuildscripts.MPXPerformanceUpgrade.CreateVideosAndPub7Content;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;

public class CreateBulkContent4 extends ParentTest{

    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void Test() throws Exception {
         
         //login
         UserLogin userLogin = applib.openApplication();
         userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
         CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
             
         //create character profile
         for(int CCount=0;CCount<20;CCount++) {
        
         try {
         createDefaultContent.CharacterProfile("Draft", null, null);
         }
         catch (Exception | AssertionError e) {
         applib.openApplication();
         }
        
         }
        
         //create post
         for(int CCount=0;CCount<20;CCount++) {
        
         try {
         createDefaultContent.Post("Draft");
         }
         catch (Exception | AssertionError e) {
         applib.openApplication();
         }
        
         }
        
         //create media gallery
         for(int CCount=0;CCount<20;CCount++) {
        
         try {
         createDefaultContent.MediaGallery("Draft");
         }
         catch (Exception | AssertionError e) {
         applib.openApplication();
         }
        
         }
        
         //create tv show
         for(int CCount=0;CCount<20;CCount++) {
        
         try {
         createDefaultContent.TVShow("Draft");
         }
         catch (Exception | AssertionError e) {
         applib.openApplication();
         }
        
         }
        
         //create tv season
         for(int CCount=0;CCount<20;CCount++) {
        
         try {
         createDefaultContent.TVSeason("Draft", null);
         }
         catch (Exception | AssertionError e) {
         applib.openApplication();
         }
        
         }
        
         //create tv episode
         for(int CCount=0;CCount<20;CCount++) {
        
         try {
         createDefaultContent.TVEpisode("Draft", null, null);
         }
         catch (Exception | AssertionError e) {
         applib.openApplication();
         }
        
         }
        
         //create person
         for(int CCount=0;CCount<20;CCount++) {
        
         try {
         createDefaultContent.TVEpisode("Draft", null, null);
         }
         catch (Exception | AssertionError e) {
         applib.openApplication();
         }
        
         }
        
    }
}
