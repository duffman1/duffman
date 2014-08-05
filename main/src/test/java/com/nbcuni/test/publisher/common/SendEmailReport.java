package com.nbcuni.test.publisher.common;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmailReport {

	public void SendEmail(String pathToReport, String reportName, Integer passedTestsCount, Integer failedTestsCount) throws Exception {

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
            
            if (!failedTestsCount.equals(0)) {
            	message.setSubject("Test Automation Report - Failed");
            }
            else {
            	message.setSubject("Test Automation Report - Passed");
            }
            
            BodyPart messageBodyPart = new MimeBodyPart();

            String failedTestScreenshotText = "";
            if (!failedTestsCount.equals(0)) {
            	failedTestScreenshotText = "Screenshots of each failed test are attached. ";
            	
            }
            
            Integer failedIndividualTestCount = failedTestsCount / (config.getReRunOnFailureCount() + 1);
            messageBodyPart.setContent("<body>Test run complete against latest build on "
            		+ "<a href='" + config.getConfigValue("AppURL") + "'>" + config.getConfigValue("AppURL") 
            			+ "</a><br /><br />Tests passed = " + passedTestsCount.toString() + "<br />Tests failed = " 
            					+ failedIndividualTestCount.toString() + "<br /><br />A detailed functional report is attached. " 
            						+ "A detailed http archive file (HAR) is attached and can be viewed with an online <a href='http://www.softwareishard.com/har/viewer/'>HAR viewer</a>. " 
            							+ failedTestScreenshotText + "Iteration Report archives are assigned to task " 
            								+ config.getConfigValue("RallyTaskID") + "." + "<br /><br />Publisher 7 "
            									+ "QA Automation</body>", "text/html");
            
            
            //attach zip file
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(pathToReport);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(reportName);
            multipart.addBodyPart(messageBodyPart);
            
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Successfully sent report result email.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}