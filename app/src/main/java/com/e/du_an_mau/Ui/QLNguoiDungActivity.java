package com.e.du_an_mau.Ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.e.du_an_mau.Adapter.NguoiDungAdapter;
import com.e.du_an_mau.R;
import com.e.du_an_mau.model.NguoiDung;
import com.e.du_an_mau.sqLite.MySqlite;
import com.e.du_an_mau.sqLite.UserDAO;

import java.util.List;

public class QLNguoiDungActivity extends AppCompatActivity {
    private ListView listView;
    private MySqlite mySqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_nguoi_dung);
        mySqlite = new MySqlite(this);
        UserDAO userDAO = new UserDAO(mySqlite);
        listView =findViewById(R.id.lvNguoiDung);
        List<NguoiDung> nguoiDungList = userDAO.getAllUser();
        NguoiDungAdapter nguoiDungAdapter = new NguoiDungAdapter(nguoiDungList);
        listView.setAdapter(nguoiDungAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QLNguoiDungActivity.this);
                View view1 = LayoutInflater.from(QLNguoiDungActivity.this).inflate(R.layout.dialog_edit_user,null);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_user, null);

            builder.setView(view);
            final EditText edAddUserName = view.findViewById(R.id.edEditUserName);
            final EditText edAddPassWord = view.findViewById(R.id.edEditPassWord);
            final EditText edAddPhone = view.findViewById(R.id.edEditPhone);
            final EditText edAddName = view.findViewById(R.id.edEditName);
            Button btnAddSave = view.findViewById(R.id.btnEditSave);
            Button btnAddCancel = view.findViewById(R.id.btnEditCancel);

            final AlertDialog alertDialog = builder.show();
            btnAddSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.username = edAddUserName.getText().toString().trim();
                    nguoiDung.password = edAddPassWord.getText().toString().trim();
                    nguoiDung.sdt = edAddPhone.getText().toString().trim();
                    nguoiDung.ten = edAddName.getText().toString().trim();

                    checkEmpty(nguoiDung.username, edAddUserName);
                    checkEmpty(nguoiDung.password, edAddPassWord);
                    checkEmpty(nguoiDung.sdt, edAddPhone);
                    checkEmpty(nguoiDung.ten, edAddName);

                    UserDAO userDAO = new UserDAO(mySqlite);

                    boolean ketQua = userDAO.addUser(nguoiDung);
                    if (ketQua) {
                        Toast.makeText(QLNguoiDungActivity.this,
                                "Thành công!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        List<NguoiDung> nguoiDungList = userDAO.getAllUser();
                        NguoiDungAdapter nguoiDungAdapter = new NguoiDungAdapter(nguoiDungList);
                        listView.setAdapter(nguoiDungAdapter);
                    } else {
                        Toast.makeText(QLNguoiDungActivity.this,
                                "Không thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btnAddCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
    public void checkEmpty(String data, EditText edt) {
        if (data.isEmpty()) {
            edt.setError("Nhập đủ thông tin...");
            return;
        }
    }
}