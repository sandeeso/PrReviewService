package org.example.codereview.controller;

import org.example.codereview.service.GitHubService;
import org.example.codereview.entity.PullRequest;
import org.example.codereview.entity.PullRequestFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitHubController {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("/github/{owner}/{repo}/pr/{pullNumber}")
    public String getPullRequest(@PathVariable String owner,
                                 @PathVariable String repo,
                                 @PathVariable int pullNumber) {
        // Fetch PR details
        PullRequest pullRequest = gitHubService.fetchPullRequestDetails(owner, repo, pullNumber);
        List<PullRequestFile> files = gitHubService.fetchPullRequestFiles(owner, repo, pullNumber);

        // Format the response
        StringBuilder response = new StringBuilder();
        response.append("PR Title: ").append(pullRequest.getTitle()).append("\n");
        response.append("PR Body: ").append(pullRequest.getBody()).append("\n");

        // Display changed files and diffs
        response.append("Changed Files:\n");
        for (PullRequestFile file : files) {
            response.append("File: ").append(file.getFilename()).append("\n");
            response.append("Status: ").append(file.getStatus()).append("\n");
            response.append("Patch: ").append(file.getPatch()).append("\n\n");
        }
        System.out.println(response.toString());

        return response.toString();
    }
}