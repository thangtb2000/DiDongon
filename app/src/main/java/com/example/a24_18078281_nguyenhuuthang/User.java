package com.example.a24_18078281_nguyenhuuthang;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity()
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Name") //khong xet thi lay ten mac dinh
    private String name;
    @ColumnInfo(name = "Age")
    private int age;
    @ColumnInfo(name = "GioiTinh")
    private  boolean gioiTinh;
    @Ignore         //chay room thi phai co @Ignore
    public User(int id, String name, int age, boolean gioiTinh) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gioiTinh = gioiTinh;
    }
        //Dung cai User nay(Khong can id vi id chay tu dong)
    public User(String name, int age, boolean gioiTinh) {
        this.name = name;
        this.age = age;
        this.gioiTinh = gioiTinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

}
