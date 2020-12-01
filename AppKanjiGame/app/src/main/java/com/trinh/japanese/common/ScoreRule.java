package com.trinh.japanese.common;

import java.text.NumberFormat;
import java.util.Currency;

public class ScoreRule {

    private static ScoreRule instance;

    public static ScoreRule getInstance() {
        if (instance == null) {
            instance = new ScoreRule();
        }
        return instance;
    }

    public String getScore(int level) {
        String score = "";
        switch (level) {
            case 2:
                score = convertFormat(100000);
                break;
            case 3:
                score = convertFormat(200000);
                break;
            case 4:
                score = convertFormat(300000);
                break;
            case 5:
                score = convertFormat(500000);
                break;
            case 6:
                score = convertFormat(1000000);
                break;
            case 7:
                score = convertFormat(2000000);
                break;
            case 8:
                score = convertFormat(3000000);
                break;
            case 9:
                score = convertFormat(5000000);
                break;
            case 10:
                score = convertFormat(10000000);
                break;
            case 11:
                score = convertFormat(15000000);
                break;
            case 12:
                score = convertFormat(20000000);
                break;
            case 13:
                score = convertFormat(30000000);
                break;
            case 14:
                score = convertFormat(50000000);
                break;
            case 15:
                score = convertFormat(70000000);
                break;
        }
        return score;
    }
    private String convertFormat(int score) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance("JPY"));
        return format.format(score);
    }
}
