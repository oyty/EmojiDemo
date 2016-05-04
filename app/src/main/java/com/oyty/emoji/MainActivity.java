package com.oyty.emoji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.oyty.R;
import com.oyty.entity.EmoManager;

public class MainActivity extends AppCompatActivity implements OnEmoClickListener {

    private EmoView mEmoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmoView = (EmoView) findViewById(R.id.mEmoView);

        EmoManager.getInstance().initData();
        mEmoView.initData(this);
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

    @Override
    public void onNormalEmoClick(int resId) {

    }
}
