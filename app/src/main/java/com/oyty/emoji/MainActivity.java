package com.oyty.emoji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.oyty.entity.EmoGroupEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnEmoClickListener {

    private EmoView mEmoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmoView = (EmoView) findViewById(R.id.mEmoView);
        EmoGroupEntity gridEntity = EmoGroupEntity.getDefaultGridEntity();
        EmoGroupEntity customEntity = EmoGroupEntity.getDefaultCustomEntity();
        List<EmoGroupEntity> entities = new ArrayList<>();
        entities.add(gridEntity);
        entities.add(customEntity);
        mEmoView.initData(this, entities);
    }

    @Override
    public void onCustomEmoClick() {

    }

    @Override
    public void onDeleteBtnClick() {

    }

    @Override
    public void onEmoClick() {

    }

    @Override
    public void onEmoSendBtnClick() {

    }

    @Override
    public void onNormalEmoClick() {

    }
}
