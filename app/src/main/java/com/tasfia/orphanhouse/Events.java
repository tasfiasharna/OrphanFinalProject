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

public class Events {private String Id, Title, Date ,Time, Venue, WriteSomething, Image;

    public Events() {
    }

    public Events(String id, String title, String date, String time, String venue,  String writeSomething, String image) {

        this.Id = id;
        this.Title = title;
        this.Date=date;
        this.Time = time;
        this.Venue = venue;
        this.WriteSomething = writeSomething;
        this.Image = image;

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

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public String getWriteSomething() {
        return WriteSomething;
    }

    public void setWriteSomething(String writeSomething) {
        WriteSomething = writeSomething;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }


    public static class MyAdapter extends ArrayAdapter<Events> {

        Context context;
        List<Events> arrayListEvents;


        public MyAdapter(@NonNull Context context, List<Events> arrayListEvents) {
            super(context, R.layout.custom_event_list_item, arrayListEvents);

            this.context = context;
            this.arrayListEvents = arrayListEvents;

        }

        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_event_list_item, null, true);

            ImageView imageView = view.findViewById(R.id.image);
            TextView tvTitle = view.findViewById(R.id.title);
            TextView tvDate = view.findViewById(R.id.date);
            TextView tvVenue = view.findViewById(R.id.venue);

            tvTitle.setText(arrayListEvents.get(position).getTitle());
            tvDate.setText(arrayListEvents.get(position).getDate() + ",\t" + arrayListEvents.get(position).getTime());
            tvVenue.setText(arrayListEvents.get(position).getVenue());

            try{
                byte[] byteImage = Base64.decode(arrayListEvents.get(position).getImage(), Base64.DEFAULT);
                Bitmap bmp = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                imageView.setImageBitmap(bmp);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

            return view;
        }
    }
}



