package com.nbcuni.test.publisher.content;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.webdriver.CustomWebDriver;

public class People {
	private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    private static String Role_lnk=".//*[@id='page']/div[1]/ul/li[2]/a[contains(text(),'Roles')]";
    Hashtable<Integer, String> roleColumnName=new Hashtable<Integer, String>();
    Hashtable<Integer, String> retRoleColumnName=new Hashtable<Integer, String>();
//    private static String CharacterFirstName_Txb = "//input[@id='edit-field-character-first-name-und-0-value']";
//    private static String CoverPhotoSelect_Btn = "//a[@id='edit-field-character-cover-photo-und-0-select']";
//    private static String Profile_Img = "//div[@class='media-thumbnail']/img";
    
    public People(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    public void ClickRolelnk() throws Exception {
    	
    	webDriver.click(Role_lnk);
    	
    }
    public void Permission(){
    	
    }/*Close Permission Method*/
    public void Roles(){
    	
    }/*Close Roles Method*/
    public void ApplyPermissionSet(){
    	
    }/*Close ApplyPermissionSet Method*/
    public boolean VerifyRolesCoumnTypes(){
    	
    	int num=0;
    	roleColumnName.put(0, "Permission".toUpperCase());
    	roleColumnName.put(1, "anonymous user".toUpperCase());
    	roleColumnName.put(2, "authenticated user".toUpperCase());
    	roleColumnName.put(3, "administrator".toUpperCase());
    	roleColumnName.put(4, "editor".toUpperCase());
    	roleColumnName.put(5, "senior editor".toUpperCase());
    	
    	List<WebElement> pageTables = webDriver.findElements(By.xpath(".//*[@id='permissions']/thead"));
    	
		for(WebElement pagetable:pageTables){				
		    	List<WebElement> rowsElements = pagetable.findElements(By.tagName("tr"));
				System.out.println(rowsElements.size());
				for (WebElement rowsElement:rowsElements){
					List<WebElement> rowsAttributes = rowsElement.findElements(By.tagName("th"));
					for(WebElement rowsAttribute:rowsAttributes){						
						if(rowsAttribute.isDisplayed()){							
							String colName =  rowsAttribute.getText().trim();
							if(!("PREVIEWER, CONTRIBUTOR, MODERATOR".contains(colName))){
							retRoleColumnName.put(num, colName);
							num++;
							}
						}/*Close if rowsAttribute.isDisplayed() */
					}/*Close For Loop rowsAttribute:rowsAttributes*/
				}/*Close For Loop ElementNum:sidemenuOptionsElement*/
//			}/*Close if tableID.equalsIgnoreCase("permissions")*/
		}/*Close For Loop pagetable:pageTables*/
		System.out.println(retRoleColumnName.values());
		if (roleColumnName.equals(retRoleColumnName)){System.out.println(retRoleColumnName.values());}
    return true;
	}/*Close VerifyRolesTypesAgainsEveryRow Method*/
    
    public boolean VerifyRoles() throws Exception{
    	int num=0;
    	roleColumnName.clear();
    	roleColumnName.put(0, "anonymous user".toUpperCase());
    	roleColumnName.put(1, "authenticated user".toUpperCase());
    	roleColumnName.put(2, "administrator".toUpperCase());
    	roleColumnName.put(3, "editor".toUpperCase());
    	roleColumnName.put(4, "senior editor".toUpperCase());
    	//webDriver.switchTo().defaultContent();
    	
    	Overlay overlay = new Overlay(webDriver);
    	//overlay.SwitchRoleFrm();
//        overlay.moveToCurrentFrame();
//        webDriver.switchTo().defaultContent();
//        int iFrameCount = -1;
//		Boolean frameFound = false;		
//		List<WebElement> frameSet = webDriver.findElements(By.tagName("iframe"));  
//		for (WebElement frameNum : frameSet){
//            iFrameCount++;
//            webDriver.switchTo().frame(iFrameCount);	
//			System.out.println(webDriver.findElement(By.xpath(".//*[@id='user-roles']/tbody/tr")).getText());
//		}
//		if (iFrameCount>-1){
//			System.out.println(iFrameCount);
//			webDriver.switchTo().frame(iFrameCount);	
//			System.out.println(webDriver.findElement(By.xpath(".//*[@id='user-roles']/tbody/tr")).getText());
//			frameFound = true;
//		}
    	List<WebElement> rolesRows = webDriver.findElements(By.xpath(".//*[@id='user-roles']/tbody/tr"));
    	
		for(WebElement rolesRow:rolesRows){				
			String roleName =  rolesRow.getText().trim();
			if(!("PREVIEWER, CONTRIBUTOR, MODERATOR".contains(roleName))){
			retRoleColumnName.put(num, roleName);
			num++;
			}
		}/*Close For Loop rolesRow:rolesRows*/		
		if (roleColumnName.equals(retRoleColumnName)){System.out.println(retRoleColumnName.values());}
		return true;	
    }
    
}/*Close People Class*/
