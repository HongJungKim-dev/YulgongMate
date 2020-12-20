package kr.ac.konkuk.yulgongmate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CustomViewHolder> {

    private ArrayList<CalendarData> arrayList;

    public CalendarAdapter(ArrayList<CalendarData> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CalendarAdapter.CustomViewHolder holder, int position) {
        holder.et_schedule_title.setText(arrayList.get(position).getEt_schedule_title());
        holder.et_date.setText(arrayList.get(position).getEt_date());
        holder.et_time.setText(arrayList.get(position).getEt_time());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });
    }

    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position); //새로고침
        }catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected EditText et_schedule_title;
        protected EditText et_date;
        protected EditText et_time;
        protected Button btn_mod;
        protected Button btn_del;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.et_schedule_title = (EditText) itemView.findViewById(R.id.et_schedule_title);
            this.et_date = (EditText) itemView.findViewById(R.id.et_date);
            this.et_time = (EditText) itemView.findViewById(R.id.et_time);

        }
    }
}
