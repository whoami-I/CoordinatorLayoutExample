package com.mike.coordinatorlayoutexample.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mike.coordinatorlayoutexample.R;
import com.mike.coordinatorlayoutexample.main.ColorGenerator;

import java.util.List;

public abstract class BaseRecyAdapter extends RecyclerView.Adapter<BaseRecyAdapter.ViewHolder> {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;

    public BaseRecyAdapter(RecyclerView recyclerView, List<String> datas) {
        mRecyclerView = recyclerView;
        mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_simple_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = mDatas.get(position);
        holder.tv_text.setText(text);
        holder.itemView.setBackgroundColor(ColorGenerator.getInstance().getColor());
        holder.itemView.setOnClickListener(setItemClickListener(holder, position));
        holder.itemView.setOnLongClickListener(setItemLongClickListener(holder, position));
    }

    public abstract View.OnClickListener setItemClickListener(ViewHolder holder, int position);

    public abstract View.OnLongClickListener setItemLongClickListener(ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_text = itemView.findViewById(R.id.tv_item_name);
        }
    }
}
