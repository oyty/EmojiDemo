package com.oyty.emoji;

/**
 * Created by oyty on 4/30/16.
 */
public interface OnEmoClickListener {

    void onNormalEmoClick(int resId);
    void onCustomEmoClick();
    void onDeleteBtnClick();
    void onEmoSendBtnClick();
}
