package com.tasfia.orphanhouse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Volunteer {

    private String Id, Name, Address ,Date,Email, Gender, ContactNo,Profession,   FromWhereYouListenAboutUs, TheReasonOfYourVolunteering, BloodGroup,  Image;

    public Volunteer(String id, String name, String address, String date, String email, String gender,  String contactNo,String profession,   String fromWhereYouListenAboutUs, String theReasonOfYourVolunteering, String bloodGroup,  Bitmap bmp1) {
    }

    public Volunteer(String id, String name, String address, String date, String email, String gender,  String contactNo,String profession,   String fromWhereYouListenAboutUs, String theReasonOfYourVolunteering, String bloodGroup,  String image) {


        this.Id = id;
        this.Name = name;
        this.Address=address;
        this.Date = date;
        this.Email = email;
        this.Gender = gender;
        this.ContactNo = contactNo;
        this.Profession = profession;
        this.FromWhereYouListenAboutUs = fromWhereYouListenAboutUs;
        this.TheReasonOfYourVolunteering = theReasonOfYourVolunteering;
        this.BloodGroup = bloodGroup;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDate() {
    return Date;
}

    public void setDate(String date) {
        Date = date;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
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

    public String getFromWhereYouListenAboutUs() {
        return FromWhereYouListenAboutUs;
    }

    public void setFromWhereYouListenAboutUs(String fromWhereYouListenAboutUs) {
        FromWhereYouListenAboutUs = fromWhereYouListenAboutUs;
    }

    public String getTheReasonOfYourVolunteering() {
        return TheReasonOfYourVolunteering;
    }

    public void setTheReasonOfYourVolunteering(String theReasonOfYourVolunteering) {
        TheReasonOfYourVolunteering = theReasonOfYourVolunteering;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }


    public static class MyAdapter extends ArrayAdapter<Volunteer> {

        Context context;
        List<Volunteer> arrayListVolunteer;


        public MyAdapter(@NonNull Context context, List<Volunteer> arrayListVolunteer) {
            super(context, R.layout.custom_volunteer_list_item, arrayListVolunteer);

            this.context = context;
            this.arrayListVolunteer = arrayListVolunteer;

        }


        @NonNull
        @Override
        public Filter getFilter() {
            return volunteerFilter;
        }

        private Filter volunteerFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Volunteer> filteredVolunteers = new ArrayList<>();

                if(charSequence == null || charSequence.length() == 0){
                    filteredVolunteers.addAll(arrayListVolunteer);
                }
                else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();
                    for (Volunteer volunteer : arrayListVolunteer){
                        if(volunteer.getName().toLowerCase().contains(filterPattern)){
                            filteredVolunteers.add(volunteer);
                        }
                        else if(volunteer.getProfession().trim().contains(filterPattern)){
                            filteredVolunteers.add(volunteer);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredVolunteers;

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


            ImageView imView = view.findViewById(R.id.image_id);
            TextView tvName = view.findViewById(R.id.txt_name);


            tvName.setText(arrayListVolunteer.get(position).getName());

            try{
                byte[] byteImage = Base64.decode(arrayListVolunteer.get(position).getImage(), Base64.DEFAULT);
                Bitmap bmp =BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                imView.setImageBitmap(bmp);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }


            return view;
        }

    }
}


