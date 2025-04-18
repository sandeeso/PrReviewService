package org.example.codereview.client;

import org.example.codereview.entity.GitHubCommentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "githubCommentClient",
        url = "https://api.github.com"
)
public interface GitHubCommentClient {

    @PostMapping("/repos/{owner}/{repo}/issues/{pullNumber}/comments")
    void postComment(@RequestHeader("Authorization") String token,
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @PathVariable("pullNumber") int pullNumber,
            @RequestBody GitHubCommentRequest request
    );
}
