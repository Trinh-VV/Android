package com.trinh.japanese.model;

import java.util.List;

public class Explain {
    private String type;
    private List<String> means;
    private List<Example> examples;
    private String id;

    public Explain(String type, List<String> means, List<Example> examples, String id) {
        this.type = type;
        this.means = means;
        this.examples = examples;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getMeans() {
        return means;
    }

    public void setMeans(List<String> means) {
        this.means = means;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
