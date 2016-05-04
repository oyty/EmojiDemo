package com.oyty.emoji;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by oyty on 4/30/16.
 */
public class EmoCustomGridView extends GridView implements AdapterView.OnItemClickListener {
    private Context context;
    private int initResId;
    private int startResId;
    private EmoCustomAdapter adapter;

    public EmoCustomGridView(Context context) {
        super(context);
        initView(context);
    }

    public EmoCustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EmoCustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        setNumColumns(4);
        setOnItemClickListener(this);
        adapter = new EmoCustomAdapter();
        setAdapter(adapter);
    }

    public void initData(int initResId, int curPagerIndex, OnEmoClickListener listener) {
        this.initResId = initResId;
        startResId = initResId + curPagerIndex * 8;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    class EmoCustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ImageView image = new ImageView(context);
            image.setImageResource(position + startResId);
            return image;
        }
    }
}
