package org.example.codereview.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.codereview.entity.GitHubPushEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/github-webhook")
public class TestingController {

    @PostMapping("/review-code")
    public ResponseEntity<String> handlePushEvent(@RequestBody GitHubPushEvent gitHubPushEvent) {

       log.info(gitHubPushEvent.toString());

        return ResponseEntity.ok("Code review triggered!");
    }
}
