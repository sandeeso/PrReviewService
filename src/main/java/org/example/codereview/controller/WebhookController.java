package org.example.codereview.controller;

import org.example.codereview.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private GitHubService gitHubService;

    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody String payload,
                                                @RequestHeader("X-GitHub-Event") String eventType) {
        if ("pull_request".equals(eventType)) {
            gitHubService.handlePullRequest(payload);
        }
        return ResponseEntity.ok("✅ Webhook received");
    }
}