package com.e.du_an_mau.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.du_an_mau.model.HoaDon;

import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonHolder> {
    private List<HoaDon> hoaDonList;

    @NonNull
    @Override
    public HoaDonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }

    public class HoaDonHolder extends RecyclerView.ViewHolder {
        public HoaDonHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
