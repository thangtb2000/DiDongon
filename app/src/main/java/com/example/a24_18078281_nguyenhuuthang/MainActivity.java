package com.example.a24_18078281_nguyenhuuthang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a24_18078281_nguyenhuuthang.database.OnClickListener;
import com.example.a24_18078281_nguyenhuuthang.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    EditText plId, plName, plAge, plGioiTinh;
    Button btnAdd, btnDelete, btnUpdate, btnClear;
    Button btnSearch;
    EditText plSearch;
    //Bộ 3 RecyclerView
    RecyclerView rcv;
    List<User> mUsers;
    CustomAdapter adt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh xạ
        plId = findViewById(R.id.plId);
        plName = findViewById(R.id.plName);
        plAge = findViewById(R.id.plAge);
        plGioiTinh = findViewById(R.id.plGioiTinh);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnClear = findViewById(R.id.btnClear);
        //Search
        plSearch = findViewById(R.id.plSearch);
        btnSearch = findViewById(R.id.btnSearch);
        //bo 3
        rcv = findViewById(R.id.rcv);
        mUsers = new ArrayList<>();
//        mUsers.add(new User(14, "Thang", 17, false));

        adt = new CustomAdapter(mUsers, this);
        //setLayout Recy
        rcv.setHasFixedSize(true);
        rcv.setAdapter(adt);
        rcv.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL, false));

        //Goi Roomdatabes
         mUsers = UserDatabase.getInstance(MainActivity.this).userDao().findAll();
         adt.setListChanged(mUsers);

         //Load du lieu
         loadUI();

        //Xu ly xu kien
        //Nut add
         btnAdd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //Kiem tra rong

                 //id tự động
                 String name = plName.getText().toString();
                 int age = Integer.parseInt(plAge.getText().toString());
                 boolean gioTinh = Boolean.parseBoolean(plGioiTinh.getText().toString());
//
//                 if(TextUtils.isEmpty(plName.getText().toString()) || TextUtils.isEmpty(plAge.getText().toString()) || TextUtils.isEmpty(plGioiTinh.getText().toString())){
//                     Toast.makeText(MainActivity.this, "Khong duoc rong", Toast.LENGTH_SHORT).show();
//                 }

                     UserDatabase.getInstance(MainActivity.this).userDao().addUser(new User(name, age, gioTinh));

                     Toast.makeText(MainActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                     loadUI();
                     Clear();



             }
         });
         //Nut Clear
         btnClear.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Clear();
                 btnAdd.setEnabled(true);
             }
         });

         //Nut Xoa o tren giao dien
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(plId.getText().toString());
                String name = plName.getText().toString();
                int age = Integer.parseInt(plAge.getText().toString());
                boolean gioiTinh = Boolean.parseBoolean(plGioiTinh.getText().toString());

                UserDatabase.getInstance(MainActivity.this).userDao().deleteUser(new User(id, name, age, gioiTinh));
                Toast.makeText(MainActivity.this, "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
                loadUI();
                Clear();
                // btnADD true
                btnAdd.setEnabled(true);
            }
        });

        //Nut Update o tren giao dien
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(plId.getText().toString());
                String name = plName.getText().toString();
                int age = Integer.parseInt(plAge.getText().toString());
                boolean gioiTinh = Boolean.parseBoolean(plGioiTinh.getText().toString());
                //Goi database xu ly
                UserDatabase.getInstance(MainActivity.this).userDao().updateUser(new User(id,name,age,gioiTinh));
                Toast.makeText(MainActivity.this, "Sua Thanh Cong", Toast.LENGTH_SHORT).show();
                loadUI();
                Clear();
                // btnADD true
                btnAdd.setEnabled(true);
            }
        });

        //Xu ly tim kiem
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = plSearch.getText().toString();
                List<User> usersTimKiem = UserDatabase.getInstance(MainActivity.this).userDao().searchByName("%"+searchText+"%");
                adt.setListChanged(usersTimKiem);
            }
        });

    }



    private void Clear() {
        plId.setText("");
        plName.setText("");
        plAge.setText("");
        plGioiTinh.setText("");

    }

    private void loadUI() {
        mUsers = UserDatabase.getInstance(MainActivity.this).userDao().findAll();
        adt.setListChanged(mUsers);
    }

    @Override
    public void itemClick(User user) {
        plId.setText(user.getId()+"");
        plName.setText(user.getName());
        plAge.setText(user.getAge()+"");
        plGioiTinh.setText(user.isGioiTinh()+"");

        //Xét không được click vào nút add khi đang ở item
        btnAdd.setEnabled(false);
    }

    //Xóa khi click nút delete ở item
    @Override
    public void deleteItemUser(User user) {
        UserDatabase.getInstance(MainActivity.this).userDao().deleteUser(user);
        Toast.makeText(this, "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
        loadUI();
        Clear();
        btnAdd.setEnabled(true);
    }
    //Sửa khi click nút delete ở item
    @Override
    public void updateItemUser(User user) {
        //Chuyển trang sang UpdateActivity
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        //Gui du lieu user qua
        intent.putExtra("user",user);

        startActivity(intent);




        //Xử lý update tại main luôn
////        int id = Integer.parseInt(plId.getText().toString());
//        String name = plName.getText().toString();
//        int age = Integer.parseInt(plAge.getText().toString());
//        boolean gioiTinh = Boolean.parseBoolean(plGioiTinh.getText().toString());
//        //Goi database xu ly
//        UserDatabase.getInstance(MainActivity.this).userDao().updateUser(new User(user.getId(),name,age,gioiTinh));
//        Toast.makeText(MainActivity.this, "Sua Thanh Cong", Toast.LENGTH_SHORT).show();
//        loadUI();
//        Clear();
//        // btnADD true
//        btnAdd.setEnabled(true);
    }


}