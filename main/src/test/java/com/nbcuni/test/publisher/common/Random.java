package com.nbcuni.test.publisher.common;

import java.util.UUID;

/*********************************************
 * publisher.nbcuni.com Random Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Random {

    
    public Random() {
        
    }
    
    public String GetCharacterString(int maximumLength) throws Exception {

        //TODO - add a loop that creates a string as long as needed and not just the standard UUID length
        String charString = UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString();
    	charString = charString.replace("-", "").substring(0, maximumLength);
    	
        return charString;
    }
    
    public Integer GetInteger(int minValue, int maxValue) throws Exception {
    	
    	java.util.Random rand = new java.util.Random();

        int randomNum = rand.nextInt((maxValue - minValue) + 1) + minValue;

        return randomNum;
    }
    
}

