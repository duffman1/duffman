package com.nbcuni.test.publisher.common.Reports;

import java.net.InetAddress;
import java.util.List;
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

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.ParentTest;

public class SendEmailReport extends ParentTest{

	public void SendEmail(String pathToReport, String reportName, Integer passedTestsCount, Integer failedTestsCount, List<String> includedGroups, List<String> excludedGroups) throws Exception {

		Config config = new Config();
		
		final String userName = config.getConfigValueString("GmailUsername");
		final String passWord = config.getConfigValueString("GmailPassword");
		String appURL = config.getConfigValueString("AppURL");
		
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
            			InternetAddress.parse(config.getConfigValueString("SendReportEmailAddress")));
            
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
            
            String excludedGroupTxt = "";
            if (!excludedGroups.isEmpty()) {
            	excludedGroupTxt = "<br />Excluded test group(s) = " + excludedGroups;
            }
            
            String machineName = InetAddress.getLocalHost().getHostName().replace(".local", "");
            Integer failedIndividualTestCount = failedTestsCount / (config.getConfigValueInt("ReRunOnFailureCount") + 1);
            
            String messageContent = null;
            if (abortTestSuite.equals(true)) {
            	messageContent = "<body>A critical error was encountered during the execution of the automated test suite on <strong>" + machineName + "</strong> against <a href='" + appURL + "'>" + appURL + "</a>, resulting in the failure of the test setup scripts. All subsequent tests were skipped. Please see attached report/screenshot for details on the setup script failure.<br /><br />To execute the suite and ignore the setup failure, set config value \"AbortSuiteOnSetupFailure\" to false and re-run. Note that this may result in test failures.<br /><br />Publisher 7 QA Automation</body>";
            }
            else {
            	messageContent = "<body>Test run complete for latest build against "
                		+ "<a href='" + appURL + "'>" + appURL 
                		+ "</a> on <strong>" + machineName + "</strong>.<br /><br />Included test group(s) = " + includedGroups + excludedGroupTxt + "<br />Tests passed = " 
                		+ passedTestsCount.toString() + "<br />Tests failed = " 
                		+ failedIndividualTestCount.toString() + "<br /><br />A detailed functional report is attached. " 
                		+ "A detailed http archive file (HAR) is attached and can be viewed with an online <a href='http://www.softwareishard.com/har/viewer/'>HAR viewer</a>. " 
                		+ failedTestScreenshotText + "Iteration Report archives are assigned to task " 
                		+ config.getConfigValueString("RallyTaskID") + "." + "<br /><br />Publisher 7 "
                		+ "QA Automation</body>";
            }
            messageBodyPart.setContent(messageContent, "text/html");
            
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