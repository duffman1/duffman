package com.nbcuni.test.publisher.tests.Performance;

import java.io.File;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class PageLoads extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void PageLoads_Test() throws Exception{
    	
    	try {
    		String baseUrl = config.getConfigValue("AppURL");
        	
        	proxyServer.newHar("HomePage");
            webDriver.get(baseUrl);
            proxyServer.endPage();
            	
            UserLogin userLogin = new UserLogin(webDriver);
            userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
                
            proxyServer.newPage("Content");
            webDriver.get(baseUrl + "/admin/content");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>AddBlock");
            webDriver.get(baseUrl + "/block/add");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>AddContent");
            webDriver.get(baseUrl + "/node/add");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>Blocks");
            webDriver.get(baseUrl + "/admin/content/blocks");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>Comments");
            webDriver.get(baseUrl + "/admin/content/comment");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>ContentRevisions");
            webDriver.get(baseUrl + "/admin/content/content-revisions");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>Files");
            webDriver.get(baseUrl + "/admin/content/file");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>Files>>Thumbnails");
            webDriver.get(baseUrl + "/admin/content/file/thumbnails");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>Files>>MPXPlayers");
            webDriver.get(baseUrl + "/admin/content/file/mpxplayer");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>Logos");
            webDriver.get(baseUrl + "/admin/content/logos");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>MediaGalleries");
            webDriver.get(baseUrl + "/admin/content/media_galleries");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>Queues");
            webDriver.get(baseUrl + "/admin/content/queues");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>Queues>>AddPromoQueue");
            webDriver.get(baseUrl + "/admin/content/queues/add/promo_queue");
            proxyServer.endPage();
                
            proxyServer.newPage("Content>>LockedDocuments");
            webDriver.get(baseUrl + "/admin/content/content_lock");
            proxyServer.endPage();
                
            proxyServer.newPage("MyWorkbench");
            webDriver.get(baseUrl + "/admin/workbench");
            proxyServer.endPage();
                
            proxyServer.newPage("MyWorkbench>>CreateContent");
            webDriver.get(baseUrl + "/admin/workbench/create");
            proxyServer.endPage();
                
            proxyServer.newPage("MyWorkbench>>Scheduled");
            webDriver.get(baseUrl + "/admin/workbench/scheduled");
            proxyServer.endPage();
                
            proxyServer.newPage("MyWorkbench>>Scheduled>>Content");
            webDriver.get(baseUrl + "/admin/workbench/scheduled/content");
            proxyServer.endPage();
                
            proxyServer.newPage("MyWorkbench>>Scheduled>>Content>>MPXMedia");
            webDriver.get(baseUrl + "/admin/workbench/scheduled/videos");
            proxyServer.endPage();
                
            proxyServer.newPage("Structure");
            webDriver.get(baseUrl + "/admin/structure");
            proxyServer.endPage();
                
            proxyServer.newPage("Structure>>Blocks");
            webDriver.get(baseUrl + "/admin/structure/block");
            proxyServer.endPage();
                
            proxyServer.newPage("Structure>>Collections");
            webDriver.get(baseUrl + "/admin/structure/collection");
            proxyServer.endPage();
                
            proxyServer.newPage("Structure>>ContentTypes");
            webDriver.get(baseUrl + "/admin/structure/types");
            proxyServer.endPage();
                
            proxyServer.newPage("Structure>>DisplaySuite");
            webDriver.get(baseUrl + "/admin/structure/ds");
            proxyServer.endPage();
                
            proxyServer.newPage("Structure>>FacebookApps");
            webDriver.get(baseUrl + "/admin/structure/fb");
            proxyServer.endPage();
                
            proxyServer.newPage("Structure>>Features");
            webDriver.get(baseUrl + "/admin/structure/features");
            proxyServer.endPage();
                
            proxyServer.newPage("Structure>>FieldCollections");
            webDriver.get(baseUrl + "/admin/structure/field-collections");
            proxyServer.endPage();
                
                proxyServer.newPage("Structure>>FileTypes");
                webDriver.get(baseUrl + "/admin/structure/file-types");
                proxyServer.endPage();
                
                proxyServer.newPage("Structure>>Menus");
                webDriver.get(baseUrl + "/admin/structure/menu");
                proxyServer.endPage();
                
                proxyServer.newPage("Structure>>PermissionSets");
                webDriver.get(baseUrl + "/admin/structure/permission_sets");
                proxyServer.endPage();
                
                proxyServer.newPage("Structure>>Queues");
                webDriver.get(baseUrl + "/admin/structure/queues");
                proxyServer.endPage();
                
                proxyServer.newPage("Structure>>Taxonomy");
                webDriver.get(baseUrl + "/admin/structure/taxonomy");
                proxyServer.endPage();
        	
                
        	proxyServer.getHar().writeTo(new File(config.getPathToHarReports() + "DefaultHar.har"));
        	System.out.println("Har was successfully written to");
            
    	}
    	catch (Exception | AssertionError e) { }
    	
    }
}
