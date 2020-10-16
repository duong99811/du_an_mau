package com.e.du_an_mau.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.e.du_an_mau.R;

public class HomeActivity extends AppCompatActivity {
    ImageView imgNguoiDung, imgTheLoai, imgHoaDon, imgSach, imgSachBanChay, imgThongKe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgNguoiDung = findViewById(R.id.imgNguoiDung);
        imgTheLoai = findViewById(R.id.imgTheLoai);
        imgHoaDon = findViewById(R.id.imgHoaDon);
        imgSach = findViewById(R.id.imgSach);
        imgSachBanChay = findViewById(R.id.imgSachBanChay);
        imgThongKe = findViewById(R.id.imgThongKe);

        //set on click
        imgNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, QLNguoiDungActivity.class));
            }
        });
        imgTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, QLTheLoaiActivity.class));

            }
        });
        imgHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, QlHoaDonActivity.class));

            }
        });
        imgSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, QLSachActivity.class));

            }
        });
        imgSachBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, QLSachBanChayActivity.class));

            }
        });
        imgThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, QLThongKeActivity.class));

            }
        });

    }
}