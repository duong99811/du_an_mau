package com.e.du_an_mau.Ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.e.du_an_mau.Adapter.HoaDonAdapter;
import com.e.du_an_mau.Adapter.HoaDonChiTietAdapter;
import com.e.du_an_mau.R;
import com.e.du_an_mau.model.HoaDon;
import com.e.du_an_mau.model.HoaDonChiTiet;
import com.e.du_an_mau.model.Sach;
import com.e.du_an_mau.sqLite.HoaDonChiTietDAO;
import com.e.du_an_mau.sqLite.HoaDonDAO;
import com.e.du_an_mau.sqLite.MySqlite;
import com.e.du_an_mau.sqLite.SachDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QlHoaDonActivity extends AppCompatActivity implements HoaDonAdapter.OnItemClickListener {
    MySqlite mySqlite;
    SachDAO sachDAO;
    HoaDonDAO hoaDonDAO;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    List<Sach> sachList;
    List<HoaDonChiTiet> hoaDonChiTietList;
    List<HoaDon> hoaDonList;
    HoaDonChiTietAdapter hoaDonChiTietAdapter;
    HoaDonAdapter hoaDonAdapter;
    Sach sach;
    HoaDon hoaDon;
    String masach;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    //init
    private EditText edSearch;
    private ImageView imgSearch;
    private RecyclerView rvHoaDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_hoa_don);
        init();
//        mySqlite = new MySqlite(this);
//        hoaDonDAO = new HoaDonDAO(mySqlite);
//        hoaDonList = hoaDonDAO.getAllHoaDon();
//        hoaDonAdapter = new HoaDonAdapter(hoaDonList, this);
//        rvHoaDon.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL));
//        rvHoaDon.setAdapter(hoaDonAdapter);
    }

    public void init() {
        edSearch = findViewById(R.id.edSearch);
        imgSearch = findViewById(R.id.imgSearch);
        rvHoaDon = findViewById(R.id.rvHoaDon);
        mySqlite = new MySqlite(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_hoa_don, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //add hóa đơn
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addHoaDon) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_hoa_don, null);
            final EditText edAddMaHD = view.findViewById(R.id.edAddMaHD);
            final EditText edAddNgayMua = view.findViewById(R.id.edAddNgayMua);
            final Spinner spnTenSach = view.findViewById(R.id.spnTenSach);
            final EditText edAddSoluongMua = view.findViewById(R.id.edAddSoluongMua);
            Button btnThemGioHang = view.findViewById(R.id.btnThemGioHang);
            Button btnThanhToan = view.findViewById(R.id.btnThanhToan);
            final RecyclerView rvHDCT = view.findViewById(R.id.rvHDCT);
            final TextView tvThanhTienXHD = view.findViewById(R.id.tvThanhTienXHD);

            Date date = Calendar.getInstance().getTime();
            edAddNgayMua.setText(simpleDateFormat.format(date));
            edAddNgayMua.setEnabled(false);
            //spner tên sách
            getTenSach(spnTenSach);
            spnTenSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    masach = sachList.get(spnTenSach.getSelectedItemPosition()).masach;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            builder.setView(view);
            final AlertDialog alertDialog = builder.show();
            btnThemGioHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String mahoadonCT = edAddMaHD.getText().toString().trim();
                    Date ngaymua = new Date();
                    int checksoluong = Integer.parseInt(sach.soluong);
                    String soluongmua = edAddSoluongMua.getText().toString().trim();
                    if (edAddMaHD.getText().toString().trim().isEmpty()) {
                        edAddMaHD.setError("Nhập đủ thông tin...");
                        return;
                    } else if (edAddSoluongMua.getText().toString().trim().isEmpty()) {
                        edAddSoluongMua.setError("Nhập đủ thông tin...");
                        return;
                    } else if (checksoluong > Integer.parseInt(soluongmua)) {
                        edAddSoluongMua.setError("Mua tối đa :" + (Integer.parseInt(sach.soluong) - Integer.parseInt(soluongmua)) + " quyển");
                        return;
                    } else {
                        hoaDon = new HoaDon(mahoadonCT, ngaymua);
                        sach = (Sach) sachDAO.getSachByMaSach(masach);
                        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(mahoadonCT, hoaDon, sach, soluongmua);
                        boolean ketQua = hoaDonChiTietDAO.addHDCT(hoaDonChiTiet);
                        if (ketQua) {
                            Toast.makeText(QlHoaDonActivity.this,
                                    "Thành công!", Toast.LENGTH_SHORT).show();
                            hoaDonChiTietList = hoaDonChiTietDAO.getAllHDCT();
                        } else {
                            Toast.makeText(QlHoaDonActivity.this,
                                    "Không thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemRemoveListener(int position, HoaDon hoaDon) {

    }

    @Override
    public void onItemClickListener(int position, HoaDon hoaDon) {

    }

    public void getTenSach(Spinner spinner) {
        sachDAO = new SachDAO(mySqlite);
        sachList = sachDAO.getAllSach();
        ArrayAdapter<Sach> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sachList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }


//    @Override
//    protected void onResume() {
//
//        super.onResume();
//        Date date = Calendar.getInstance().getTime();
////        edNgayMua.setText(simpleDateFormat.format(date));
////        edNgayMua.setEnabled(false);
//    }
}