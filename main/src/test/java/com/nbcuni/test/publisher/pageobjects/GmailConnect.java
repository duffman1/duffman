package com.nbcuni.test.publisher.pageobjects;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.testng.Assert;

import com.nbcuni.test.publisher.common.AppLib;

/*********************************************
 * publisher.nbcuni.com Gmail Connect Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 13, 2014
 *********************************************/

public class GmailConnect {

    private static AppLib applib;
    private static Session session;
    private static Store store;
    
    public GmailConnect(AppLib applib) {
        GmailConnect.applib = applib;
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
    
    public void VerifyAutoEmailRecieved (String subject) throws Exception {
    	
    	ConnectToGmail();
    	
    	Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
    	
        boolean msgRecieved = false;
        for (int second = 0; ; second++) {
            if (second >= 60) {
                Assert.fail("Auto email was not received after timeout");
            }
            Message[] msg = folder.getMessages();
            if (msg[msg.length - 1].getSubject().equals(subject)) {
            	msgRecieved = true;
            }
            if (msgRecieved == true){ break;}
            Thread.sleep(500);
        }
        folder.close(false);
        
        DisconnectFromGmail();
    }
    
    public void DeleteAllAutoEmailsInInbox (String subject) throws Exception {
    	
    	ConnectToGmail();
    	
    	Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
    	
        Message[] allMessages = folder.getMessages();
        for (Message message : allMessages) {
        	if (message.getSubject().equals(subject)) {
        		message.setFlag(Flags.Flag.DELETED, true);
        	}
        }
        folder.close(true);
        
        DisconnectFromGmail();
    }
    
}