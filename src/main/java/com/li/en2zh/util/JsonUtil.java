package com.li.en2zh.util;

import com.li.en2zh.model.TranslationResult;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public static TranslationResult parseResponse(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);

            TranslationResult result = new TranslationResult();

            JSONObject pronunciations = jsonResponse.getJSONObject("dictionary").getJSONObject("prons");

            result.setUkPronunciation(pronunciations.getString("en"));
            result.setUsPronunciation(pronunciations.getString("en-us"));

            JSONArray explanations = jsonResponse.getJSONObject("dictionary").getJSONArray("explanations");
            List<String> explanationList = new ArrayList<>();
            for (int i = 0; i < explanations.length(); i++) {
                explanationList.add(explanations.getString(i));
            }
            result.setExplanations(explanationList);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
}
