package com.example.week3daily4homework;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    List<RepoResponse> list = new ArrayList<>();

    public RecyclerViewAdapter(List<RepoResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        RepoResponse response = list.get(position);
        if(response != null){
            String field1 = response.getName();
            String field2 = response.getCommitsUrl();
            String field3 = response.getUrl();

            viewHolder.setItemResult(response);
            viewHolder.tvField1.setText(field1);
            viewHolder.tvField2.setText(field2);
            viewHolder.tvField3.setText(field3);
        }
    }

    public void addRepo(RepoResponse result){
        list.add(result);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RepoResponse itemResult;
        TextView tvField1, tvField2, tvField3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvField1 = itemView.findViewById(R.id.tvField1);
            tvField2 = itemView.findViewById(R.id.tvField2);
            tvField3 = itemView.findViewById(R.id.tvField3);
        }

        public void setItemResult(RepoResponse itemResult){
            this.itemResult = itemResult;
        }
    }
}
