package com.e.du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.du_an_mau.R;
import com.e.du_an_mau.model.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.TheLoaiHoder> {
    private List<TheLoai> theLoaiList;
    private OnItemClickListener onItemClickListener;

    public TheLoaiAdapter(List<TheLoai> theLoaiList, OnItemClickListener onItemClickListener) {
        this.theLoaiList = theLoaiList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public TheLoaiHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TheLoaiHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_the_loai, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiHoder holder, int position) {
        holder.bindItem(position);
    }

    @Override
    public int getItemCount() { return theLoaiList.size(); }

    public void submitList(List<TheLoai> theLoaiList) {
        this.theLoaiList.clear();
        this.theLoaiList.addAll(theLoaiList);
        notifyDataSetChanged();
    }

    public class TheLoaiHoder extends RecyclerView.ViewHolder {
        ImageView imgDelTheLoai;
        TextView tvTenTheLoai;
        TextView tvMaTheLoai;
        public TheLoaiHoder(@NonNull View itemView) {
            super(itemView);
            imgDelTheLoai = itemView.findViewById(R.id.imgDelTheLoai);
            tvTenTheLoai = itemView.findViewById(R.id.tvTenTheLoai);
            tvMaTheLoai = itemView.findViewById(R.id.tvMaTheLoai);
        }
        public void bindItem(int position) {
            final TheLoai theLoai = theLoaiList.get(position);
            tvTenTheLoai.setText(theLoai.tentheloai);
            tvMaTheLoai.setText(theLoai.matheloai);
            imgDelTheLoai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemRemoveListener(getLayoutPosition(),theLoai);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(getLayoutPosition(),theLoai);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemRemoveListener(int position, TheLoai theLoai);
        void onItemClickListener(int position, TheLoai theLoai);
    }
}
