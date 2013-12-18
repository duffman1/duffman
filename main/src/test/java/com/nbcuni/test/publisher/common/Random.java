package com.nbcuni.test.publisher.common;


import java.util.UUID;

import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Random Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Random {

    

    public Random() {
        
    }
    
    Util util = new Util(null);
    
    public String GetCharacterString(int MaximumLength) {

        //TODO - add a loop that creates a string as long as needed and not just the standard UUID length
        //generate a random character string value
    	String charString = UUID.randomUUID().toString().replace("-", "").substring(0, MaximumLength);
    	
        return charString;
    }
    
    
    
    
  
}

