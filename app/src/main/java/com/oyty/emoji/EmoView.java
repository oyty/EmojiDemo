package com.oyty.emoji;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.oyty.entity.EmoGroupChangeEntity;
import com.oyty.entity.EmoGroupEntity;
import com.oyty.entity.EmoPagerEntity;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.OnClickListener;


/**
 * Created by oyty on 4/30/16.
 */
public class EmoView extends LinearLayout implements OnClickListener, ViewPager.OnPageChangeListener, OnGroupChangeListener {

    private Context context;

    public static final int TYPE_GRID_VIEW_NORMAL = 0x01;
    public static final int TYPE_GRID_VIEW_CUSTOM = 0x02;

    private ViewPager mEmoViewPager;
    private EmoDotView mEmoDotView;
    private EmoGroupView mEmoGroupView;
    private Button mEmoSendBtn;

    private EmoPagerAdapter adapter;
    private OnEmoClickListener listener;

    private List<EmoNormalGridView> viewList;

    private List<EmoGroupEntity> emoGroupEntities;
    private List<EmoPagerEntity> emoPagerEntities;
    private List<EmoGroupChangeEntity> emoGroupChangeEntities;

    public EmoView(Context context) {
        super(context);
        initView(context);
    }

    public EmoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EmoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_emo_container, this);
        mEmoViewPager = (ViewPager) findViewById(R.id.mEmoViewPager);
        mEmoDotView = (EmoDotView) findViewById(R.id.mEmoDotView);
        mEmoGroupView = (EmoGroupView) findViewById(R.id.mEmoGroupView);
        mEmoSendBtn = (Button) findViewById(R.id.mEmoSendBtn);

        initViewListener();
    }

    private void initViewListener() {
        mEmoSendBtn.setOnClickListener(this);
        mEmoViewPager.addOnPageChangeListener(this);
    }

    public void initData(OnEmoClickListener listener, List<EmoGroupEntity> entities) {
        this.listener = listener;
        this.emoGroupEntities = entities;
        initEntities();
        mEmoDotView.notifyDataChanged(0, entities.get(0).pageCount);
        mEmoGroupView.initData(this, entities);

        adapter = new EmoPagerAdapter();
        mEmoViewPager.setAdapter(adapter);
    }

    private void initEntities() {
        emoPagerEntities = new ArrayList<>();
        emoGroupChangeEntities = new ArrayList<>();
        int startIndex = 0;
        for(int i = 0; i< emoGroupEntities.size(); i++) {
            EmoGroupEntity entity = emoGroupEntities.get(i);
            EmoGroupChangeEntity changeEntity = new EmoGroupChangeEntity();
            changeEntity.groupIndex = i;
            changeEntity.groupStartIndex = startIndex;
            emoGroupChangeEntities.add(changeEntity);
            startIndex += entity.pageCount;
            for(int j=0; j<entity.pageCount; j++) {
                EmoPagerEntity pagerEntity = new EmoPagerEntity();
                pagerEntity.typeGridView = entity.isGridType() ? TYPE_GRID_VIEW_NORMAL : TYPE_GRID_VIEW_CUSTOM;
                pagerEntity.startResId = entity.index;
                pagerEntity.pagerCount = entity.pageCount;
                pagerEntity.curPagerIndex = j;
                pagerEntity.groupIndex = i;
                emoPagerEntities.add(pagerEntity);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mEmoSendBtn:
                listener.onEmoSendBtnClick();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        EmoPagerEntity entity = emoPagerEntities.get(position);
        mEmoDotView.notifyDataChanged(entity.curPagerIndex, entity.pagerCount);
        mEmoGroupView.notifyDataChanged(entity.groupIndex);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onGroupChanged(int position) {
        EmoGroupChangeEntity changeEntity = emoGroupChangeEntities.get(position);
        mEmoViewPager.setCurrentItem(changeEntity.groupStartIndex);
    }


    private int getTotalPagerCount() {
        int totalCount = 0;
        for(EmoGroupEntity entity : emoGroupEntities) {
            totalCount += entity.pageCount;
        }
        return totalCount;
    }

    class EmoPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return getTotalPagerCount();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            EmoPagerEntity entity = emoPagerEntities.get(position);
            if(entity.typeGridView == TYPE_GRID_VIEW_NORMAL) {
                EmoNormalGridView normal = new EmoNormalGridView(context);
                normal.initData(entity.startResId, entity.curPagerIndex, listener);
                container.addView(normal);
                return normal;
            } else {
                EmoCustomGridView custom = new EmoCustomGridView(context);
                custom.initData(entity.startResId, entity.curPagerIndex, listener);
                container.addView(custom);
                return custom;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}

