package com.li.en2zh.service;

import com.li.en2zh.config.Config;
import com.li.en2zh.model.TranslationResult;
import com.li.en2zh.util.JsonUtil;

import java.util.List;

public class TranslationService {
    private final NetworkService networkService;

    public TranslationService(NetworkService networkService) {
        this.networkService = networkService;
    }

    public String translate(String word) {
        try {
            String response = networkService.sendPostRequest(Config.API_URL, word);
            List<String> explanations = JsonUtil.parseResponse(response).getExplanations();
            StringBuilder result = new StringBuilder();
            for(String str : explanations) {
                result.append(str).append("\n");
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
