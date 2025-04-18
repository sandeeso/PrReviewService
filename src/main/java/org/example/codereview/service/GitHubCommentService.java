package org.example.codereview.service;

import lombok.RequiredArgsConstructor;
import org.example.codereview.client.GitHubCommentClient;
import org.example.codereview.entity.GitHubCommentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GitHubCommentService {
    @Value("${github.token}")
    String YOUR_GITHUB_TOKEN;

    private final GitHubCommentClient commentClient;

    public void commentOnPullRequest(String owner, String repo, int prNumber, String commentText) {
        GitHubCommentRequest request = new GitHubCommentRequest(commentText);
        String token = "Bearer " + YOUR_GITHUB_TOKEN;
        commentClient.postComment(token,owner, repo, prNumber, request);
    }
}