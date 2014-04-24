package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.EnhanceMediaGalleriesTab;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Content.MediaGalleries;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class EnhanceMediaGalleriesTab extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1293
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17615653109
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void EnhanceMediaGalleriesTab_TC1293() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 2
    	taxonomy.NavigateSite("Content>>Media Galleries");
    	overlay.SwitchToActiveFrame();
    	MediaGalleries mediaGalleries = new MediaGalleries(webDriver);
    	mediaGalleries.ClickAddMediaGalleryLnk();
    	overlay.SwitchToActiveFrame();
    	BasicInformation basicInformation = new BasicInformation(webDriver);
        String title = random.GetCharacterString(15);
        basicInformation.EnterTitle(title);
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();
        contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
    	
    	//Step 3
        taxonomy.NavigateSite("Content>>Media Galleries");
    	overlay.SwitchToActiveFrame();
        
    	//Steps 4 and 5
    	SearchFor searchFor = new SearchFor(webDriver, applib);
    	searchFor.SelectStatus("Draft");
    	searchFor.EnterTitle(title);
    	searchFor.ClickApplyBtn();
    	overlay.switchToDefaultContent();
    	searchFor.VerifySearchResultsPresent(Arrays.asList(title));
    	searchFor.SelectStatus("Published");
    	searchFor.EnterTitle(title);
    	searchFor.ClickApplyBtn();
    	contentParent.VerifyPageContentNotPresent(Arrays.asList(title));
    	
    	//Steps 6 and 7
    	searchFor.ClickResetBtn();
    	searchFor.EnterTitle(title);
    	Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, 1);
        Date today = cal.getTime();
        Date tomorrow = cal2.getTime();
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
        sdfDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        String todayDate = sdfDate.format(today);
        String tomorrowDate = sdfDate.format(tomorrow);
        searchFor.EnterUpdatedOnAfterDate(tomorrowDate);
        searchFor.ClickApplyBtn();
        contentParent.VerifyPageContentNotPresent(Arrays.asList(title));
        searchFor.EnterUpdatedOnAfterDate(todayDate);
        searchFor.ClickApplyBtn();
        searchFor.VerifySearchResultsPresent(Arrays.asList(title));
        searchFor.ClickResetBtn();
        
        //Steps 8 - 13
        Integer resultSetSize = searchFor.GetSearchResultSize();
    	Assert.assertTrue(resultSetSize >= 1);
    	searchFor.ClickSearchHeaderColumnLnk("Title");
    	Assert.assertEquals(searchFor.GetSearchResultSize(), resultSetSize);
    	searchFor.ClickSearchHeaderColumnLnk("Author");
    	Assert.assertEquals(searchFor.GetSearchResultSize(), resultSetSize);
    	searchFor.ClickSearchHeaderColumnLnk("Status");
    	Assert.assertEquals(searchFor.GetSearchResultSize(), resultSetSize);
    	searchFor.ClickSearchHeaderColumnLnk("Updated");
    	Assert.assertEquals(searchFor.GetSearchResultSize(), resultSetSize);
    	
    	//Step 14
    	searchFor.EnterTitle(title);
    	searchFor.ClickApplyBtn();
    	mediaGalleries.ClickEditMenuBtn(title);
    	basicInformation.EnterSynopsis();
    	contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Media Gallery " + title + " has been updated.");
        taxonomy.NavigateSite("Content>>Media Galleries");
        overlay.SwitchToActiveFrame();
        searchFor.EnterTitle(title);
    	searchFor.ClickApplyBtn();
    	overlay.switchToDefaultContent();
    	mediaGalleries.ClickEditExtendMenuBtn(title);
    	mediaGalleries.ClickDeleteMenuBtn(title);
    	Delete delete = new Delete(webDriver);
    	delete.ClickDeleteBtn();
    	contentParent.VerifyMessageStatus("Media Gallery " + title + " has been deleted.");
    	
    	//Step 15 N/A
    	
    }
}
