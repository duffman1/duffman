package com.nbcuni.test.publisher.pageobjects;

import java.util.Properties;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;

/*********************************************
 * publisher.nbcuni.com Gmail Connect Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 13, 2014
 *********************************************/

public class GmailConnect {

    private AppLib applib;
    private Session session;
    private Store store;
    
    public GmailConnect(AppLib applib) {
        this.applib = applib;
    }
    
    private void ConnectToGmail() throws Exception { 
    	
    	Properties props = new Properties();
        session = Session.getInstance(props);
        store = session.getStore("imaps");
        store.connect("imap.gmail.com", applib.getGmailAutoEmailUsername(), applib.getGmailAutoEmailPassword());
    }
    
    private void DisconnectFromGmail() throws Exception { 
    	
    	store.close();
    }
    
    public void VerifyAutoEmailRecieved(String subject) throws Exception {
    	
    	Reporter.log("Verify that the auto email titled '" + subject + "' arrives in the gmail inbox.");
    	ConnectToGmail();
    	
    	Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
    	
        for (int second = 0; ; second++) {
            if (second >= 60) {
                Assert.fail("Auto email was not received after timeout");
            }
            Message[] msg = folder.getMessages();
            if (msg[msg.length - 1].getSubject().equals(subject)) {
            	break;
            }
            Thread.sleep(1000);
        }
        folder.close(false);
        
        DisconnectFromGmail();
    }
    
    public void DeleteAllAutoEmailsInInbox(String subject) throws Exception {
    	
    	ConnectToGmail();
    	
    	Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
    	
        Message[] allMessages = folder.getMessages();
        for (Message message : allMessages) {
        	if (message.getSubject().equals(subject)) {
        		message.setFlag(Flags.Flag.DELETED, true);
        	}
        }
        folder.expunge();
        folder.close(true);
        
        DisconnectFromGmail();
    }
    
}