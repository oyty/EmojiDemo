package com.oyty.emoji;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by oyty on 4/30/16.
 */
public class EmoDotView extends View {

    private float density;
    private int width;
    private int position;
    private int count;
    private float radius;
    private float padding;
    private float mStartX;
    private Paint paint;

    public EmoDotView(Context context) {
        super(context);
        initView(context);
    }

    public EmoDotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EmoDotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        width = getResources().getDisplayMetrics().widthPixels;
        density = getResources().getDisplayMetrics().density;
        radius = 2 * density;
        padding = 10 * density;
        setPadding(10, 10, 10, 10);
        paint = new Paint();
    }

    public void notifyDataChanged(int curGroupPosition, int count) {
        this.position = curGroupPosition;
        this.count = count;
        mStartX = (width - (count * 2 * radius + (count - 1) * padding)) / 2;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0; i<count; i++) {
            if(position == i) {
                paint.setColor(Color.WHITE);
            } else {
                paint.setColor(Color.GRAY);
            }
            canvas.drawCircle(mStartX + i * padding + i * 2 * radius, radius, radius, paint);
        }
    }
}
