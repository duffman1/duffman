package com.nbcuni.test.publisher.common;

import org.eclipse.egit.github.core.CommitStatus;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.testng.annotations.Test;

public class UploadReportsGitHub {

	@Test
    public void UploadReport() throws Exception {

    	Config config = new Config();
    	
    	
    	//Basic authentication
    	GitHubClient client = new GitHubClient();
    	client.setCredentials("baclark77@yahoo.com", "tufNewcyd4");
    	
    	int pullId = 20;
    	
    	PullRequestService pullRequestService = new PullRequestService();
    	pullRequestService.getClient().setCredentials("baclark77", "tufNewcyd4");
    	RepositoryId repository = new RepositoryId("NBCUOTS", "Publisher7_citest");
    	
    	CommitService commitService = new CommitService();
    	commitService.getClient().setCredentials("baclark77", "tufNewcyd4");
    	
    	String shaId = null;
    	for (RepositoryCommit commit : pullRequestService.getCommits(repository, pullId)) {
    		System.out.println(commit.getUrl());
    		shaId = commit.getCommit().getSha();
    		
    	}
    	
    	
    	CommitStatus commitStatus = new CommitStatus();
    	commitStatus.setState("success");
    	commitStatus.setDescription("an automated description");
    	commitStatus.setTargetUrl("http://google.com");
    	commitService.createStatus(repository, "a8736b475f08e5954dfc3faf1735fd45f478902f", commitStatus);
    	
    	
    	
    	
    	
    	
    	
    	
    }
}