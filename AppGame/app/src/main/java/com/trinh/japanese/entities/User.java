package com.trinh.japanese.entities;

public class User {
    public String username;
    public int level;
    public User(String username, int level) {
        this.username = username;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public int getUserScore() {
        return level;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserScore(int userScore) {
        this.level = userScore;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userScore='" + level + '\'' +
                '}';
    }
}
