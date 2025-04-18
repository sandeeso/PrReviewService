package org.example.codereview.controller;

import lombok.RequiredArgsConstructor;
import org.example.codereview.service.GitHubCommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GitHubCommentController {

    private final GitHubCommentService commentService;

    @PostMapping("/comment")
    public String postCommentToPR(
            @RequestParam String owner,
            @RequestParam String repo,
            @RequestParam int prNumber,
            @RequestParam String message
    ) {
        try {
            commentService.commentOnPullRequest(owner, repo, prNumber, message);
            return "✅ Comment posted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Failed to post comment: " + e.getMessage();
        }
    }
}
