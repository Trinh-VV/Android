package com.trinh.truyencuoi.entities;

import java.io.Serializable;
import java.util.Objects;

public class Story implements Serializable {
    private String name, content;

    public Story(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
         return content;
    }

    @Override
    public boolean equals(Object o) {
        Story story = (Story) o;
        return name.equals(story.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Story{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
