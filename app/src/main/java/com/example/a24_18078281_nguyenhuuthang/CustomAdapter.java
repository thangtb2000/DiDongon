package com.example.a24_18078281_nguyenhuuthang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a24_18078281_nguyenhuuthang.database.OnClickListener;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    List<User> mUsers;
    OnClickListener listener;

    public CustomAdapter(List<User> mUsers, OnClickListener listener) {
        this.mUsers = mUsers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            User user = mUsers.get(position);
            holder.user1 = user;
        //xét test cho từng ô trong list-Item
        holder.tvId.setText(user.getId()+"");       // + "" để thành chuỗi
        holder.tvName.setText(user.getName());
        holder.tvAge.setText(user.getAge()+"");
        holder.tvGioiTinh.setText(user.isGioiTinh()+"");
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setListChanged(List<User> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName;
        TextView tvAge, tvGioiTinh;
        User user1;
        Button btnListSua, btnListXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Ánh xạ
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvGioiTinh = itemView.findViewById(R.id.tvGioiTinh);
            btnListSua = itemView.findViewById(R.id.btnListSua);
            btnListXoa = itemView.findViewById(R.id.btnListXoa);
            //Xử lý sự kiện itemView click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(user1);
                }
            });
            btnListXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Dùng interface để click
                    listener.deleteItemUser(user1);
                }
            });
            btnListSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.updateItemUser(user1);
                }
            });
        }
    }
}
