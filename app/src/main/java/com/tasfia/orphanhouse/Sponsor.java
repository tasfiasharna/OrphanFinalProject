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
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class Sponsor {private String Id, Name, ContactNo ,Profession, SelectedChild, Address, Sponsorship, Amount, Time, Image;

    public Sponsor() {
    }

    public Sponsor(String id, String name, String contactNo, String profession, String child,  String address, String sponsorship, String amount,  String time, String image) {


        this.Id = id;
        this.Name = name;
        this.ContactNo=contactNo;
        this.Profession = profession;
        this.SelectedChild = child;
        this.Address = address;
        this.Sponsorship = sponsorship;
        this.Amount = amount;
        this.Time = time;
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

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public String getSelectedChild() {
        return SelectedChild;
    }

    public void setSelectedChild(String selectedChild) {
        SelectedChild = selectedChild;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSponsorship() {
        return Sponsorship;
    }

    public void setSponsorship(String sponsorship) {
        Sponsorship = sponsorship;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }


    public static class SponsorAdapter extends ArrayAdapter<Sponsor> {

        Context context;
        List<Sponsor> arrayListSponsor;


        public SponsorAdapter(@NonNull Context context, List<Sponsor> arrayListSponsor) {
            super(context, R.layout.custom_volunteer_list_item, arrayListSponsor);

            this.context = context;
            this.arrayListSponsor = arrayListSponsor;

        }

        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_volunteer_list_item, null, true);

            ImageView imageView = view.findViewById(R.id.image_id);
            TextView tvName = view.findViewById(R.id.txt_name);
//            TextView tvDate = view.findViewById(R.id.date);
//            TextView tvVenue = view.findViewById(R.id.venue);

            tvName.setText(arrayListSponsor.get(position).getName());


            try{
                byte[] byteImage = Base64.decode(arrayListSponsor.get(position).getImage(), Base64.DEFAULT);
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

