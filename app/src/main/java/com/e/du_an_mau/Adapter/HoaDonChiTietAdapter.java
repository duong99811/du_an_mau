package com.e.du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.du_an_mau.R;
import com.e.du_an_mau.model.HoaDonChiTiet;

import java.util.List;

public class HoaDonChiTietAdapter extends RecyclerView.Adapter<HoaDonChiTietAdapter.HoaDonChiTietHolder> {
    private List<HoaDonChiTiet> hoaDonChiTietList;
    private OnItemClickListener onItemClickListener;

    public HoaDonChiTietAdapter(List<HoaDonChiTiet> hoaDonChiTietList, OnItemClickListener onItemClickListener) {
        this.hoaDonChiTietList = hoaDonChiTietList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HoaDonChiTietHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HoaDonChiTietHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_hoa_don, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonChiTietHolder holder, int position) {
        holder.bindItem(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void submitList(List<HoaDonChiTiet> hoaDonChiTiets) {
        this.hoaDonChiTietList.clear();
        this.hoaDonChiTietList.addAll(hoaDonChiTiets);
        notifyDataSetChanged();
    }

    public class HoaDonChiTietHolder extends RecyclerView.ViewHolder {
        TextView tvTenSachXHD;
        TextView tvSoLuongXHD;
        TextView tvGiabanXHD;
        TextView tvThanhTienXHD;


        public HoaDonChiTietHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSachXHD = itemView.findViewById(R.id.tvTenSachXHD);
            tvSoLuongXHD = itemView.findViewById(R.id.tvSoLuongXHD);
            tvGiabanXHD = itemView.findViewById(R.id.tvGiabanXHD);
            tvThanhTienXHD = itemView.findViewById(R.id.tvThanhTienXHD);
        }

        public void bindItem(int position) {
            final HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietList.get(position);
            
        }
    }

    public interface OnItemClickListener {
        void onItemRemoveListener(int position, HoaDonChiTiet hoaDonChiTiet);

        void onItemClickListener(int position, HoaDonChiTiet hoaDonChiTiet);
    }
}
