package com.tasfia.orphanhouse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AssignWork {
    private String id,name,department;

    public AssignWork() {
    }

    public AssignWork(String id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public static class MyAssignAdapter extends ArrayAdapter<AssignWork> {

        Context context;
        List<AssignWork> arrayListAssignWork;

        public MyAssignAdapter(@NonNull Context context, List<AssignWork> arrayListAssignWork) {
            super(context, R.layout.custom_assignwork_list_item, arrayListAssignWork);

            this.context = context;
            this.arrayListAssignWork = arrayListAssignWork;

        }

        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_assignwork_list_item, null, true);

            TextView tvDapertment = view.findViewById(R.id.department);

            tvDapertment.setText(arrayListAssignWork.get(position).getDepartment());

            return view;
        }

    }
}

