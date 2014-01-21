package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.sikuli.script.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/*********************************************
 * publisher.nbcuni.com MPX Add Player Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class MPXAddPlayer {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    private final Util ul;
    Screen s = new Screen();
    
    
    public MPXAddPlayer(final CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        ul = new Util(webDriver);
        
    }
    
    private String getImagePath() {
    	
    	String PathToImages = applib.getPathToSikuliImages();
    	return PathToImages;
    }
    
    public void ClickPlayersLnk() throws Exception {
    	
    	String path = this.getImagePath();
    	s.wait(path + "Players/Players_Lnk.png", 30);
    	Thread.sleep(2000);
    	s.click(path + "Players/Players_Lnk.png");
    }
    
    public void ClickAllPlayersLnk() throws Exception {
    	
    	String path = this.getImagePath();
    	s.wait(path + "Players/AllPlayers_Lnk.png", 30);
    	Thread.sleep(2000);
    	s.click(path + "Players/AllPlayers_Lnk.png");
    }
    
    public void ClickAddBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	s.wait(path + "Common/Add_Btn.png", 30);
    	Thread.sleep(2000);
    	s.click(path + "Common/Add_Btn.png");
    }
    
    public void EnterPlayerTitle(String playerTitle) throws Exception {
    	
    	String path = this.getImagePath();
    	s.wait(path + "Players/TitleNewPlayer_Txb.png", 30);
    	Thread.sleep(2000);
    	s.type(playerTitle);
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	s.wait(path + "Common/Save_Btn.png", 30);
    	Thread.sleep(2000);
    	s.click(path + "Common/Save_Btn.png");
    }
    
    
    
  
}

