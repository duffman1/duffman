package com.nbcuni.test.publisher.common;

import java.util.List;
import org.eclipse.egit.github.core.CommitStatus;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.testng.annotations.Test;

public class UploadReportsGitHub {

	@Test
    public void UploadReport(Integer passedTestCount, Integer failedTestCount, String rallyReportAttachURL) {

    	Config config = new Config();
    	
    	//set variables from config
    	String envURL = config.getConfigValueString("AppURL");
    	String userName = config.getConfigValueString("GitHubUsername");
    	String passWord = config.getConfigValueString("GitHubPassword");
    	String owner = config.getConfigValueString("GithubOwner");
    	String repo = config.getConfigValueString("GithubRepo");
    	int pullId = 0;
    	String ssh = null;
    	
    	try {
    		
    		//get the pull request id from the url
        	pullId = Integer.parseInt(envURL.replace("http://p7-", "").replace(".pr.publisher7.com", ""));
        	
        	//initiate github services
        	PullRequestService pullRequestService = new PullRequestService();
        	pullRequestService.getClient().setCredentials(userName, passWord);
        	CommitService commitService = new CommitService();
        	commitService.getClient().setCredentials(userName, passWord);
        	
        	//initiate repository
        	RepositoryId repository = new RepositoryId(owner, repo);
        	
        	//get the last commit associated with the pr
        	List<RepositoryCommit> commits = pullRequestService.getCommits(repository, pullId);
        	RepositoryCommit commit = commits.get(commits.size() - 1);
        	
        	//get the ssh associated with the commit
        	ssh = commit.getUrl().replace("https://api.github.com/repos/" + owner + "/" + repo + "/commits/", "");
        	
        	//set the commit status based on the results
        	String status = "success";
        	String description = "Automated Web Tests";
        	String targetUrl = rallyReportAttachURL;
        	if (failedTestCount != 0) {
        		status = "failure";
        	}
        	
        	CommitStatus commitStatus = new CommitStatus();
        	commitStatus.setState(status);
        	commitStatus.setDescription(description);
        	if (config.getConfigValueString("UploadReportToRally").equals("true")) {
        		commitStatus.setTargetUrl(targetUrl);
        	}
        	
        	//update the pull request with the status
        	commitService.createStatus(repository, ssh, commitStatus);
    		System.out.println("Successfully updated Github Pull Request.");
    		
    	}
    	catch (Exception e) {
    		System.out.println("Failed to update Github Pull Request.");
    		e.printStackTrace();
    		
    	}
    	
    }
}