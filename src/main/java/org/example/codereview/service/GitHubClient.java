package org.example.codereview.service;

import org.example.codereview.entity.PullRequest;
import org.example.codereview.entity.PullRequestFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "GitHubClient", url = "https://api.github.com")
public interface GitHubClient {

    // Fetch the PR details

    @GetMapping("/repos/{owner}/{repo}/pulls/{pullNumber}")
    PullRequest getPullRequestDetails(@PathVariable("owner") String owner,
                                      @PathVariable("repo") String repo,
                                      @PathVariable("pullNumber") int pullNumber);

    // Fetch files changed in the PR (diff)
    @GetMapping("/repos/{owner}/{repo}/pulls/{pullNumber}/files")
    List<PullRequestFile> getPRFiles(@PathVariable("owner") String owner,
                                     @PathVariable("repo") String repo,
                                     @PathVariable("pullNumber") int pullNumber);

    @GetMapping(value = "/repos/{owner}/{repo}/pulls/{pull_number}", produces = "application/vnd.github.v3.diff")
    String getPullRequestDiff(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @PathVariable("pull_number") int pullNumber
    );
}
