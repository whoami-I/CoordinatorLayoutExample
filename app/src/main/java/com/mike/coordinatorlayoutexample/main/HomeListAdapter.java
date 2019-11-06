package com.mike.coordinatorlayoutexample.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mike.coordinatorlayoutexample.R;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemDataBean> mDataList;
    private Context mContext;

    public HomeListAdapter(MainActivity activity, List<ItemDataBean> dataList) {
        mDataList = dataList;
        mContext = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_home_list, parent, false);
        return new HomeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeListViewHolder) {
            HomeListViewHolder holder1 = (HomeListViewHolder) holder;
            holder1.tvItemName.setText(mDataList.get(position).getItemName());
            holder1.itemView.setBackgroundColor(mDataList.get(position).getColor());
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, mDataList.get(position).getClazz());
                    mContext.startActivity(intent);
                }
            });
        } else {
            throw new RuntimeException("holder is not instanceof HomeListViewHolder");
        }
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class HomeListViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemName;

        public HomeListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tv_item_name);
        }
    }
}
