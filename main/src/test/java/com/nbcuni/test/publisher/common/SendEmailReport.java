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

	public void SendEmail(String pathToReport, String reportName, Integer passedTestsCount, Integer failedTestsCount) {

		Config config = new Config();
        final String username = config.getConfigValue("GmailUsername");
        final String password = config.getConfigValue("GmailPassword");

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Automation@nbcuni.com"));
            
            //if there are failures send to automation qa resource for review
            Integer totalTestCount = passedTestsCount + failedTestsCount;
            if (!failedTestsCount.equals(0)) {
            	message.setRecipients(Message.RecipientType.TO,
            			InternetAddress.parse("brandon.clark@nbcuni.com"));
            }
            else {
            	message.setRecipients(Message.RecipientType.TO,
            			InternetAddress.parse(config.getConfigValue("SendReportEmailAddress")));
            }
            
            message.setSubject("Test Automation Report");
            
            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText("Test run complete against latest build on " + config.getConfigValue("AppURL")
                + "\n\n Total tests executed = " + totalTestCount.toString()
                	+ "\n Tests passed = " + passedTestsCount.toString()
                		+ "\n Tests failed = " + failedTestsCount.toString()
                			+ "\n\n A detailed report is attached. Iteration Report archives are assigned to task " + config.getConfigValue("RallyTaskID") + "."
                				+ "\n\n Publisher 7 Automation Team");

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();

            DataSource source = new FileDataSource(pathToReport);

            messageBodyPart.setDataHandler(new DataHandler(source));

            messageBodyPart.setFileName(reportName);

            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            if (config.getConfigValue("SendReportAutoEmails").equals("true")) {
            	Transport.send(message);
            	System.out.println("Successfully sent report result email");
            }
            else {
            	System.out.println("Report result email not sent per configuration setting.");
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}