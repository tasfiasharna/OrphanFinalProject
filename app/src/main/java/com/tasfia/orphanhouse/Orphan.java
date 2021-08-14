package com.tasfia.orphanhouse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Orphan implements Serializable {
    private String Id, Name, Age, History, Gender, PhysicalDefect, WhatKindOfPhysicalDefect, MentalStatus, BloodGroup, AgeCategory, Image;

    public Orphan() {
    }

    public Orphan(String id, String name, String age, String history, String gender, String physicalDefect, String whatKindOfPhysicalDefect, String mentalStatus, String bloodGroup, String image, String ageCategory) {
        this.Id = id;
        this.Name = name;
        this.Age = age;
        this.History = history;
        this.Gender = gender;
        this.PhysicalDefect = physicalDefect;
        this.WhatKindOfPhysicalDefect = whatKindOfPhysicalDefect;
        this.MentalStatus = mentalStatus;
        this.BloodGroup = bloodGroup;
        this.AgeCategory = ageCategory;
        this.Image = image;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getHistory() {
        return History;
    }

    public void setHistory(String history) {
        History = history;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPhysicalDefect() {
        return PhysicalDefect;
    }

    public void setPhysicalDefect(String physicalDefect) {
        PhysicalDefect = physicalDefect;
    }

    public String getWhatKindOfPhysicalDefect() {
        return WhatKindOfPhysicalDefect;
    }

    public void setWhatKindOfPhysicalDefect(String whatKindOfPhysicalDefect) {
        WhatKindOfPhysicalDefect = whatKindOfPhysicalDefect;
    }

    public String getMentalStatus() {
        return MentalStatus;
    }

    public void setMentalStatus(String mentalStatus) {
        MentalStatus = mentalStatus;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getAgeCategory() {
        return AgeCategory;
    }

    public void setAgeCategory(String ageCategory) {
        AgeCategory = ageCategory;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }


    public static class MyAdapter extends ArrayAdapter<Orphan> {

        Context context;
        List<Orphan> arrayListOrphan;

        public MyAdapter(@NonNull Context context, List<Orphan> arrayListOrphan) {
            super(context, R.layout.custom_list_item, arrayListOrphan);

            this.context = context;
            this.arrayListOrphan = arrayListOrphan;

        }

        @NonNull
        @Override
        public Filter getFilter() {
            return orphanFilter;
        }

        private Filter orphanFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Orphan> filteredOrphans = new ArrayList<>();

                if(charSequence == null || charSequence.length() == 0){
                    filteredOrphans.addAll(arrayListOrphan);
                }
                else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();
                    for (Orphan orphan : arrayListOrphan){
                        if(orphan.getName().toLowerCase().contains(filterPattern)){
                            filteredOrphans.add(orphan);
                        }
                        else if(orphan.getAge().trim().contains(filterPattern)){
                            filteredOrphans.add(orphan);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredOrphans;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                clear();
                addAll((List) filterResults.values);
                notifyDataSetChanged();
            }
        };

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_volunteer_list_item, null, true);


            ImageView imageView = view.findViewById(R.id.image_id);
            TextView tvName = view.findViewById(R.id.txt_name);

            tvName.setText(arrayListOrphan.get(position).getName());


            try{
                byte[] byteImage = Base64.decode(arrayListOrphan.get(position).getImage(), Base64.DEFAULT);
                Bitmap bmp =BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                imageView.setImageBitmap(bmp);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

            return view;
        }
    }
}
