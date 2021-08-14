package com.tasfia.orphanhouse;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Donator {

    private String Id, Name, Gender ,Address, City, Profession,  ContactNo, Email;

    public Donator() {
    }

    public Donator(String id, String name, String gender, String address, String city,  String profession,   String contactNo,  String email) {

        this.Id = id;
        this.Name = name;
        this.Gender = gender;
        this.Address=address;
        this.City = city;
        this.Profession = profession;
        this.ContactNo = contactNo;
        this.Email = email;

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

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public static class MyAdapter extends ArrayAdapter<Donator> {

        Context context;
        List<Donator> arrayListDonator;

        public MyAdapter(@NonNull Context context, List<Donator> arrayListDonator) {
            super(context, R.layout.custom_list_item, arrayListDonator);

            this.context = context;
            this.arrayListDonator = arrayListDonator;

        }

        @NonNull
        @Override
        public Filter getFilter() {
            return donatorFilter;
        }

        private Filter donatorFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Donator> filteredDonators = new ArrayList<>();

                if(charSequence == null || charSequence.length() == 0){
                    filteredDonators.addAll(arrayListDonator);
                }
                else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();
                    for (Donator donator : arrayListDonator){
                        if(donator.getName().toLowerCase().contains(filterPattern)){
                            filteredDonators.add(donator);
                        }
                        else if(donator.getCity().trim().contains(filterPattern)){
                            filteredDonators.add(donator);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredDonators;

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

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, null, true);

//            ImageView imageview = view.findViewById(R.id.image);
            TextView tvName = view.findViewById(R.id.txt_name);

//            tvID.setText(arrayListDonator.get(position).getId());
            tvName.setText(arrayListDonator.get(position).getName());

            return view;
        }
    }
}

