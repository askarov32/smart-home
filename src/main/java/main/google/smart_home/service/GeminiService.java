package main.google.smart_home.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

    private final RestTemplate restTemplate;
    private final String apiKey;

    public GeminiService(RestTemplate restTemplate, @Value("${gemini.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    public String getGeminiData(String parameter) {
        String url = String.format("https://api.gemini.com/v1/some-endpoint?param=%s&key=%s", parameter, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
}
