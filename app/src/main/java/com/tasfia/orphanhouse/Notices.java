package com.tasfia.orphanhouse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Notices {private String Id, Title, Date ,Time;

    public Notices() {
    }

    public Notices(String id, String title, String date, String time) {

        this.Id = id;
        this.Title = title;
        this.Date=date;
        this.Time = time;

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }


    public static class MyAdapter extends ArrayAdapter<Notices> {

        Context context;
        List<Notices> arrayListNotices;

        public MyAdapter(@NonNull Context context, List<Notices> arrayListNotices) {
            super(context, R.layout.custom_event_list_item, arrayListNotices);

            this.context = context;
            this.arrayListNotices = arrayListNotices;

        }

        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_notice_list_item, null, true);

            TextView tvTitle = view.findViewById(R.id.title);
            TextView tvDate = view.findViewById(R.id.date);

            tvTitle.setText(arrayListNotices.get(position).getTitle());
            tvDate.setText(arrayListNotices.get(position).getDate() + ",\t" + arrayListNotices.get(position).getTime());


            return view;
        }
    }
}



