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

import com.oyty.R;
import com.oyty.entity.EmoGroup;
import com.oyty.entity.EmoManager;
import com.oyty.entity.EmoPager;

import static android.view.View.OnClickListener;


/**
 * Created by oyty on 4/30/16.
 */
public class EmoView extends LinearLayout implements OnClickListener, ViewPager.OnPageChangeListener, OnGroupChangeListener {

    private Context context;
    private ViewPager mEmoViewPager;
    private EmoDotView mEmoDotView;
    private EmoGroupView mEmoGroupView;
    private Button mEmoSendBtn;

    private EmoPagerAdapter adapter;
    private OnEmoClickListener listener;

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

    public void initData(OnEmoClickListener listener) {
        this.listener = listener;
        mEmoDotView.notifyDataChanged(0, EmoManager.getInstance().getEmoEntities().get(0).pageCount);
        mEmoGroupView.initData(this, EmoManager.getInstance().getEmoEntities());

        adapter = new EmoPagerAdapter();
        mEmoViewPager.setAdapter(adapter);
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
        EmoPager entity = EmoManager.getInstance().getEmoPagerEntities().get(position);
        mEmoDotView.notifyDataChanged(entity.pagerIndex, entity.pagerCount);
        mEmoGroupView.notifyDataChanged(entity.groupIndex);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onGroupChanged(int position) {
        EmoGroup changeEntity = EmoManager.getInstance().getEmoGroupEntities().get(position);
        mEmoViewPager.setCurrentItem(changeEntity.pagerStartIndex);
    }

    class EmoPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return EmoManager.getInstance().getTotalPagerCount();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            EmoPager entity = EmoManager.getInstance().getEmoPagerEntities().get(position);
            if(entity.type == EmoManager.TYPE_GRID_VIEW_NORMAL) {
                EmoNormalGridView normal = new EmoNormalGridView(context);
                normal.initData(entity.startResId, entity.pagerIndex, listener);
                container.addView(normal);
                return normal;
            } else {
                EmoCustomGridView custom = new EmoCustomGridView(context);
                custom.initData(entity.startResId, entity.pagerIndex, listener);
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

