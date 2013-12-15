package com.nbcuni.test.publisher;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.testng.Reporter;

import static org.testng.AssertJUnit.fail;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nbcuni.test.lib.Util;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * NBC.com Search Library. Copyright
 * 
 * @author Robert Picazo
 * @version 1.0 Date: September 11, 2013
 *********************************************/

public class Search {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Page_Title = "Search for";
    private static String Page_Header = "Search";
    private static String Edit_Search = "//input[@id='ezsearch-string']";
    private static String Button_Search = "//a[@title='Search']";
    private static String Section_Full_Episodes = "//div[@id='ez-fullep']";
//    private static String Link_Pagination_Previous = "//a[@title='Previous']";
//    private static String Link_Pagination_Next = "//a[@title='Next']";
//    private static String Link_Pagination_PageNumber = "//a[@title='xx']"; 
//    private static String Section_Pagination_PageNumbers = "//a[@class='ez-paginationMod-page']";
    private static String Edit_Top_Search = "//input[@id = 'search-global']";
    private static String Button_Top_Search = "//form[@class='navbar-search']//button[@class='icons-nbc-search']";
    private static String Pagination_Previous_ia = "//div[@id='ez-pagination-bottom']//span[@class='ez-paginationMod-prev']";
    private static String Pagination_Previous_a = "//div[@id='ez-pagination-bottom']//a[@class='ez-paginationMod-prev']";
    private static String Pagination_Next_ia = "//div[@id='ez-pagination-bottom']//span[@class='ez-paginationMod-next']";
    private static String Pagination_Next_a = "//div[@id='ez-pagination-bottom']//a[@class='ez-paginationMod-next']";
    private static String Pagination_Current = "//div[@id='ez-pagination-bottom']//span[@class='ez-paginationMod-currentPage']";    
    
    
    public Search(final CustomWebDriver custWebDr, final AppLib al2) {
        webDriver = custWebDr;
        al = al2;
        ul = new Util(webDriver);
        try {
            if (!webDriver.getTitle().contains(Page_Title)) {
                fail("Page Was Not in the Search Page screen as expected");
            }
        } catch (Exception e) {
            al.fail(e.toString());
        }
    }
    
    public void validateSearchResults(String query) throws Exception{
    	ul.verifyValuefromEditBox(Edit_Search, query);
    	try {
            if (!webDriver.getTitle().contains(Page_Title + " " + query)) {
                fail("Search text does not display on page title");
            }
        } catch (Exception e) {
            al.fail(e.toString());
        }
    	
    }
    
    
    public void getFullEpisodeContentFromAPI(String query) throws Exception{
    	JsonArray arrFields = null;
    	String api_query = "http://api.ramp.com/v1/search?apikey=9e288fdfa9b120e08715abc563d1d3ff"
    	+ "&media=video"
    	+ "&format=json"
    	+ "&field=ezgenericnavmulti03:%22Full%20Episode%22"
    	+ "&q=" + query
    	+ "&num=3&start=1";
        Reporter.log("HTTP REQUEST: " + api_query);

        // get JSON response
        URL url = new URL(api_query);
        Reporter.log("URL: " + url);
        JsonObject response = getRootNode(url).getAsJsonObject();
        JsonObject jo = getAsJsonObject(response, "Response");
        jo = jo.getAsJsonObject("ResultSet").getAsJsonObject("Results");
        JsonArray arrResults = getAsJsonArray(jo, "CompleteResult");

        int count = 0;
        for (JsonElement item : arrResults) {
        	count = count + 1;
            //JsonObject object = item.getAsJsonObject();
            //if (object.isJsonNull()) {
            //    fail("Null entry in ArrayList position " + count + ". Array Data: " + arrResults.toString());
            //}
            //if (item.getAsJsonObject().has("SearchFields")) {
            	arrFields = item.getAsJsonObject().getAsJsonObject("SearchFields").getAsJsonArray("FIELD");
            	
            	/* TODO 
            	for (Map.Entry<String, JsonElement> member : arrFields.entrySet()) {
            		
            	} */
                for (JsonElement item2 : arrFields) {
                	
                	JsonObject vid = item2.getAsJsonObject().getAsJsonObject("NAME");
                	System.out.println(vid.toString());
                    //JsonObject object1 = item2.getAsJsonObject();
                    //if (item.getAsJsonObject().getAsJsonObject("NAME")) {
                    //}
                }
            	convertJsonArrayToHashMap(arrFields);
            //}
            
        }
        
    	
    }
    

