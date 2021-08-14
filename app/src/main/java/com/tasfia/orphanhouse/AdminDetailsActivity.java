package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminDetailsActivity extends AppCompatActivity {


    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    Button btnProfile,btnOrphan,btnVolunteers,btnDonator,btnEvent,btnFeedbackReport,btnSignOut;
    Button btnRequest,btnAdoptionInfo,btnGivenWork,btnViewGivenWork;
    Button btnSponsorDetails;
    Button btnNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_details);

        btnProfile = findViewById(R.id.btn_profile);
        btnOrphan = findViewById(R.id.btn_orphan);
        btnVolunteers = findViewById(R.id.btn_volunteers);
        btnDonator= findViewById(R.id.btn_donator);
        btnEvent= findViewById(R.id.btn_event);
        btnFeedbackReport= findViewById(R.id.btn_viewFeedback);
        btnSignOut= findViewById(R.id.btn_signout);
        btnRequest= findViewById(R.id.btn_request);
        btnAdoptionInfo= findViewById(R.id.btn_adoptionInfo);
        btnGivenWork= findViewById(R.id.btn_givenWork);
        btnViewGivenWork= findViewById(R.id.btn_viewWork);

        btnNotice= findViewById(R.id.btn_notice);
        btnSponsorDetails= findViewById(R.id.btn_sponsorship);



        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminDetailsActivity.this, AdminOwnProfileActivity.class);
                startActivity(intent);
                Toast.makeText(AdminDetailsActivity.this, "My Profile Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnOrphan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminDetailsActivity.this,ViewOrphanDetailsActivity.class);
                startActivity(intent);
                Toast.makeText(AdminDetailsActivity.this, "Orphan List", Toast.LENGTH_SHORT).show();
            }
        });

        btnVolunteers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminDetailsActivity.this,ViewVolunteerDetailsActivity.class);
                startActivity(intent);
                Toast.makeText(AdminDetailsActivity.this, "Volunteer List", Toast.LENGTH_SHORT).show();
            }
        });

        btnDonator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminDetailsActivity.this,DonatorInfoActivity.class);
                startActivity(intent);
                Toast.makeText(AdminDetailsActivity.this, "Donator List", Toast.LENGTH_SHORT).show();
            }
        });

        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminDetailsActivity.this,CreateEventActivity.class);
                startActivity(intent);
                Toast.makeText(AdminDetailsActivity.this, "Create Event", Toast.LENGTH_SHORT).show();
            }
        });

        btnFeedbackReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminDetailsActivity.this,ViewFeedbackActivity.class);
                startActivity(intent);
                Toast.makeText(AdminDetailsActivity.this, "Feedback List", Toast.LENGTH_SHORT).show();
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(AdminDetailsActivity.this, AdminLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminDetailsActivity.this, AllConfirmationActivity.class);
//                intent.putExtra("urls" ,"http://192.168.0.105:1215/VolunteerRegistration/GetAllVolunteerRequest");
                startActivity(intent);
                Toast.makeText(AdminDetailsActivity.this, "Request", Toast.LENGTH_SHORT).show();
            }
        });

        btnAdoptionInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminDetailsActivity.this, RequestActivity.class);
                intent.putExtra("urls" ,"http://192.168.0.105:1215/Adoption/AllAdoptionHistory");
                startActivity(intent);
                Toast.makeText(AdminDetailsActivity.this, "Adoption History", Toast.LENGTH_SHORT).show();
            }
        });

        btnGivenWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminDetailsActivity.this, RequestActivity.class);
                intent.putExtra("urls" ,"http://192.168.0.105:1215/GivenWork/GivenWorkIndex");
                startActivity(intent);
                Toast.makeText(AdminDetailsActivity.this, "Given Work", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewGivenWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminDetailsActivity.this, RequestActivity.class);
                intent.putExtra("urls" ,"http://192.168.0.105:1215/ViewVolunteersGivenWork/GetVolunteers");
                startActivity(intent);
                Toast.makeText(AdminDetailsActivity.this, "View Volunteers Work", Toast.LENGTH_SHORT).show();
            }
        });

        btnNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminDetailsActivity.this,NoticeDetailsActivity.class);
                startActivity(intent);
                Toast.makeText(AdminDetailsActivity.this, "Notice", Toast.LENGTH_SHORT).show();
            }
        });

        btnSponsorDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent=new Intent(AdminDetailsActivity.this,AllSponsorActivity.class);
                Intent intent=new Intent(AdminDetailsActivity.this, RequestActivity.class);
                intent.putExtra("urls" ,"http://192.168.0.105:1215/Sponsorship/SponsorshipHistory");
                startActivity(intent);
                Toast.makeText(AdminDetailsActivity.this, "Sponsorship Details", Toast.LENGTH_SHORT).show();
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

            Intent intent=new Intent(AdminDetailsActivity.this, HomeActivity.class);
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
