package com.e.du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.du_an_mau.R;
import com.e.du_an_mau.model.HoaDon;
import com.e.du_an_mau.model.NguoiDung;

import java.text.SimpleDateFormat;
import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonHolder> {
    private List<HoaDon> hoaDonList;
    private OnItemClickListener onItemClickListener;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public HoaDonAdapter(List<HoaDon> hoaDonList, OnItemClickListener onItemClickListener) {
        this.hoaDonList = hoaDonList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HoaDonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HoaDonHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cutom_hoa_don, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonHolder holder, int position) {
        holder.bindItem(position);
    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }

    public void submitList(List<HoaDon> hoaDonList) {
        this.hoaDonList.clear();
        this.hoaDonList.addAll(hoaDonList);
        notifyDataSetChanged();
    }

    public class HoaDonHolder extends RecyclerView.ViewHolder {
        private TextView tvmaHD;
        private TextView tvNgayMuaHD;
        private TextView tvThanhTienXHD;
        private ImageView imgDelHD;

        public HoaDonHolder(@NonNull View itemView) {
            super(itemView);
            tvmaHD = itemView.findViewById(R.id.tvmaHD);
            tvNgayMuaHD = itemView.findViewById(R.id.tvNgayMuaHD);
            tvThanhTienXHD = itemView.findViewById(R.id.tvThanhTienXHD);
            imgDelHD = itemView.findViewById(R.id.imgDelHD);
        }

        public void bindItem(int position) {
            final HoaDon hoaDon = hoaDonList.get(position);
            tvmaHD.setText(hoaDon.maHoaDon);
            tvNgayMuaHD.setText((CharSequence) hoaDon.ngayMua);

            imgDelHD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemRemoveListener(getLayoutPosition(), hoaDon);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(getLayoutPosition(),hoaDon);
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemRemoveListener(int position, HoaDon hoaDon);
        void onItemClickListener(int position, HoaDon hoaDon);
    }
}
