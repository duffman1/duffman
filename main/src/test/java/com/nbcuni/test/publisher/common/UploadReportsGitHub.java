package com.nbcuni.test.publisher.common;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.testng.annotations.Test;



public class UploadReportsGitHub {

	@Test
    public void UploadReport() throws Exception {

    	Config config = new Config();
    	
    	//Basic authentication
    	GitHubClient client = new GitHubClient();
    	client.setCredentials("baclark77@yahoo.com", "tufNewcyd4");
    	
    	int pullId = 1545;
    	
    	PullRequestService service = new PullRequestService();
    	service.getClient().setCredentials("baclark77", "tufNewcyd4");
    	RepositoryId repository = new RepositoryId("NBCUOTS", "Publisher7");
    	System.out.println(service.getPullRequest(repository, pullId));
    	for (CommitComment comment: service.getComments(repository, pullId)) {
    		System.out.println(comment.getBody());
    	}
    	CommitComment commitComment = new CommitComment();
    	commitComment.setPath("docroot/profiles/publisher/modules/contrib/quiz/CHANGELOG.txt");
    	commitComment.setCommitId("7feb0d66df9b259b0b20be8adcc7be148dec221c");
    	commitComment.setBody("Test comment from java github api via pub7 automated test suite.");
    	service.createComment(repository, pullId, commitComment);
    	
    	System.out.println(repository.getName());
    }
}