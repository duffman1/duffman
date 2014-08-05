package com.nbcuni.test.publisher.common;

import java.net.InetAddress;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendTestRunStartEmail {

	public void SendEmail() throws Exception {

		Config config = new Config();
		
		final String userName = config.getConfigValue("GmailUsername");
		final String passWord = config.getConfigValue("GmailPassword");
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, passWord);
			}
		  });
		
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Automation@nbcuni.com"));
            
            message.setRecipients(Message.RecipientType.TO,
            			InternetAddress.parse(config.getConfigValue("SendReportEmailAddress")));
            
            message.setSubject("Test Automation Execution Started");
            message.setContent("<body>An automated test suite execution has been started on <strong>" + InetAddress.getLocalHost().getHostName().replace(".local", "") 
            		+ "</strong> against " + "<a href='" + config.getConfigValue("AppURL") + "'>" + config.getConfigValue("AppURL") + "</a>" 
            		+ ". Please wait until this suite has completed and notification has been sent " 
            		+ "to QA staff before accessing this box remotely.<br /><br /> Publisher 7 QA Automation</body>", "text/html");
            
            if (InetAddress.getLocalHost().getHostName().contains("NBCAutomation")) {
            	Transport.send(message);
            	System.out.println("Successfully sent test run start email.");
            }
            else {
            	System.out.println("Test run start email not sent as CRE is not a public NBC environment.");
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}