package kr.ac.konkuk.yulgongmate;

import java.util.ArrayList;

public class FamousSayingCategoryData {
    private String tv_category;
    ArrayList<FamousSayingItemData> itemDataList;

    public FamousSayingCategoryData(String tv_category, ArrayList<FamousSayingItemData> itemDataList) {
        this.tv_category = tv_category;
        this.itemDataList = itemDataList;
    }

    public ArrayList<FamousSayingItemData> getItemDataList() {
        return itemDataList;
    }

    public void setItemDataList(ArrayList<FamousSayingItemData> itemDataList) {
        this.itemDataList = itemDataList;
    }

    public String getTv_category() {
        return tv_category;
    }

    public void setTv_category(String tv_category) {
        this.tv_category = tv_category;
    }
}
