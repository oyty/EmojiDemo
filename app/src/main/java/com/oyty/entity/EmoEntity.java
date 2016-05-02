package com.oyty.entity;

import com.oyty.emoji.R;

/**
 * Created by oyty on 5/1/16.
 * 整个表情的实体类，表示一种表情类型
 */
public class EmoEntity {
    public int index;
    public int count;
    public int pageCount;
    public String url;
    public String name;
    public int resId;
    public String iconUrl;
    /**
     * 0 ：grid 3*7
     * 1 ：custom 2*4
     */
    private int type;

    public boolean isGridType() {
        return type == 0;
    }

    public static EmoEntity getDefaultGridEntity() {
        EmoEntity entity = new EmoEntity();
        entity.index = R.drawable.smiley_00;
        entity.count = 100;
        entity.pageCount = 5;
        entity.type = 0;
        entity.resId = R.drawable.emotionstore_emoji_icon;
        return entity;
    }

    public static EmoEntity getDefaultCustomEntity() {
        EmoEntity entity = new EmoEntity();
        entity.index = R.drawable.icon_000;
        entity.count = 16;
        entity.pageCount = 2;
        entity.type = 1;
        entity.resId = R.drawable.emotions_bagcover;
        return entity;
    }




}
