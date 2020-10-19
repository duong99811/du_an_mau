package com.e.du_an_mau.Ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.e.du_an_mau.Adapter.SachAdapter;
import com.e.du_an_mau.R;
import com.e.du_an_mau.model.Sach;
import com.e.du_an_mau.model.TheLoai;
import com.e.du_an_mau.sqLite.MySqlite;
import com.e.du_an_mau.sqLite.SachDAO;
import com.e.du_an_mau.sqLite.TheLoaiDAO;

import java.util.ArrayList;
import java.util.List;

public class QLSachActivity extends AppCompatActivity implements SachAdapter.OnItemClickListener {
    private MySqlite mySqlite;
    private RecyclerView recyclerView;
    private SachAdapter sachAdapter;
    private SachDAO sachDAO;
    private TheLoaiDAO theLoaiDAO;
    private List<Sach> sachList;
    private List<TheLoai> theLoaiList;
    String matheloai, soluong;
    EditText edSearch;
    List<String> listSoLuong = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_sach);
        mySqlite = new MySqlite(this);
        sachDAO = new SachDAO(mySqlite);
        recyclerView = findViewById(R.id.rvSach);
        edSearch = findViewById(R.id.edSearch);
        ImageView imgSearch = findViewById(R.id.imgSearch);
        sachList = sachDAO.getAllSach();
        sachAdapter = new SachAdapter(sachList, this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(sachAdapter);
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    sachList = sachDAO.getAllSach();
                    sachAdapter = new SachAdapter(sachList,QLSachActivity.this);
                    recyclerView.setAdapter(sachAdapter);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensach = edSearch.getText().toString().trim();
                if (tensach.isEmpty()) {
                    edSearch.setError("Nhập dữ liệu...");
                    return;
                }
                sachDAO = new SachDAO(mySqlite);
                sachList = sachDAO.searchSach(tensach);
                if (sachList.size() == 0) {
                    edSearch.setError("Không tìm thấy tên sách này!");
                } else {
                    sachAdapter = new SachAdapter(sachList,QLSachActivity.this);
                    recyclerView.setAdapter(sachAdapter);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_sach, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addSach) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_sach, null);
            ImageView imgAddTheLoai = view.findViewById(R.id.imgAddTheLoai);
            final EditText edAddMaSach = view.findViewById(R.id.edAddMaSach);
            final Spinner spnTheLoai = view.findViewById(R.id.spnTheLoai);
            final EditText edAddTenSach = view.findViewById(R.id.edAddTenSach);
            final EditText edAddTacGia = view.findViewById(R.id.edAddTacGia);
            final EditText edAddNXB = view.findViewById(R.id.edAddNXB);
            final EditText edAddGiaBia = view.findViewById(R.id.edAddGiaBia);
            final Spinner spnSoLuong = view.findViewById(R.id.spnSoLuong);
            Button btnAddSave = view.findViewById(R.id.btnAddSaveSach);
            Button btnAddCancel = view.findViewById(R.id.btnAddCancelSach);

            //spinner số lượng
            getSoLuong(spnSoLuong);
            spnSoLuong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    soluong = listSoLuong.get(spnSoLuong.getSelectedItemPosition());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            //spinner thể loại
            getMaTheLoai(spnTheLoai);
            spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    matheloai = theLoaiList.get(spnTheLoai.getSelectedItemPosition()).matheloai;
                    matheloai = adapterView.getSelectedItem().toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            builder.setView(view);
            final AlertDialog alertDialog = builder.show();
            //add thể loại
            imgAddTheLoai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(QLSachActivity.this);
                    View view1 = LayoutInflater.from(QLSachActivity.this).inflate(R.layout.dialog_add_the_loai, null);
                    final EditText edAddMaTheLoai = view1.findViewById(R.id.edAddMaTheLoai);
                    final EditText edAddTenTheLoai = view1.findViewById(R.id.edAddTenTheLoai);
                    final EditText edAddVitri = view1.findViewById(R.id.edAddVitri);
                    final EditText edAddMota = view1.findViewById(R.id.edAddMota);
                    Button btnAddSaveTheLoai = view1.findViewById(R.id.btnAddSaveTheLoai);
                    Button btnAddCancelTheLoai = view1.findViewById(R.id.btnAddCancelTheLoai);
                    builder.setView(view1);
                    final AlertDialog alertDialog1 = builder.show();
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
                                    Toast.makeText(QLSachActivity.this,
                                            "Thành công!", Toast.LENGTH_SHORT).show();
                                    alertDialog1.dismiss();
                                    alertDialog.dismiss();
                                } else {
                                    Toast.makeText(QLSachActivity.this,
                                            "Không thành công!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    });
                    btnAddCancelTheLoai.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog1.dismiss();
                        }
                    });
                }
            });


            //add sách
            btnAddSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String masach = edAddMaSach.getText().toString().trim();
                    String tensach = edAddTenSach.getText().toString().trim();
                    String tacgia = edAddTacGia.getText().toString().trim();
                    String NXB = edAddNXB.getText().toString().trim();
                    String giabia = edAddGiaBia.getText().toString().trim();
                    Sach sach = new Sach(masach, matheloai, tensach, tacgia, NXB, giabia, soluong);

                    if (edAddMaSach.getText().toString().trim().isEmpty()) {
                        edAddMaSach.setError("Nhập đủ thông tin...");
                        return;
                    } else if (edAddTenSach.getText().toString().trim().isEmpty()) {
                        edAddTenSach.setError("Nhập đủ thông tin...");
                        return;
                    } else if (edAddTacGia.getText().toString().trim().isEmpty()) {
                        edAddTacGia.setError("Nhập đủ thông tin...");
                        return;
                    } else if (edAddNXB.getText().toString().trim().isEmpty()) {
                        edAddNXB.setError("Nhập đủ thông tin...");
                        return;
                    } else if (edAddGiaBia.getText().toString().trim().isEmpty()) {
                        edAddGiaBia.setError("Nhập đủ thông tin...");
                        return;
                    } else {
                        boolean ketQua = sachDAO.addSach(sach);
                        if (ketQua) {
                            Toast.makeText(QLSachActivity.this,
                                    "Thành công!", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            List<Sach> sachList = sachDAO.getAllSach();
                            sachAdapter.submitList(sachList);
                        } else {
                            Toast.makeText(QLSachActivity.this,
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

    public void getMaTheLoai(Spinner spinner) {
        theLoaiDAO = new TheLoaiDAO(mySqlite);
        theLoaiList = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theLoaiList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    public void getSoLuong(Spinner spinner) {
        for (int i = 1; i <= 250; i++) {
            String addSoLuong = String.valueOf(i);
            listSoLuong.add(addSoLuong);
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(QLSachActivity.this,
                android.R.layout.simple_list_item_1, listSoLuong);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onItemRemoveListener(final int position, final Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(QLSachActivity.this);
        View view = LayoutInflater.from(QLSachActivity.this).inflate(R.layout.dialog_delete, null);
        Button btnOK = view.findViewById(R.id.btnDialogDelete);
        Button btnCancel = view.findViewById(R.id.btnDialogCancel);
        builder.setView(view);
        final AlertDialog alertDialog = builder.show();
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean status = sachDAO.deleteSach(sach.masach);
                if (status) {
                    sachList.remove(position);
                    sachAdapter.notifyItemRemoved(position);
                    Toast.makeText(QLSachActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(QLSachActivity.this, "Không thể xóa được", Toast.LENGTH_SHORT).show();
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

    //update Sách
    @Override
    public void onItemClickListener(int position, Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(QLSachActivity.this);
        View view = LayoutInflater.from(QLSachActivity.this).inflate(R.layout.dialog_edit_sach, null);
        final EditText edEditMaSach = view.findViewById(R.id.edEditMaSach);
        final Spinner spnTheLoai = view.findViewById(R.id.spnTheLoai);
        ImageView imgAddTheLoaiEdit = view.findViewById(R.id.imgAddTheLoaiEdit);
        final EditText edEditTenSach = view.findViewById(R.id.edEditTenSach);
        final EditText edEditTacGia = view.findViewById(R.id.edEditTacGia);
        final EditText edEditNXB = view.findViewById(R.id.edEditNXB);
        final EditText edEditGiaBia = view.findViewById(R.id.edEditGiaBia);
        final Spinner spnSoLuong = view.findViewById(R.id.spnSoLuong);
        Button btnEditSaveSach = view.findViewById(R.id.btnEditSaveSach);
        Button btnEditCancelSach = view.findViewById(R.id.btnEditCancelSach);

        edEditMaSach.setText(sach.masach);
        edEditTenSach.setText(sach.tensach);
        edEditTacGia.setText(sach.tacgia);
        edEditNXB.setText(sach.NXB);
        edEditGiaBia.setText(sach.giabia);
        edEditMaSach.setEnabled(false);
        //spinner số lượng
        getSoLuong(spnSoLuong);
        //spinner thể loại
        getMaTheLoai(spnTheLoai);
        builder.setView(view);
        final AlertDialog alertDialog = builder.show();
        //add thể loại
        imgAddTheLoaiEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        //edit Sach
        btnEditSaveSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masach = edEditMaSach.getText().toString().trim();
                String tensach = edEditTenSach.getText().toString().trim();
                String tacgia = edEditTacGia.getText().toString().trim();
                String NXB = edEditNXB.getText().toString().trim();
                String giabia = edEditGiaBia.getText().toString().trim();
                Sach sach = new Sach(masach, matheloai, tensach, tacgia, NXB, giabia, soluong);
                if (edEditMaSach.getText().toString().trim().isEmpty()) {
                    edEditMaSach.setError("Nhập đủ thông tin...");
                    return;
                } else if (edEditTenSach.getText().toString().trim().isEmpty()) {
                    edEditTenSach.setError("Nhập đủ thông tin...");
                    return;

                } else if (edEditTacGia.getText().toString().trim().isEmpty()) {
                    edEditTacGia.setError("Nhập đủ thông tin...");
                    return;

                } else if (edEditNXB.getText().toString().trim().isEmpty()) {
                    edEditNXB.setError("Nhập đủ thông tin...");
                    return;

                } else if (edEditGiaBia.getText().toString().trim().isEmpty()) {
                    edEditGiaBia.setError("Nhập đủ thông tin...");
                    return;

                } else {
                    boolean ketQua = sachDAO.updateSach(sach);
                    if (ketQua) {
                        Toast.makeText(QLSachActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        sachList = sachDAO.getAllSach();
                        sachAdapter.submitList(sachList);
                    } else {
                        Toast.makeText(QLSachActivity.this,
                                "Không thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnEditCancelSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}