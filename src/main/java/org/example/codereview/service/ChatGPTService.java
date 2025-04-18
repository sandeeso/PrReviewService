package org.example.codereview.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ChatGPTService {

    private final String OPENAI_API_KEY;
    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ChatGPTService(@Value("${openai.api.key}") String apiKey) {
        this.OPENAI_API_KEY = apiKey;
    }

    public String getSuggestions(String codeSnippet) {
        OkHttpClient client = new OkHttpClient();

        String prompt = "Review the following Java code and suggest improvements:\n" + codeSnippet;

        // Use raw string to avoid escaping
        Map<String, Object> jsonBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                )
        );
try {
    String json = objectMapper.writeValueAsString(jsonBody);
    Request request = new Request.Builder()
            .url(ENDPOINT)
            .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
            .addHeader("Content-Type", "application/json")
            .post(RequestBody.create(json, MediaType.get("application/json")))
            .build();

    try (Response response = client.newCall(request).execute()) {
        if (!response.isSuccessful()) {
            return "❌ Error: " + response.code() + " " + response.message() + " - " + response.body().string();
        }
        return response.body() != null ? response.body().string() : "❌ Empty response from ChatGPT.";
    }
}catch (IOException e) {
            return "❌ IOException: " + e.getMessage();
        }
    }
}
