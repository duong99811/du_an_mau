package com.e.du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.e.du_an_mau.R;
import com.e.du_an_mau.model.NguoiDung;
import com.e.du_an_mau.sqLite.MySqlite;
import com.e.du_an_mau.sqLite.UserDAO;

import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    private  List<NguoiDung> nguoiDungList;

    public NguoiDungAdapter(List<NguoiDung> nguoiDungList) {
        this.nguoiDungList = nguoiDungList;
    }

    @Override
    public int getCount() {
        return nguoiDungList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view,final ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nguoi_dung,viewGroup,false);

        TextView tvNameUserName = view.findViewById(R.id.tvUserName);
        TextView tvName = view.findViewById(R.id.tvName);
        tvNameUserName.setText(nguoiDungList.get(i).username);
        tvName.setText(nguoiDungList.get(i).ten);
        
        view.findViewById(R.id.btnDel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDAO userDAO = new UserDAO(new MySqlite(viewGroup.getContext()));
                String username = nguoiDungList.get(i).username;
                boolean ketQua = userDAO.deleteUser(username);
                if (ketQua) {
                    Toast.makeText(viewGroup.getContext(), "Xóa thành công!",
                            Toast.LENGTH_SHORT).show();
                    nguoiDungList.remove(i);
                    notifyDataSetChanged();

                } else {
                    Toast.makeText(viewGroup.getContext(), "Lỗi khi xóa!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
