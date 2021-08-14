package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AllConfirmationActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;


    Button btnVolunteerRequest,btnDonatorRequest,btnAdoptionRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_confirmation);


        btnVolunteerRequest= findViewById(R.id.btn_volunteerRequest);
        btnDonatorRequest= findViewById(R.id.btn_donationRequest);
        btnAdoptionRequest= findViewById(R.id.btn_adoptionRequest);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Request");


        btnVolunteerRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AllConfirmationActivity.this, RequestActivity.class);
                intent.putExtra("urls" ,"http://192.168.0.105:1215/VolunteerRegistration/GetAllVolunteerRequest");
                startActivity(intent);
                Toast.makeText(AllConfirmationActivity.this, "Volunteer Request Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        btnAdoptionRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AllConfirmationActivity.this, RequestActivity.class);
                intent.putExtra("urls" ,"http://192.168.0.105:1215/Adoption/GetAllAdoptionMemberRequest");
                startActivity(intent);
                Toast.makeText(AllConfirmationActivity.this, "Adoption Request Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnDonatorRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AllConfirmationActivity.this, RequestActivity.class);
                intent.putExtra("urls" ,"http://192.168.0.105:1215/Donator/GetAllDonatorRequest");
                startActivity(intent);
                Toast.makeText(AllConfirmationActivity.this, "Donator Request Clicked", Toast.LENGTH_SHORT).show();
            }
        });

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

    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {

            Intent intent=new Intent(AllConfirmationActivity.this, AdminDetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}