    public final JsonElement getRootNode(URL url) throws Exception {
    	url.openConnection(al.getHttpProxy());
        InputStream is = url.openStream();
        Reporter.log("HTTP REQUEST: " + url);
        InputStreamReader reader = new InputStreamReader(is);
        JsonElement je = new JsonParser().parse(reader);
        Reporter.log("JSON Response from Root: " + je);
        return (je);
    }

    public final JsonArray getAsJsonArray(JsonObject object, String item) throws Exception {
        JsonArray ja = object.getAsJsonArray(item);
        Reporter.log("JSON Array: " + ja);
        return (ja);
    } 
    
    public final JsonObject getAsJsonObject(JsonObject object, String item) throws Exception {
    	JsonObject jo = object.getAsJsonObject(item);
        Reporter.log("JSON Object: " + jo);
        return (jo);
    } 
    
    public ArrayList<HashMap<String, String>> convertJsonArrayToHashMap(JsonArray arrJson) throws Exception {
        ArrayList<HashMap<String, String>> locations = new ArrayList<HashMap<String, String>>();
        Integer count = 0;
        String value;
        for (JsonElement location : arrJson) {
            JsonObject object = location.getAsJsonObject();
            if (object.isJsonNull()) {
                fail("Null entry in ArrayList position " + count + ". Array Data: " + arrJson.toString());
            }

            HashMap<String, String> items = new HashMap<String, String>();
            for (Map.Entry<String, JsonElement> member : object.entrySet()) {
                if (member != null) {
                    if (member.getValue().isJsonNull()) {
                        value = null;
                    } else {
                        value = member.getValue().getAsString();
                    }

                    items.put(member.getKey(), value);
                } else {
                    fail("Json array member had a null entry at Array index " + count + ". Array item: "
                            + object.toString() + "Array Data: " + arrJson.toString());
                }
            }
            count++;
            locations.add(new HashMap<String, String>(items));
            Reporter.log("Items Added to ArrayList: " + items.toString());
            items.clear();
        }

        Reporter.log("Total Counter: " + count);
        Reporter.log("Total in ArrayList: " + locations.size());

        return locations;
    }
    
    public void performSearch(String searchstring) {
    	webDriver.type(Edit_Top_Search, searchstring);
    	webDriver.click(Button_Top_Search);
    	try {
    		if (!(webDriver.getTitle().contains("Search for "+ searchstring))) {
    			fail("Search Page Was Not Loaded as expected");
    		}
    	} catch (Exception e) {
            al.fail(e.toString());
        }
    }
    
    public void validatePagination() {
    	int i=0, pageLimit=5;
    	String[] urlsArray = new String[pageLimit-1];
    		//Verify page numbers match incremental index & collect page urls into an array
    	while(webDriver.isVisible(Pagination_Next_a) && i!=pageLimit) {
    		Reporter.log("Looking at page number: " + webDriver.getText(Pagination_Current));
    		Reporter.log("Storing current URL value into array: " + webDriver.getCurrentUrl());
    		if (!webDriver.getText(Pagination_Current).contains(String.valueOf(i+1))) {
    			fail("Page number on pagination ("+ webDriver.getText(Pagination_Current) +") does not match current page number ("+ String.valueOf(i+1) +")");
    		}
//    		System.out.println("Page Number: " + webDriver.getText(Pagination_Current) + " URL: " + webDriver.getCurrentUrl());
//    		System.out.println("Next page xpath value: " + Pagination_Next_a);
    		urlsArray[i++]=webDriver.getCurrentUrl();
    		webDriver.click(Pagination_Next_a);
    	}
    	
    	/* TODO - Go back through the pages while reading through the urlsArray and comparing
    	 * the urls to the current page
    	 */
    }
    
}

