package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import com.nbcuni.test.publisher.common.AppLib;
import org.sikuli.script.*;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com MPX Add Player Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class MPXAddPlayer {

    private static AppLib applib;
    private static Screen sikuli;
    private static MPXAssets mpxAssets;
    
    public MPXAddPlayer(AppLib applib) {
        sikuli = new Screen();
    	MPXAddPlayer.applib = applib;
    	mpxAssets = new MPXAssets(applib);
        
    }
    
    private String getImagePath() {
    	
    	return applib.getPathToSikuliImages();
    }
    
    public void ClickPlayersLnk() throws Exception {
    	
    	Reporter.log("Click the 'Players' link.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Players/Players_Lnk.png");
    	sikuli.click(getImagePath() + "Players/Players_Lnk.png");
    }
    
    public void ClickAllPlayersLnk() throws Exception {
    	
    	Reporter.log("Click the 'All Players' link.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Players/AllPlayers_Lnk.png");
    	sikuli.click(getImagePath() + "Players/AllPlayers_Lnk.png");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Players/AllPlayers_Lst.png");
    	mpxAssets.WaitForAllAssetsToLoad();
    }
    
    public void ClickAddBtn() throws Exception {
    	
    	Reporter.log("Click the 'Add' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Common/Add_Btn.png");
    	sikuli.click(getImagePath() + "Common/Add_Btn.png", 1);
    	mpxAssets.WaitForImgPresent(getImagePath() + "Players/NewPlayer_Lbl.png");
    }
    
    public void EnterPlayerTitle(String playerTitle) throws Exception {
    	
    	Reporter.log("Enter '" + playerTitle + "' in the 'Title' text box.");
    	Thread.sleep(3000); //TODO - bad but necessary pause
    	mpxAssets.WaitForImgPresent(getImagePath() + "Media/Title_Txb.png");
    	Pattern pattern = new Pattern(getImagePath() + "Media/Title_Txb.png").targetOffset(0, 15);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.ClearInput();
    	sikuli.type(playerTitle);
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Common/Save_Btn.png");
    	sikuli.click(getImagePath() + "Common/Save_Btn.png");
    	Thread.sleep(5000); //long pause necessary as mpx processes player on backend
    }
    
    public void GiveFocusToPlayerItem() throws Exception {
    	
    	Reporter.log("Click the 'Player Title' label to give focus to the player item.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Media/Title_Txb.png");
    	sikuli.click(getImagePath() + "Media/Title_Txb.png");
    	
    }
    
    public void ClickDisablePlayerCbx() throws Exception {
    	
    	Reporter.log("Click the 'Disable Player' check box.");
    	mpxAssets.ScrollDownForImgPresent(getImagePath() + "Players/DisableThisPlayer_Cbx.png");
    	sikuli.click(getImagePath() + "Players/DisableThisPlayer_Cbx.png");
    }
    
  
}

