package com.example.a24_18078281_nguyenhuuthang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a24_18078281_nguyenhuuthang.database.UserDatabase;

public class UpdateActivity extends AppCompatActivity {
    EditText plUpdateId, plUpdateName, plUpdateAge, plUpdateGioiTinh;
    Button btnUpdateSua, btnUpdateCancel;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //Anh xa
        plUpdateId = findViewById(R.id.plUpdateId);
        plUpdateName = findViewById(R.id.plUpdateName);
        plUpdateAge = findViewById(R.id.plUpdateAge);
        plUpdateGioiTinh = findViewById(R.id.plUpdateGioTinh);
        btnUpdateSua = findViewById(R.id.btnUpdateSua);
        btnUpdateCancel = findViewById(R.id.btnUpdateCancel);

        //Hiện thị thông tin user ra giao diện
        user = (User) getIntent().getSerializableExtra("user");
        plUpdateId.setText(user.getId() +"");
        plUpdateName.setText(user.getName());
        plUpdateAge.setText(user.getAge() +"");
        plUpdateGioiTinh.setText(user.isGioiTinh() +"");

        //Xu ly Update
        btnUpdateSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = user.getId();
                String name = plUpdateName.getText().toString();
                int age = Integer.parseInt(plUpdateAge.getText().toString());
                boolean gioiTinh = Boolean.parseBoolean(plUpdateGioiTinh.getText().toString());
                UserDatabase.getInstance(UpdateActivity.this).userDao().updateUser(new User(id, name, age, gioiTinh));

                Toast.makeText(UpdateActivity.this, "Update Thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //Xu ly Cancel
        btnUpdateCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}