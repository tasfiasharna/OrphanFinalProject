package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button btnProfile,btnVideos,btnDonate,btnGallery,btnEvents,btnInfo,btnAdoption,btnFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnProfile = findViewById(R.id.btn_profile);
        btnVideos = findViewById(R.id.btn_videos);
        btnDonate = findViewById(R.id.btn_donate);
        btnGallery = findViewById(R.id.btn_gallery);
        btnEvents = findViewById(R.id.btn_events);
        btnInfo = findViewById(R.id.btn_info);
        btnAdoption = findViewById(R.id.btn_adoption);
        btnFeedback = findViewById(R.id.btn_feedback);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Profile Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, VideosActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Videos Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(HomeActivity.this,DonationActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Donate Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, GalleryActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Gallery Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(HomeActivity.this,EventsActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Events Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, InfoActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Info Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnAdoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(HomeActivity.this, "Adoption Clicked", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(HomeActivity.this, WebActivity.class);
                intent.putExtra("url" ,"http://192.168.0.105:1215/Adoption/ChoseCategory");
                startActivity(intent);
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(HomeActivity.this,FeedbackActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Feedback Clicked", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
