package kr.ac.konkuk.yulgongmate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FamousSayingActivity extends AppCompatActivity{
    RecyclerView mainCategoryRecycler;
    FamousSayingCategoryAdapter mainRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.famous_saying);

        ArrayList<FamousSayingItemData> itemDataList = new ArrayList<>();
        itemDataList.add(new FamousSayingItemData("오랫동안 꿈을 그리는 사람은 마침내 그 꿈을 닮아 간다"));
        itemDataList.add(new FamousSayingItemData("오랫동안 꿈을 그리는 사람은 마침내 그 꿈을 닮아 간다"));
        itemDataList.add(new FamousSayingItemData("오랫동안 꿈을 그리는 사람은 마침내 그 꿈을 닮아 간다"));
        itemDataList.add(new FamousSayingItemData("오랫동안 꿈을 그리는 사람은 마침내 그 꿈을 닮아 간다"));
        itemDataList.add(new FamousSayingItemData("오랫동안 꿈을 그리는 사람은 마침내 그 꿈을 닮아 간다"));

        ArrayList<FamousSayingItemData> itemDataList2 = new ArrayList<>();
        itemDataList2.add(new FamousSayingItemData("삶이 있는 한 희망은 있다"));
        itemDataList2.add(new FamousSayingItemData("삶이 있는 한 희망은 있다"));
        itemDataList2.add(new FamousSayingItemData("삶이 있는 한 희망은 있다"));
        itemDataList2.add(new FamousSayingItemData("삶이 있는 한 희망은 있다"));
        itemDataList2.add(new FamousSayingItemData("삶이 있는 한 희망은 있다"));

        ArrayList<FamousSayingItemData> itemDataList3= new ArrayList<>();
        itemDataList3.add(new FamousSayingItemData("이 또한 지나가리라"));
        itemDataList3.add(new FamousSayingItemData("이 또한 지나가리라"));
        itemDataList3.add(new FamousSayingItemData("이 또한 지나가리라"));
        itemDataList3.add(new FamousSayingItemData("이 또한 지나가리라"));
        itemDataList3.add(new FamousSayingItemData("이 또한 지나가리라"));

        ArrayList<FamousSayingItemData> itemDataList4 = new ArrayList<>();
        itemDataList4.add(new FamousSayingItemData("산다는것 그것은 치열한 전투이다"));
        itemDataList4.add(new FamousSayingItemData("산다는것 그것은 치열한 전투이다"));
        itemDataList4.add(new FamousSayingItemData("산다는것 그것은 치열한 전투이다"));
        itemDataList4.add(new FamousSayingItemData("산다는것 그것은 치열한 전투이다"));
        itemDataList4.add(new FamousSayingItemData("산다는것 그것은 치열한 전투이다"));

        ArrayList<FamousSayingItemData> itemDataList5 = new ArrayList<>();
        itemDataList5.add(new FamousSayingItemData("직업에서 행복을 찾아라. 아니면 행복이 무엇인지 절대 모를 것이다"));
        itemDataList5.add(new FamousSayingItemData("직업에서 행복을 찾아라. 아니면 행복이 무엇인지 절대 모를 것이다"));
        itemDataList5.add(new FamousSayingItemData("직업에서 행복을 찾아라. 아니면 행복이 무엇인지 절대 모를 것이다"));
        itemDataList5.add(new FamousSayingItemData("직업에서 행복을 찾아라. 아니면 행복이 무엇인지 절대 모를 것이다"));
        itemDataList5.add(new FamousSayingItemData("직업에서 행복을 찾아라. 아니면 행복이 무엇인지 절대 모를 것이다"));

        ArrayList<FamousSayingCategoryData> categoryDataList = new ArrayList<>();
        categoryDataList.add(new FamousSayingCategoryData("#꿈", itemDataList));
        categoryDataList.add(new FamousSayingCategoryData("#희망", itemDataList2));
        categoryDataList.add(new FamousSayingCategoryData("#위로", itemDataList3));
        categoryDataList.add(new FamousSayingCategoryData("#열정", itemDataList4));
        categoryDataList.add(new FamousSayingCategoryData("#행복", itemDataList5));

        setMainCategoryRecycler(categoryDataList);
    }
    private void setMainCategoryRecycler(ArrayList<FamousSayingCategoryData> categoryDataList){
        mainCategoryRecycler = (RecyclerView) findViewById(R.id.rv_famous_saying);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mainCategoryRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new FamousSayingCategoryAdapter(this, categoryDataList);
        mainCategoryRecycler.setAdapter(mainRecyclerAdapter);

        mainCategoryRecycler.addItemDecoration(new FamousSayingRecyclerViewDecoration(10, 60)); //카테고리 사이에 간격 설정
    }
}
