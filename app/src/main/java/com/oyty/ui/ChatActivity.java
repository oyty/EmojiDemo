package com.oyty.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.oyty.R;
import com.oyty.emoji.EmoView;
import com.oyty.emoji.OnEmoClickListener;
import com.oyty.entity.EmoManager;

import java.lang.reflect.Field;

/**
 * Created by oyty on 5/3/16.
 */
public class ChatActivity extends Activity implements View.OnClickListener, OnEmoClickListener {

    private EmoView mEmoView;
    private ImageButton mSwitchInputAction;
    private EditText mSendValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mEmoView = (EmoView) findViewById(R.id.mEmoView);
        mSwitchInputAction = (ImageButton) findViewById(R.id.mSwitchInputAction);
        mSwitchInputAction.setOnClickListener(this);
        mSendValue = (EditText) findViewById(R.id.mSendValue);
    }

    @Override
    public void onClick(View view) {
        mSwitchInputAction.setBackgroundResource(R.drawable.chatting_setmode_keyboard_btn_normal);
        EmoManager.getInstance().initData();
        mEmoView.initData(this);
        mEmoView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNormalEmoClick(int resId) {
        //  根据资源ID获得资源图像的Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        //  根据Bitmap对象创建ImageSpan对象
        ImageSpan imageSpan = new ImageSpan(this, bitmap);
        //  创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
        SpannableString spannableString = new SpannableString("face");
        //  用ImageSpan对象替换face
        spannableString.setSpan(imageSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //  将随机获得的图像追加到EditText控件的最后
        mSendValue.append(spannableString);
    }

    @Override
    public void onCustomEmoClick() {

    }

    @Override
    public void onDeleteBtnClick() {

    }

    @Override
    public void onEmoSendBtnClick() {

    }
}
