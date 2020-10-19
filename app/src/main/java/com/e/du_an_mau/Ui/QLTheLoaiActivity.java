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

import com.e.du_an_mau.Adapter.TheLoaiAdapter;
import com.e.du_an_mau.R;
import com.e.du_an_mau.model.TheLoai;
import com.e.du_an_mau.sqLite.MySqlite;
import com.e.du_an_mau.sqLite.TheLoaiDAO;

import java.util.List;

public class QLTheLoaiActivity extends AppCompatActivity implements TheLoaiAdapter.OnItemClickListener {
    private MySqlite mySqlite;
    private RecyclerView recyclerView;
    private TheLoaiAdapter theLoaiAdapter;
    private TheLoaiDAO theLoaiDAO;
    private List<TheLoai> theLoaiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_the_loai);
        mySqlite = new MySqlite(this);
        theLoaiDAO = new TheLoaiDAO(mySqlite);
        recyclerView = findViewById(R.id.rvTheLoai);
        theLoaiList = theLoaiDAO.getAllTheLoai();
        theLoaiAdapter = new TheLoaiAdapter(theLoaiList, this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(theLoaiAdapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_the_loai, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //addTheLoai
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addTheLoai) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_the_loai, null);
            final EditText edAddMaTheLoai = view.findViewById(R.id.edAddMaTheLoai);
            final EditText edAddTenTheLoai = view.findViewById(R.id.edAddTenTheLoai);
            final EditText edAddVitri = view.findViewById(R.id.edAddVitri);
            final EditText edAddMota = view.findViewById(R.id.edAddMota);
            Button btnAddSaveTheLoai = view.findViewById(R.id.btnAddSaveTheLoai);
            Button btnAddCancelTheLoai = view.findViewById(R.id.btnAddCancelTheLoai);

            builder.setView(view);
            final AlertDialog alertDialog = builder.show();
            btnAddSaveTheLoai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TheLoai theLoai = new TheLoai();
                    theLoai.matheloai = edAddMaTheLoai.getText().toString().trim();
                    theLoai.tentheloai = edAddTenTheLoai.getText().toString().trim();
                    theLoai.vitri = edAddVitri.getText().toString().trim();
                    theLoai.mota = edAddMota.getText().toString().trim();

                    if (edAddMaTheLoai.getText().toString().trim().isEmpty()) {
                        edAddMaTheLoai.setError("Nhập đủ thông tin...");
                        return;
                    } else if (edAddTenTheLoai.getText().toString().trim().isEmpty()) {
                        edAddTenTheLoai.setError("Nhập đủ thông tin...");
                        return;
                    } else if (edAddVitri.getText().toString().trim().isEmpty()) {
                        edAddVitri.setError("Nhập đủ thông tin...");
                        return;
                    } else if (edAddMota.getText().toString().trim().isEmpty()) {
                        edAddMota.setError("Nhập đủ thông tin...");
                        return;
                    } else {
                        TheLoaiDAO theLoaiDAO = new TheLoaiDAO(mySqlite);
                        boolean ketQua = theLoaiDAO.addTheLoai(theLoai);
                        if (ketQua) {
                            Toast.makeText(QLTheLoaiActivity.this,
                                    "Thành công!", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            List<TheLoai> theLoaiList = theLoaiDAO.getAllTheLoai();
                            theLoaiAdapter.submitList(theLoaiList);

                        } else {
                            Toast.makeText(QLTheLoaiActivity.this,
                                    "Không thành công!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
            btnAddCancelTheLoai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });

        }
        return super.onOptionsItemSelected(item);
    }

    //deleteTheLoai
    @Override
    public void onItemRemoveListener(final int position,final TheLoai theLoai) {
        AlertDialog.Builder builder = new AlertDialog.Builder(QLTheLoaiActivity.this);
        View view = LayoutInflater.from(QLTheLoaiActivity.this).inflate(R.layout.dialog_delete, null);
        Button btnOK = view.findViewById(R.id.btnDialogDelete);
        Button btnCancel = view.findViewById(R.id.btnDialogCancel);
        builder.setView(view);
        final AlertDialog alertDialog = builder.show();
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean status = theLoaiDAO.deleteTheLoai(theLoai.matheloai);
                if (status) {
                    theLoaiList.remove(position);
                    theLoaiAdapter.notifyItemRemoved(position);
                    Toast.makeText(QLTheLoaiActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(QLTheLoaiActivity.this, "Không thể xóa được!", Toast.LENGTH_SHORT).show();
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
    //updateTheLoai
    @Override
    public void onItemClickListener(int position, TheLoai theLoai) {
        AlertDialog.Builder builder = new AlertDialog.Builder(QLTheLoaiActivity.this);
        View view = LayoutInflater.from(QLTheLoaiActivity.this).inflate(R.layout.dialog_edit_the_loai, null);
        final EditText edEditMaTheLoai = view.findViewById(R.id.edEditMaTheLoai);
        final EditText edEditTenTheLoai = view.findViewById(R.id.edEditTenTheLoai);
        final EditText edEditVitri = view.findViewById(R.id.edEditVitri);
        final EditText edEditMota = view.findViewById(R.id.edEditMota);
        Button btnEditSaveTheLoai = view.findViewById(R.id.btnEditSaveTheLoai);
        Button btnEditCancelTheLoai = view.findViewById(R.id.btnEditCancelTheLoai);
        edEditMaTheLoai.setText(theLoai.matheloai);
        edEditTenTheLoai.setText(theLoai.tentheloai);
        edEditVitri.setText(theLoai.vitri);
        edEditMota.setText(theLoai.mota);
        edEditMaTheLoai.setEnabled(false);
        builder.setView(view);
        final AlertDialog alertDialog = builder.show();
        btnEditSaveTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheLoai theLoai = new TheLoai();
                theLoai.matheloai = edEditMaTheLoai.getText().toString().trim();
                theLoai.tentheloai = edEditTenTheLoai.getText().toString().trim();
                theLoai.vitri = edEditVitri.getText().toString().trim();
                theLoai.mota = edEditMota.getText().toString().trim();

                if (edEditMaTheLoai.getText().toString().trim().isEmpty()) {
                    edEditMaTheLoai.setError("Nhập đủ thông tin...");
                } else if (edEditTenTheLoai.getText().toString().trim().isEmpty()) {
                    edEditTenTheLoai.setError("Nhập đủ thông tin...");
                } else if (edEditVitri.getText().toString().trim().isEmpty()) {
                    edEditVitri.setError("Nhập đủ thông tin...");
                } else if (edEditMota.getText().toString().trim().isEmpty()) {
                    edEditMota.setError("Nhập đủ thông tin...");
                } else {
                    TheLoaiDAO theLoaiDAO = new TheLoaiDAO(mySqlite);
                    boolean ketQua = theLoaiDAO.updateTheLoai(theLoai);

                    if (ketQua) {
                        Toast.makeText(QLTheLoaiActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        theLoaiList = theLoaiDAO.getAllTheLoai();
                        theLoaiAdapter.submitList(theLoaiList);

                    } else {
                        Toast.makeText(QLTheLoaiActivity.this,
                                "Không thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnEditCancelTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}