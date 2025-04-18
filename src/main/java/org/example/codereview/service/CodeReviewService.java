package org.example.codereview.service;

import lombok.extern.slf4j.Slf4j;
import org.example.codereview.entity.PullRequestFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CodeReviewService {
    @Autowired
    private GitHubService gitHubService;

    @Autowired
    private GitHubCommentService gitHubCommentService;

    @Autowired
    private ChatGPTService chatGPTService;

    public String reviewPullRequest(String repoOwner, String repoName, int prNumber) {
        // Step 1: Fetch PR diff or code
         List<PullRequestFile> file  = gitHubService.fetchPullRequestFiles(repoOwner, repoName, prNumber);
         StringBuilder prCode = new StringBuilder();
         file.forEach(x -> prCode.append(x.getPatch()));

         //String prCode = gitHubService.getPullRequestDiff(repoOwner, repoName, prNumber);

        // Step 2: Send to ChatGPT for review
        return chatGPTService.getSuggestions(new String(prCode));
    }

    public String reviewCommentPullRequest(String repoOwner, String repoName, int prNumber) {
        // Step 1: Fetch PR diff or code
        List<PullRequestFile> file  = gitHubService.fetchPullRequestFiles(repoOwner, repoName, prNumber);
        StringBuilder prCode = new StringBuilder();
        file.forEach(x -> prCode.append(x.getPatch()));

        //String prCode = gitHubService.getPullRequestDiff(repoOwner, repoName, prNumber);

        // Step 2: Send to ChatGPT for review

         log.info("going to get suggestion on PR");
         String message = chatGPTService.getSuggestions(new String(prCode));

         log.info(" Going to comment on PR");
         gitHubCommentService.commentOnPullRequest(repoOwner, repoName, prNumber, message);

         return "Application successfully posted a comment";

    }

}
