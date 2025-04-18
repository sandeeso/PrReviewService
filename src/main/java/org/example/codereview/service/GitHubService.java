package org.example.codereview.service;

import org.example.codereview.entity.PullRequest;
import org.example.codereview.entity.PullRequestFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitHubService {

    @Autowired
    private GitHubClient gitHubClient;
    public void handlePullRequest(String payload) {
        // TODO: Parse JSON, fetch PR code using GitHub API
        // TODO: Send code to ChatGPTService for suggestions
        // TODO: Post suggestions to GitHub PR as a comment
    }

    public PullRequest fetchPullRequestDetails(String owner, String repo, int pullNumber) {
        return gitHubClient.getPullRequestDetails(owner, repo, pullNumber);
    }

    // Fetch PR changed files (diff)
    public List<PullRequestFile> fetchPullRequestFiles(String owner, String repo, int pullNumber) {
        return gitHubClient.getPRFiles(owner, repo, pullNumber);
    }

    public String getPullRequestDiff(String owner, String repo, int prNumber) {
        try {
            return gitHubClient.getPullRequestDiff(owner, repo, prNumber);
        } catch (Exception e) {
            return "❌ Error fetching PR diff: " + e.getMessage();
        }
    }

}
