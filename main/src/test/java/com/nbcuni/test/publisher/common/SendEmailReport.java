package com.nbcuni.test.publisher.common;

import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmailReport {

	public void SendEmail(String pathToReport, String reportName, Integer passedTestsCount, Integer failedTestsCount, List<String> failedScreenshots) {

		Config config = new Config();
		
		Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.host", "mailrelay.nbcuni.ge.com");
        props.put("mail.smtp.port", "25");

        Session session = Session.getDefaultInstance(props);
		
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
            	if (failedScreenshots.size() <= 20) {
            		failedTestScreenshotText = "Screenshots of each failed test are attached. ";
            	}
            }
            
            Integer failedIndividualTestCount = failedTestsCount / (config.getReRunOnFailureCount() + 1);
            messageBodyPart.setContent("<body>Test run complete against latest build on "
            		+ "<a href='" + config.getConfigValue("AppURL") + "'>" + config.getConfigValue("AppURL") 
            			+ "</a><br /><br />Tests passed = " + passedTestsCount.toString() + "<br />Tests failed = " 
            					+ failedIndividualTestCount.toString() + "<br /><br />A detailed report is attached. " 
            						+ failedTestScreenshotText + "Iteration Report archives are assigned to task " 
            							+ config.getConfigValue("RallyTaskID") + "." + "<br /><br />Publisher 7 "
            								+ "QA Automation</body>", "text/html");
            
            //attach html test report
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(pathToReport);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(reportName);
            multipart.addBodyPart(messageBodyPart);
            
            //add each failed screenshot (if there are less than 20 due to file size restrictions)
            if (failedScreenshots.size() <= 20) {
            	MimeBodyPart screenshotMimeBodyParts = null;
            	for (String failedScreenshot : failedScreenshots) {
            	
            		String[] fileNames = failedScreenshot.split("screenshots");
            		String fileName = fileNames[1].replace("\\", "");
            		fileName = fileName.replace("/", "");
            		screenshotMimeBodyParts = null;
            		screenshotMimeBodyParts=new MimeBodyPart();
            		DataSource screenshotSource = new FileDataSource(failedScreenshot);
            		screenshotMimeBodyParts.setDataHandler(new DataHandler(screenshotSource));
            		screenshotMimeBodyParts.setFileName(fileName);
            		multipart.addBodyPart(screenshotMimeBodyParts);
                
            	}
            }

            message.setContent(multipart);

            if (config.getConfigValue("SendReportAutoEmails").equals("true")) {
            	Transport.send(message);
            	System.out.println("Successfully sent report result email.");
            }
            else {
            	System.out.println("Report result email not sent per configuration setting.");
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}