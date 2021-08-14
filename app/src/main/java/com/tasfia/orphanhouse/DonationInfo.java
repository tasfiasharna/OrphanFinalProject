package com.tasfia.orphanhouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DonationInfo { private String Id, Name, Address, ContactNo, Email ,Donate, Amount, Item, Time;

    public DonationInfo() {
    }

    public DonationInfo(String id, String name,  String address,   String contactNo,  String email, String donate,  String amount, String item,  String time) {

        this.Id = id;
        this.Name = name;
        this.Address=address;
        this.ContactNo = contactNo;
        this.Email = email;
        this.Donate = donate;
        this.Amount = amount;
        this.Item = item;
        this.Time = time;

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

    public String getDonate() {
        return Donate;
    }

    public void setDonate(String donate) {
        Donate = donate;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }


    public static class DonateInfoAdapter extends ArrayAdapter<DonationInfo> {

        Context context;
        List<DonationInfo> arrayListDonationInfo;

        public DonateInfoAdapter(@NonNull Context context, List<DonationInfo> arrayListDonationInfo) {
            super(context, R.layout.custom_donation_list_item, arrayListDonationInfo);

            this.context = context;
            this.arrayListDonationInfo = arrayListDonationInfo;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_donation_list_item, null, true);

            TextView tvItem = view.findViewById(R.id.donate);
            TextView tvTime = view.findViewById(R.id.time);

            tvItem.setText(arrayListDonationInfo.get(position).getDonate());
            tvTime.setText(arrayListDonationInfo.get(position).getTime());

            return view;
        }
    }
}

