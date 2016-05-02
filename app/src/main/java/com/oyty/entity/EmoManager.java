package com.oyty.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oyty on 5/2/16.
 */
public class EmoManager {

    public static final int TYPE_GRID_VIEW_NORMAL = 0x01;
    public static final int TYPE_GRID_VIEW_CUSTOM = 0x02;

    private static EmoManager mInstance;


    private List<EmoEntity> emoEntities;
    private List<EmoPager> emoPagerEntities;
    private List<EmoGroup> emoGroupEntities;

    private EmoManager() {
    }

    public static EmoManager getInstance() {
        if(mInstance == null) {
            synchronized (EmoManager.class) {
                if(mInstance == null) {
                    mInstance = new EmoManager();
                }
            }
        }
        return mInstance;
    }

    public void initData() {
        EmoEntity gridEntity = EmoEntity.getDefaultGridEntity();
        EmoEntity customEntity = EmoEntity.getDefaultCustomEntity();
        emoEntities = new ArrayList<>();
        emoEntities.add(gridEntity);
        emoEntities.add(customEntity);

        initEntities();
    }
    private void initEntities() {
        emoPagerEntities = new ArrayList<>();
        emoGroupEntities = new ArrayList<>();
        int startIndex = 0;
        for(int i = 0; i< emoEntities.size(); i++) {
            EmoEntity entity = emoEntities.get(i);

            EmoGroup changeEntity = new EmoGroup();
            changeEntity.groupIndex = i;
            changeEntity.pagerStartIndex = startIndex;
            emoGroupEntities.add(changeEntity);
            startIndex += entity.pageCount;

            for(int j=0; j<entity.pageCount; j++) {
                EmoPager pagerEntity = new EmoPager();
                pagerEntity.type = entity.isGridType() ? TYPE_GRID_VIEW_NORMAL : TYPE_GRID_VIEW_CUSTOM;
                pagerEntity.startResId = entity.index;
                pagerEntity.pagerCount = entity.pageCount;
                pagerEntity.pagerIndex = j;
                pagerEntity.groupIndex = i;
                emoPagerEntities.add(pagerEntity);
            }
        }
    }

    public List<EmoEntity> getEmoEntities() {
        return emoEntities;
    }

    public List<EmoPager> getEmoPagerEntities() {
        return emoPagerEntities;
    }

    public List<EmoGroup> getEmoGroupEntities() {
        return emoGroupEntities;
    }

    public int getTotalPagerCount() {
        int totalCount = 0;
        for(EmoEntity entity : emoEntities) {
            totalCount += entity.pageCount;
        }
        return totalCount;
    }
}
