package com.nbcuni.test.publisher;


import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * NBC.com Homepage Library. Copyright
 * 
 * @author Jeremy Ocampo
 * @version 1.0 Date: September 1, 2013
 *********************************************/

public class Homepage {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Page_Title = "NBC.com | TV Network for Primetime, Daytime and Late Night Television Shows";
    private static String Menu_Top_Shows = "//a[text() = 'Shows']";
    private static String Menu_Top_FullEpisodes = "//a[text() = 'Full Episodes']";
    private static String Menu_Top_Schedule = "//a[text() = 'Schedule']";
    private static String Menu_Top_NewsSports = "//a[text() = 'News & Sports']";
    private static String Menu_Top_Extras = "//a[text() = 'Extras']";
    private static String Menu_Top_Shop = "//a[text() = 'Shop']";
    private static String Image_Top_logo = "//a[@class = 'brand icons-nbc-large']";
    private static String Edit_Top_Search = "//input[@id = 'search-global']";
    private static String Button_Top_Search = "//button[@class='icons-nbc-search']";

    public Homepage(final CustomWebDriver custWebDr, final AppLib al2) {
        webDriver = custWebDr;
        al = al2;
        ul = new Util(webDriver);
        try {
            if (!webDriver.getTitle().contains(Page_Title)) {
                al.fail("Page Was Not in the Home Page screen as expected");
            }
        } catch (Exception e) {
            al.fail(e.toString());
        }
    }
    
    
    public Search performHeaderSearch(String query) throws Exception {
    	webDriver.type(Edit_Top_Search, query);
    	webDriver.click(Button_Top_Search);
    	Reporter.log("URL: " + webDriver.getLocation());
        return new Search(webDriver, al);
    }
    
    public void validateTopMenu() throws Exception{
    	ul.verifyObjectExists(Image_Top_logo);
    	ul.verifyObjectExists(Menu_Top_Shows);
    	ul.verifyObjectExists(Menu_Top_FullEpisodes);
    	ul.verifyObjectExists(Menu_Top_Schedule);
    	ul.verifyObjectExists(Menu_Top_NewsSports);
    	ul.verifyObjectExists(Menu_Top_Extras);
    	ul.verifyObjectExists(Menu_Top_Shop);
    	ul.verifyObjectExists(Edit_Top_Search);
    	
    }
  
}

