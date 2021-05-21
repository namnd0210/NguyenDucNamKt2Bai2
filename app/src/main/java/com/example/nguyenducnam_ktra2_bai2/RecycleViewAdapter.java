package com.example.nguyenducnam_ktra2_bai2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    Context context;
    List<KhoaHoc> list = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.khoa_hoc_item, parent, false);

        context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvDate.setText(list.get(position).getDate());
        holder.tvChuyenNganh.setText(list.get(position).getChuyenNganh());
        holder.tvActive.setText(list.get(position).isActive().equals("1") ? "Active" : "Deactive");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailKhoaHoc.class);
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("name", list.get(position).getName());
                intent.putExtra("date", list.get(position).getDate());
                intent.putExtra("chuyenNganh", list.get(position).getChuyenNganh());
                intent.putExtra("active", list.get(position).isActive());
                System.out.println(list.get(position).toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvChuyenNganh, tvActive;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvChuyenNganh = itemView.findViewById(R.id.tvChuyenNganh);
            tvActive = itemView.findViewById(R.id.tvActive);
        }
    }
}
