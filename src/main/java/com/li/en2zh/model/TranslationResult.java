package com.li.en2zh.model;

import java.util.List;

public class TranslationResult {
    private String ukPronunciation;
    private String usPronunciation;
    private List<String> explanations;

    public String getUkPronunciation() {
        return ukPronunciation;
    }

    public void setUkPronunciation(String ukPronunciation) {
        this.ukPronunciation = ukPronunciation;
    }

    public String getUsPronunciation() {
        return usPronunciation;
    }

    public void setUsPronunciation(String usPronunciation) {
        this.usPronunciation = usPronunciation;
    }

    public List<String> getExplanations() {
        return explanations;
    }

    public void setExplanations(List<String> explanations) {
        this.explanations = explanations;
    }
}
