package com.e.du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.du_an_mau.R;
import com.e.du_an_mau.model.NguoiDung;

import java.util.List;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.NguoiDungHolder> {
    private List<NguoiDung> nguoiDungList;
    private OnItemClickListener onItemClickListener;

    public NguoiDungAdapter(List<NguoiDung> nguoiDungList, OnItemClickListener onItemClickListener) {
        this.nguoiDungList = nguoiDungList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NguoiDungHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NguoiDungHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_nguoi_dung, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NguoiDungHolder holder, int position) {
        holder.bindItem(position);
    }

    @Override
    public int getItemCount() {
        return nguoiDungList.size();
    }

    public void submitList(List<NguoiDung> nguoiDungList) {
        this.nguoiDungList.clear();
        this.nguoiDungList.addAll(nguoiDungList);
        notifyDataSetChanged();
    }

    public class NguoiDungHolder extends RecyclerView.ViewHolder {
        TextView tvNameUserName;
        TextView tvName;
        ImageView imgDelNguoiDung;

        public NguoiDungHolder(@NonNull View itemView) {
            super(itemView);
            tvNameUserName = itemView.findViewById(R.id.tvUsername);
            tvName = itemView.findViewById(R.id.tvName);
            imgDelNguoiDung = itemView.findViewById(R.id.imgDelNguoiDung);
        }

        public void bindItem(final int position) {
            final NguoiDung nguoiDung = nguoiDungList.get(position);
            tvNameUserName.setText(nguoiDung.username);
            tvName.setText(nguoiDung.ten);
            imgDelNguoiDung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemRemoveListener(getLayoutPosition(), nguoiDung);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(getLayoutPosition(),nguoiDung);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemRemoveListener(int position, NguoiDung nguoiDung);
        void onItemClickListener(int position, NguoiDung nguoiDung);
    }
}
