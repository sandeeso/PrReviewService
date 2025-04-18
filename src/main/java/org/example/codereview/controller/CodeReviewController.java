package org.example.codereview.controller;

import org.example.codereview.service.ChatGPTService;
import org.example.codereview.service.CodeReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class CodeReviewController {

    private final ChatGPTService chatGPTService;

    @Autowired
    private CodeReviewService codeReviewService;
    public CodeReviewController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    // Endpoint to send PR code to ChatGPT and get suggestions
    @PostMapping("/suggestions")
    public String getSuggestions(@RequestBody String codeSnippet) {
        return chatGPTService.getSuggestions(codeSnippet);
    }

    @GetMapping("/{owner}/{repo}/{prNumber}")
    public String reviewPR(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable int prNumber
    ) {
        return codeReviewService.reviewPullRequest(owner, repo, prNumber);
    }

    @GetMapping("/code/{owner}/{repo}/{prNumber}")
    public String reviewCommentPR(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable int prNumber
    ) {
        return codeReviewService.reviewCommentPullRequest(owner, repo, prNumber);
    }
}
