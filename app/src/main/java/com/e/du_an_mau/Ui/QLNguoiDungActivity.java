package com.e.du_an_mau.Ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.e.du_an_mau.Adapter.NguoiDungAdapter;
import com.e.du_an_mau.R;
import com.e.du_an_mau.model.NguoiDung;
import com.e.du_an_mau.sqLite.MySqlite;
import com.e.du_an_mau.sqLite.UserDAO;

import java.util.List;

public class QLNguoiDungActivity extends AppCompatActivity implements NguoiDungAdapter.OnItemClickListener {
    private MySqlite mySqlite;
    private RecyclerView recyclerView;
    private NguoiDungAdapter nguoiDungAdapter;
    private UserDAO userDAO;
    private List<NguoiDung> nguoiDungList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_nguoi_dung);
        mySqlite = new MySqlite(this);
        userDAO = new UserDAO(mySqlite);
        recyclerView = findViewById(R.id.rvNguoiDung);
        nguoiDungList = userDAO.getAllUser();
        nguoiDungAdapter = new NguoiDungAdapter(nguoiDungList, this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(nguoiDungAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //addUser
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_user, null);
            final EditText edAddUserName = view.findViewById(R.id.edAddUserName);
            final EditText edAddPassWord = view.findViewById(R.id.edAddPassWord);
            final EditText edAddPhone = view.findViewById(R.id.edAddPhone);
            final EditText edAddName = view.findViewById(R.id.edAddName);
            Button btnAddSave = view.findViewById(R.id.btnAddSave);
            Button btnAddCancel = view.findViewById(R.id.btnAddCancel);
            builder.setView(view);
            final AlertDialog alertDialog = builder.show();

            btnAddSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.username = edAddUserName.getText().toString().trim();
                    nguoiDung.password = edAddPassWord.getText().toString().trim();
                    nguoiDung.sdt = edAddPhone.getText().toString().trim();
                    nguoiDung.ten = edAddName.getText().toString().trim();

                    if (edAddUserName.getText().toString().trim().isEmpty()) {
                        edAddUserName.setError("Nhập đủ thông tin...");
                        return;
                    } else if (edAddPassWord.getText().toString().trim().isEmpty()) {
                        edAddPassWord.setError("Nhập đủ thông tin...");
                        return;
                    } else if (edAddPhone.getText().toString().trim().isEmpty()) {
                        edAddPhone.setError("Nhập đủ thông tin...");
                        return;
                    } else if (edAddName.getText().toString().trim().isEmpty()) {
                        edAddName.setError("Nhập đủ thông tin...");
                        return;
                    } else {
                        boolean ketQua = userDAO.addUser(nguoiDung);
                        if (ketQua) {
                            Toast.makeText(QLNguoiDungActivity.this,
                                    "Thành công!", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            List<NguoiDung> nguoiDungList = userDAO.getAllUser();
                            nguoiDungAdapter.submitList(nguoiDungList);
                        } else {
                            Toast.makeText(QLNguoiDungActivity.this,
                                    "Không thành công!", Toast.LENGTH_SHORT).show();
                        }
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

    @Override
    public void onItemRemoveListener(final int position, final NguoiDung nguoiDung) {
        AlertDialog.Builder builder = new AlertDialog.Builder(QLNguoiDungActivity.this);
        View view = LayoutInflater.from(QLNguoiDungActivity.this).inflate(R.layout.dialog_delete, null);
        Button btnOK = view.findViewById(R.id.btnDialogDelete);
        Button btnCancel = view.findViewById(R.id.btnDialogCancel);
        builder.setView(view);
        final AlertDialog alertDialog = builder.show();
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean status = userDAO.deleteUser(nguoiDung.username);
                if (status) {
                    nguoiDungList.remove(position);
                    nguoiDungAdapter.notifyItemRemoved(position);
                    Toast.makeText(QLNguoiDungActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(QLNguoiDungActivity.this, "Không thể xóa được", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    //update
    @Override
    public void onItemClickListener(int position, NguoiDung nguoiDung) {
        AlertDialog.Builder builder = new AlertDialog.Builder(QLNguoiDungActivity.this);
        View view = LayoutInflater.from(QLNguoiDungActivity.this).inflate(R.layout.dialog_edit_user, null);
        final EditText edEditUserName = view.findViewById(R.id.edEditUserName);
        final EditText edEditPassWord = view.findViewById(R.id.edEditPassWord);
        final EditText edEditPhone = view.findViewById(R.id.edEditPhone);
        final EditText edEditName = view.findViewById(R.id.edEditName);
        Button btnEditSave = view.findViewById(R.id.btnEditSave);
        Button btnEditCancel = view.findViewById(R.id.btnEditCancel);
        edEditUserName.setText(nguoiDung.username);
        edEditPassWord.setText(nguoiDung.password);
        edEditPhone.setText(nguoiDung.sdt);
        edEditName.setText(nguoiDung.ten);
        edEditUserName.setEnabled(false);
        builder.setView(view);
        final AlertDialog alertDialog = builder.show();
        btnEditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.username = edEditUserName.getText().toString().trim();
                nguoiDung.password = edEditPassWord.getText().toString().trim();
                nguoiDung.sdt = edEditPhone.getText().toString().trim();
                nguoiDung.ten = edEditName.getText().toString().trim();

                if (edEditUserName.getText().toString().trim().isEmpty()) {
                    edEditUserName.setError("Nhập đủ thông tin...");
                    return;
                } else if (edEditPassWord.getText().toString().trim().isEmpty()) {
                    edEditPassWord.setError("Nhập đủ thông tin...");
                    return;

                } else if (edEditPhone.getText().toString().trim().isEmpty()) {
                    edEditPhone.setError("Nhập đủ thông tin...");
                    return;

                } else if (edEditName.getText().toString().trim().isEmpty()) {
                    edEditName.setError("Nhập đủ thông tin...");
                    return;

                } else {
                    boolean ketQua = userDAO.updateUser(nguoiDung);
                    if (ketQua) {
                        Toast.makeText(QLNguoiDungActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        nguoiDungList = userDAO.getAllUser();
                        nguoiDungAdapter.submitList(nguoiDungList);
                    } else {
                        Toast.makeText(QLNguoiDungActivity.this,
                                "Không thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnEditCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}