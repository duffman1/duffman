package com.nbcuni.test.publisher.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

public class SendIRCReport {

	public void SendReport(String pathToReport, String reportName, Integer passedTestsCount, Integer failedTestsCount, List<String> failedScreenshots) throws Exception {

		Config config = new Config();
		
		//Server conn props
        String server = config.getConfigValue("IRCServer");
        String nick = config.getConfigValue("IRCNickname");
        String login = "simple_bot";

        //Channel to connect to
        String channel = config.getConfigValue("IRCChannel");
        
        //If enabled, connect to the server
        if (config.getConfigValue("SendReportIRCChat").equals("true")) {
        	
        	@SuppressWarnings("resource")
    		Socket socket = new Socket(server, 6667);
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream( )));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream( )));
            
            //Log on to the server
            writer.write("NICK " + nick + "\r\n");
            writer.write("USER " + login + " 8 * : Java IRC Automation Bot\r\n");
            writer.flush();
            
            //Wait until connected to server
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.indexOf("004") >= 0) {
                    //Logged in
                    break;
                }
                else if (line.indexOf("433") >= 0) {
                    System.out.println("Nickname is already in use.");
                    return;
                }
            }
            
            Integer failedIndividualTestCount = failedTestsCount / (config.getReRunOnFailureCount() + 1);
            String sendTo;
            if (!failedTestsCount.equals(0)) {
            	sendTo = "baclark77";
            }
            else {
            	sendTo = channel;
            }
            
            //Join channel and write results
            writer.write("JOIN " + channel + "\r\n");
            writer.flush();
            writer.write("PRIVMSG " + sendTo + " :Test run complete against latest build on " + config.getConfigValue("AppURL") + ".\r\n");
            writer.flush();
            writer.write("PRIVMSG " + sendTo + " :Total tests passed = " + passedTestsCount.toString() + ".\r\n");
            writer.flush();
            writer.write("PRIVMSG " + sendTo + " :Total tests failed = " + failedIndividualTestCount.toString() + ".\r\n");
            writer.flush();
            writer.write("PRIVMSG " + sendTo + " :A detailed test report has been emailed to you as well as posted to Rally attached to " + config.getConfigValue("RallyTaskID") + ".\r\n");
            writer.flush();
            
            //Close writer and release socket
            writer.close();
            socket.close();
            System.out.println("Successfully sent report result IRC chat.");
        }
        else {
        	System.out.println("Report result IRC chat not sent per configuration setting.");
        }
        
        
    }
}