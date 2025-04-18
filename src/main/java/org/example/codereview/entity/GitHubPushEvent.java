package org.example.codereview.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubPushEvent {
    private String ref;
    private Repository repository;
    private List<Commit> commits;

    // Getters and setters...

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Repository {
        private String name;
        private Owner owner;

        // Getters and setters...
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner {
        private String name;
        private String login;

        // Getters and setters...
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Commit {
        private String id;
        private String message;

        @JsonProperty("url")
        private String commitUrl;

        // Getters and setters...
    }
}

