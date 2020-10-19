package com.e.du_an_mau.Ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.e.du_an_mau.R;
import com.e.du_an_mau.model.NguoiDung;
import com.e.du_an_mau.sqLite.MySqlite;
import com.e.du_an_mau.sqLite.UserDAO;

import java.util.List;

public class DangNhapActivity extends AppCompatActivity {
    List<NguoiDung> nguoiDungList;
    UserDAO userDAO;
    NguoiDung nguoiDung;
    MySqlite mySqlite;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText edTaiKhoan = findViewById(R.id.edTaiKhoan);
        final EditText edMatKhau = findViewById(R.id.edMatKhau);
        final CheckBox chkRemember = findViewById(R.id.chkRemember);
        Button btnDangNhap = findViewById(R.id.btnDangNhap);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edTaiKhoan.getText().toString().trim();
                password = edMatKhau.getText().toString().trim();
                if (username.isEmpty()) {
                    edTaiKhoan.setError("Không được để trống");
                } else if (password.isEmpty()) {
                    edMatKhau.setError("Không được để trống");
                } else {
//                    if (userDAO.checkLogin(username, password) > 0) {
//                     startActivity(new Intent(DangNhapActivity.this,HomeActivity.class));
//                    }
                    if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
                        rememberUser(username, password, chkRemember.isChecked());
                        startActivity(new Intent(DangNhapActivity.this, HomeActivity.class));
                    } else {
                        Toast.makeText(DangNhapActivity.this, "Tên đăng nhập và tài khoản không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void rememberUser(String user, String pass, boolean status) {
        SharedPreferences preferences = getSharedPreferences("USER", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("username", user);
            editor.putString("password", pass);
            editor.putBoolean("remember", status);
        }
        editor.commit();
    }

}