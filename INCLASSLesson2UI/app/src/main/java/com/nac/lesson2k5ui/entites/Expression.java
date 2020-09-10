package com.nac.lesson2k5ui.entites;

import java.io.Serializable;

public class Expression implements Serializable {
    private int a,b;

    public Expression(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

}
