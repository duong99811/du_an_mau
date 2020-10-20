package com.e.du_an_mau.Ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.e.du_an_mau.Adapter.HoaDonChiTietAdapter;
import com.e.du_an_mau.R;
import com.e.du_an_mau.model.HoaDon;
import com.e.du_an_mau.model.HoaDonChiTiet;
import com.e.du_an_mau.model.Sach;
import com.e.du_an_mau.sqLite.MySqlite;
import com.e.du_an_mau.sqLite.SachDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QlHoaDonActivity extends AppCompatActivity implements HoaDonChiTietAdapter.OnItemClickListener {
    private EditText edMaHDCT;
    private EditText edNgayMua;
    private Spinner spnTenMauGiay;
    private EditText edSoluongMua;
    private Button btnThemGioHang;
    private Button btnXemHoaDon;
    private RecyclerView rvNguoiDung;

    MySqlite mySqlite;
    SachDAO sachDAO;
    Sach sach;
    HoaDon hoaDon;
    HoaDonChiTiet hoaDonChiTiet;
    List<Sach> sachList;
    List<HoaDonChiTiet> hoaDonChiTiets;
    String tensach;
    HoaDonChiTietAdapter hoaDonChiTietAdapter;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_hoa_don);
        init();

        hoaDonChiTietAdapter = new HoaDonChiTietAdapter(hoaDonChiTiets, this);
        rvNguoiDung.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        rvNguoiDung.setAdapter(hoaDonChiTietAdapter);
        getTenSach();
        spnTenMauGiay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tensach = sachList.get(spnTenMauGiay.getSelectedItemPosition()).tensach;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edMaHDCT.getText().toString().isEmpty()) {
                    edMaHDCT.setError("Vui lòng nhập thông tin!");
                } else if (edSoluongMua.getText().toString().isEmpty()) {
                    edSoluongMua.setError("Vui lòng nhập thông tin!");
                } else {
                    if (hoaDonChiTiets.size() > 0) {
                        sach = new Sach();
                        int index = -1;
                        for (int i = 0; i < hoaDonChiTiets.size(); i++) {
                            if (sach.tensach.equals(hoaDonChiTiets.get(i).sach.tensach)) {
                                index = i;
                                break;
                            }
                        }
                        if (index >= 0) {
                            int tongSoLuongSach = Integer.parseInt(hoaDonChiTiets.get(index).sach.soluong);
                            if ((Integer.parseInt(hoaDonChiTiets.get(index).soluongmua) + Integer.parseInt(edSoluongMua.getText().toString())) > tongSoLuongSach) {
                                Toast.makeText(QlHoaDonActivity.this, "Số lượng sách tối đa bạn có thể mua là: " + (tongSoLuongSach - Integer.parseInt(hoaDonChiTiets.get(index).sach.soluong) + ""), Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                hoaDonChiTiet = hoaDonChiTiets.get(index);
                                String soLuong = hoaDonChiTiet.soluongmua + Integer.parseInt(edSoluongMua.getText().toString());
                                HoaDonChiTiet hoaDonChiTiet1 = new HoaDonChiTiet(hoaDonChiTiet.hoaDon, tensach, soLuong);
                                hoaDonChiTiets.set(index, hoaDonChiTiet1);
                                hoaDonChiTietAdapter.notifyDataSetChanged();
                            }
                        } else {
                            themgiohang();
                        }
                    } else {
                        themgiohang();
                    }
                }
            }});
    }

    public void getTenSach() {
        sachDAO = new SachDAO(mySqlite);
        sachList = sachDAO.getAllSach();
        ArrayAdapter<Sach> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sachList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTenMauGiay.setAdapter(arrayAdapter);
    }

    public void init() {
        edMaHDCT = findViewById(R.id.edMaHDCT);
        edNgayMua = findViewById(R.id.edNgayMua);
        spnTenMauGiay = findViewById(R.id.spnTenMauGiay);
        edSoluongMua = findViewById(R.id.edSoluongMua);
        btnThemGioHang = findViewById(R.id.btnThemGioHang);
        btnXemHoaDon = findViewById(R.id.btnXemHoaDon);
        rvNguoiDung = findViewById(R.id.rvNguoiDung);
        mySqlite = new MySqlite(this);
        sachDAO = new SachDAO(mySqlite);
    }

    private void themgiohang() {
        String maHoaDon = edMaHDCT.getText().toString();
        edMaHDCT.setEnabled(false);
        edNgayMua.setEnabled(false);
        String soLuong = edSoluongMua.getText().toString();

        if (Integer.parseInt(soLuong) > Integer.parseInt(sach.soluong)) {
            Toast.makeText(this, "Số lượng sách còn lại trong kho là :" + sach.soluong, Toast.LENGTH_SHORT).show();
            return;
        }
        hoaDon = new HoaDon(maHoaDon, Calendar.getInstance().getTime());
        hoaDonChiTiet = new HoaDonChiTiet(hoaDon, tensach, soLuong);
        hoaDonChiTiets.add(hoaDonChiTiet);
        hoaDonChiTietAdapter = new HoaDonChiTietAdapter(hoaDonChiTiets, this);
        rvNguoiDung.setAdapter(hoaDonChiTietAdapter);
        hoaDonChiTietAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Date date = Calendar.getInstance().getTime();
        edNgayMua.setText(simpleDateFormat.format(date));
        edNgayMua.setEnabled(false);
    }

    @Override
    public void onItemRemoveListener(int position, HoaDonChiTiet hoaDonChiTiet) {

    }

    @Override
    public void onItemClickListener(int position, HoaDonChiTiet hoaDonChiTiet) {

    }
}