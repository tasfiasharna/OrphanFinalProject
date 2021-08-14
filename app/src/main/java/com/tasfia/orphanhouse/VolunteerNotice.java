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

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VolunteerNotice  {private String Id, Title, Date ,Time;

    public VolunteerNotice() {
    }

    public VolunteerNotice(String id, String title, String date, String time) {



        this.Id = id;
        this.Title = title;
        this.Date=date;
        this.Time = time;
//        this.Venue = venue;
//        this.WriteSomething = writeSomething;
//        this.Image = image;

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

//    public String getVenue() {
//        return Venue;
//    }
//
//    public void setVenue(String venue) {
//        Venue = venue;
//    }
//
//    public String getWriteSomething() {
//        return WriteSomething;
//    }
//
//    public void setWriteSomething(String writeSomething) {
//        WriteSomething = writeSomething;
//    }
//
//    public String getImage() {
//        return Image;
//    }
//
//    public void setImage(String image) {
//        Image = image;
//    }



    public static class MyAdapter extends ArrayAdapter<VolunteerNotice> {

        Context context;
        //        Bitmap bitmap;
        List<VolunteerNotice> arrayListVolunteerNotice;


        public MyAdapter(@NonNull Context context, List<VolunteerNotice> arrayListVolunteerNotice) {
            super(context, R.layout.custom_notice_list_item, arrayListVolunteerNotice);

            this.context = context;
            this.arrayListVolunteerNotice = arrayListVolunteerNotice;

        }

        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_notice_list_item, null, true);

//            TextView tvID = view.findViewById(R.id.txt_id);
//            ImageView imageView = view.findViewById(R.id.image);
            TextView tvTitle = view.findViewById(R.id.title);
            TextView tvDate = view.findViewById(R.id.date);
//            TextView tvVenue = view.findViewById(R.id.venue);

           /* if(admin){
               tvID.setVisibility(View.VISIBLE);
               imageID.setVisibility(View.GONE);
            }
            else {
                imageID.setVisibility(View.VISIBLE);
                tvID.setVisibility(View.GONE);
            }*/

//            imageID.setText(arrayListVolunteer.get(position).getId());
            tvTitle.setText(arrayListVolunteerNotice.get(position).getTitle());
            tvDate.setText(arrayListVolunteerNotice.get(position).getDate() + ",\t" + arrayListVolunteerNotice.get(position).getTime());
//            tvVenue.setText(arrayListVolunteerNotice.get(position).getVenue());

//            try{
//                byte[] byteImage = Base64.decode(arrayListVolunteerNotice.get(position).getImage(), Base64.DEFAULT);
//                Bitmap bmp = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
//                imageView.setImageBitmap(bmp);
//            }
//            catch(Exception ex){
//                ex.printStackTrace();
//            }

//            Glide.with(context)
//                    .load(arrayListEvents.get(position).getImage())
//                    .into(imageView);

            return view;
        }

    }
}



