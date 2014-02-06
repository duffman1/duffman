package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.sikuli.script.*;

/*********************************************
 * publisher.nbcuni.com MPX Add Player Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class MPXAddPlayer {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    Screen s = new Screen();
    MPXAssets mpxAssets = new MPXAssets(webDriver, applib);
    
    public MPXAddPlayer(final CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        
    }
    
    private String getImagePath() {
    	
    	String PathToImages = applib.getPathToSikuliImages();
    	return PathToImages;
    }
    
    public void ClickPlayersLnk() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "Players/Players_Lnk.png");
    	s.click(path + "Players/Players_Lnk.png");
    }
    
    public void ClickAllPlayersLnk() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "Players/AllPlayers_Lnk.png");
    	s.click(path + "Players/AllPlayers_Lnk.png");
    	mpxAssets.WaitForImgPresent(path + "Players/AllPlayers_Lst.png");
    	mpxAssets.WaitForAllAssetsToLoad(path);
    }
    
    public void ClickAddBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "Common/Add_Btn.png");
    	s.click(path + "Common/Add_Btn.png");
    	mpxAssets.WaitForImgPresent(path + "Players/NewPlayer_Lbl.png");
    }
    
    public void EnterPlayerTitle(String playerTitle) throws Exception {
    	
    	String path = this.getImagePath();
    	Thread.sleep(3000); //TODO - bad but necessary pause
    	mpxAssets.WaitForImgPresent(path + "Media/Title_Txb.png");
    	Pattern pImage = new Pattern(path + "Media/Title_Txb.png").targetOffset(0, 15);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    	mpxAssets.ClearInput();
    	s.type(playerTitle);
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "Common/Save_Btn.png");
    	s.click(path + "Common/Save_Btn.png");
    	Thread.sleep(2000); //TODO - add dynamic wait
    }
    
    public void GiveFocusToPlayerItem() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "Media/Title_Txb.png");
    	s.click(path + "Media/Title_Txb.png");
    	
    }
    
    public void ClickDisablePlayerCbx() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.ScrollDownForImgPresent(path + "Players/DisableThisPlayer_Cbx.png");
    	s.click(path + "Players/DisableThisPlayer_Cbx.png");
    }
    
  
}

