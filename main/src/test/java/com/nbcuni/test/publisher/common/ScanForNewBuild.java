package com.nbcuni.test.publisher.common;
/*
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import org.testng.TestNG;*/

public class ScanForNewBuild {

	/*//TODO - this class needs a LOT of future work to clean up and eventually run as CI integration.
	public  void main(String[] args) throws Exception {

		//scan the inbox account for a new build
		Session session;
	    Store store;
	    
	    Properties props = new Properties();
        session = Session.getInstance(props);
        store = session.getStore("imaps");
        store.connect("imap.gmail.com", "Pub7NBCUAutoEmailUser1@gmail.com", "Pub7NBCUAutoEmailPassword1");
		
        
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
    	
        boolean msgRecieved = false;
        Message[] msg = folder.getMessages();
            if (msg[msg.length - 1].getSubject().contains("nbcupublisher7 pipeline")) {
            	msgRecieved = true;
            
        }
            
        for (Message message : msg) {
        	if (message.getSubject().contains("nbcupublisher7 pipeline")) {
        		//message.setFlag(Flags.Flag.DELETED, true);
        	}
        }
        
        //folder.expunge();
        folder.close(false);
        store.close();
		
		if (msgRecieved == true) {
		 List<String> files = new ArrayList<String>();
	        files.add("/Users/brandonclark/SQE-TEST-Pub7/main/src/test/resources/ConfigTestNGFORTESTING.xml");

	        TestNG tng = new TestNG();
	        tng.setTestSuites(files);
	        tng.run();
		}
		
		System.exit(0);
    }*/
}