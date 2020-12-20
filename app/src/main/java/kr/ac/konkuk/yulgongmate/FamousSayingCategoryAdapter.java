package kr.ac.konkuk.yulgongmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FamousSayingCategoryAdapter extends RecyclerView.Adapter<FamousSayingCategoryAdapter.CategoryHolder> {

    private Context context;
    private ArrayList<FamousSayingCategoryData> arrayList;

    public FamousSayingCategoryAdapter(Context context, ArrayList<FamousSayingCategoryData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryHolder(LayoutInflater.from(context).inflate(R.layout.famous_saying_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.tv_category.setText(arrayList.get(position).getTv_category());
        setCatItemRecycler(holder.rv_category, arrayList.get(position).getItemDataList());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        TextView tv_category;
        RecyclerView rv_category;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            tv_category = itemView.findViewById(R.id.tv_category);
            rv_category = itemView.findViewById(R.id.rv_category);
        }
    }
    private void setCatItemRecycler(RecyclerView recyclerView, ArrayList<FamousSayingItemData> itemDataList){

        FamousSayingItemRecyclerAdapter itemRecyclerAdapter= new FamousSayingItemRecyclerAdapter(context, itemDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(itemRecyclerAdapter);

    }
}
