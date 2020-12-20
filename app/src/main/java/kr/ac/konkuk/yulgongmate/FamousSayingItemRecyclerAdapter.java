package kr.ac.konkuk.yulgongmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FamousSayingItemRecyclerAdapter extends RecyclerView.Adapter<FamousSayingItemRecyclerAdapter.ItemRecyclerViewHolder> {

    private Context context;
    private ArrayList<FamousSayingItemData> itemDataList;

    public FamousSayingItemRecyclerAdapter(Context context, ArrayList<FamousSayingItemData> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public ItemRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.famous_saying_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRecyclerViewHolder holder, int position) {
        holder.tv_famous_saying.setText(itemDataList.get(position).getTv_famous_saying());
    }

    @Override
    public int getItemCount() {
        return (null != itemDataList ? itemDataList.size() : 0);
    }

    public class ItemRecyclerViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_famous_saying;
        public ItemRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_famous_saying = (TextView) itemView.findViewById(R.id.tv_famous_saying);
        }
    }
}
