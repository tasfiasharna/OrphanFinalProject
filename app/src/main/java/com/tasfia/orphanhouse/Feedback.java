package com.tasfia.orphanhouse;

import android.annotation.SuppressLint;
import android.content.Context;
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


public class Feedback {
    private String id,name,email,feedback;

    public Feedback() {
    }

    public Feedback(String id, String name, String email,  String feedback) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.feedback = feedback;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


    public static class MyAdapter extends ArrayAdapter<Feedback> {

        Context context;
        List<Feedback> arrayListFeedback;

        public MyAdapter(@NonNull Context context, List<Feedback> arrayListFeedback) {
            super(context, R.layout.custom_feedback_list_item, arrayListFeedback);

            this.context = context;
            this.arrayListFeedback = arrayListFeedback;

        }

        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_feedback_list_item, null, true);

            TextView tvName = view.findViewById(R.id.name);
            TextView tvEmail = view.findViewById(R.id.email);
            TextView tvFeedback = view.findViewById(R.id.feedback);

            tvName.setText(arrayListFeedback.get(position).getName());
            tvEmail.setText(arrayListFeedback.get(position).getEmail());
            tvFeedback.setText(arrayListFeedback.get(position).getFeedback());

            return view;
        }
    }
}

