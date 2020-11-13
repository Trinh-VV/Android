package com.trinh.japanese.entities;

public interface OnFragmentCallback {
    void showFrg(String sourceTag, String tag, boolean isMoveBack);
    void showFrg(String sourceTag, String tag, Object data, boolean isMoveBack);
    void closeApp();
}
