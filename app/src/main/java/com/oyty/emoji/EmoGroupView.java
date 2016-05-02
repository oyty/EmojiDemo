package com.oyty.emoji;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.oyty.entity.EmoEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by oyty on 4/30/16.
 */
public class EmoGroupView extends HorizontalScrollView implements View.OnClickListener {

    private Context context;

    public static final int STATE_CLICK_CHANGE = 0x01;
    public static final int STATE_PAGER_CHANGE = 0x02;

    private int state = STATE_CLICK_CHANGE;

    private RadioGroup mRadioGroup;
    private OnGroupChangeListener listener;
    private List<EmoEntity> entities;
    private List<RadioButton> radioButtons;

    public EmoGroupView(Context context) {
        super(context);
        initView(context);
    }

    public EmoGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EmoGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        mRadioGroup = new RadioGroup(context);
        mRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
        addView(mRadioGroup);

    }

    public void initData(OnGroupChangeListener listener, List<EmoEntity> entities) {
        this.listener = listener;
        this.entities = entities;
        radioButtons = new ArrayList<>();
        for(int i=0; i<entities.size(); i++) {
            EmoEntity entity = entities.get(i);
            RadioButton button = new RadioButton(context);
//            button.setButtonDrawable(new BitmapDrawable());
            button.setCompoundDrawablesWithIntrinsicBounds(0, entity.resId, 0, 0);
            if(i == 0) {
                button.setChecked(true);
            }
            button.setId(i);
            button.setOnClickListener(this);
            radioButtons.add(button);
            mRadioGroup.addView(button);
        }
    }

    public void notifyDataChanged(int groupIndex) {
        state = STATE_PAGER_CHANGE;
        radioButtons.get(groupIndex).setChecked(true);
    }

    @Override
    public void onClick(View view) {
        listener.onGroupChanged(view.getId());
        radioButtons.get(view.getId()).setChecked(true);
    }
}
