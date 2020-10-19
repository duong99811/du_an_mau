package com.e.du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.du_an_mau.R;
import com.e.du_an_mau.model.Sach;

import java.util.List;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachHolder> {
    private List<Sach> sachList;
    private OnItemClickListener onItemClickListener;

    public SachAdapter(List<Sach> sachList, OnItemClickListener onItemClickListener) {
        this.sachList = sachList;
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public SachHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SachHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_sach, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SachHolder holder, int position) {
        holder.bindItem(position);
    }

    @Override
    public int getItemCount() {
        return sachList.size();
    }

    public void submitList(List<Sach> sachList) {
        this.sachList.clear();
        this.sachList.addAll(sachList);
        notifyDataSetChanged();
    }

    public class SachHolder extends RecyclerView.ViewHolder {
        ImageView imgDelSach;
        TextView tvTenSach;
        TextView tvMaSach;
        TextView tvSoLuongSach;
        TextView tvGiabanSach;

        public SachHolder(@NonNull View itemView) {
            super(itemView);
            imgDelSach = itemView.findViewById(R.id.imgDelSach);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvSoLuongSach = itemView.findViewById(R.id.tvSoLuongSach);
            tvGiabanSach = itemView.findViewById(R.id.tvGiabanSach);
        }

        public void bindItem(int position) {
            final Sach sach = sachList.get(position);
            tvTenSach.setText(sach.tensach);
            tvMaSach.setText(sach.masach);
            tvSoLuongSach.setText(sach.soluong);
            tvGiabanSach.setText(sach.giabia);
            imgDelSach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemRemoveListener(getLayoutPosition(), sach);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(getLayoutPosition(), sach);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemRemoveListener(int position, Sach sach);

        void onItemClickListener(int position, Sach sach);
    }
}
