package com.trinh.japanese.model;

import java.util.List;

public class ListWord {
    List<Word> listMean;

    public ListWord(List<Word> listMean) {
        this.listMean = listMean;
    }

    public List<Word> getListMean() {
        return listMean;
    }

    public void setListMean(List<Word> listMean) {
        this.listMean = listMean;
    }
}
