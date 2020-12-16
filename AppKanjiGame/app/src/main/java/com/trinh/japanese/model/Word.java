package com.trinh.japanese.model;

import java.util.List;

public class Word {
    private boolean isActive;
    private List<Explain> explains;
    private String createdAt;
    private String updatedAt;
    private String id;
    private String keySearch;
    private String reading;
    private String wordDict;

    public Word(String createdAt, String updatedAt, String id, boolean isActive,
                String keySearch, String reading, String wordDict, List<Explain> explains) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        this.isActive = isActive;
        this.keySearch = keySearch;
        this.reading = reading;
        this.wordDict = wordDict;
        this.explains = explains;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getWordDict() {
        return wordDict;
    }

    public void setWordDict(String wordDict) {
        this.wordDict = wordDict;
    }

    public List<Explain> getExplains() {
        return explains;
    }

    public void setExplains(List<Explain> explains) {
        this.explains = explains;
    }
}
