package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

public class VolunteerDetailActivity extends AppCompatActivity {


    TextView tvId, tvName, tvAddress,  tvEmail,  tvGender,tvDate, tvContactNo, tvProfession,  tvListen, tvReason, tvBlood;
    ImageView imageView;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_detail);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

        tvId = findViewById(R.id.txtid);
        tvName = findViewById(R.id.txtname);
        tvAddress = findViewById(R.id.txtaddress);
        tvDate = findViewById(R.id.txtdate);
        tvEmail = findViewById(R.id.txtemail);
        tvGender = findViewById(R.id.txtgender);
        tvContactNo = findViewById(R.id.txtcontactNo);
        tvProfession = findViewById(R.id.txtprofession);
        tvListen = findViewById(R.id.txtlisten);
        tvReason = findViewById(R.id.txtreason);
        tvBlood = findViewById(R.id.txtblood);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");


        tvId.setText("ID: "+ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getId());
        tvName.setText("Name: "+ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getName());
        tvAddress.setText("Address: "+ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getAddress());
        tvDate.setText("Date of Birth: "+ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getDate());
        tvEmail.setText("Email: "+ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getEmail());
        tvGender.setText("Gender: "+ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getGender());
        tvContactNo.setText("Contact No: "+ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getContactNo());
        tvProfession.setText("Profession: "+ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getProfession());
        tvListen.setText("From Where You Learn About Us: "+ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getFromWhereYouListenAboutUs());
        tvReason.setText("The Reason of Your Volunteering: "+ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getTheReasonOfYourVolunteering());
        tvBlood.setText("Blood Group: "+ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getBloodGroup());


        try{
            byte[] byteImage = Base64.decode(ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getImage(), Base64.DEFAULT);
            Bitmap bmp1 =BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
            imageView.setImageBitmap(bmp1);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}







