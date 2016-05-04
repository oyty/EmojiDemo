package com.oyty.emoji;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.oyty.R;

/**
 * Created by oyty on 4/30/16.
 */
public class EmoNormalGridView extends GridView implements AdapterView.OnItemClickListener {
    private Context context;

    private OnEmoClickListener listener;
    private int startResId;
    private EmoAdapter adapter;
    private int curPagerIndex;

    public EmoNormalGridView(Context context) {
        super(context);
        initView(context);
    }

    public EmoNormalGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EmoNormalGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        setNumColumns(7);
        setPadding(10, 30, 10, 20);
        setOnItemClickListener(this);
        adapter = new EmoAdapter();
        setAdapter(adapter);
    }

    public void initData(int initResId, int curPagerIndex, OnEmoClickListener listener) {
        this.listener = listener;
        this.curPagerIndex = curPagerIndex;
        startResId = curPagerIndex * 20 + initResId;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if(position != 20) {
            listener.onNormalEmoClick(startResId + position);
        } else {
            listener.onDeleteBtnClick();
        }
    }

    class EmoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 21;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView image = new ImageView(context);
            image.setPadding(0, 15, 0, 15);
            if(position != 20) {
                image.setImageResource(startResId + position);
            } else {
                image.setImageResource(R.drawable.del_btn_nor);
            }
            return image;
        }
    }
}
