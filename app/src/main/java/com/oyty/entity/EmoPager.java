package com.oyty.entity;

/**
 * Created by oyty on 5/1/16.
 * 表情每一个pager页面的实体
 */
public class EmoPager {
    /** 表情的类型，grid或custom */
    public int type;
    /**  起始表情的资源id*/
    public int startResId;

    /** 对应的pager数量 */
    public int pagerCount;

    /** 当前的pager index */
    public int pagerIndex;

    /** 当前的pager对应的group index */
    public int groupIndex;
}
