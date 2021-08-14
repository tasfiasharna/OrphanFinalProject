package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VolunteerProfileActivity extends AppCompatActivity {


    Button btnProfile,btnPassword,btnAssignWork,btnNotice,btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_profile);

        btnProfile = findViewById(R.id.btn_profile);
        btnPassword = findViewById(R.id.btn_password);
        btnAssignWork = findViewById(R.id.btn_assignWork);
        btnNotice = findViewById(R.id.btn_notice);
        btnLogOut = findViewById(R.id.btn_logOut);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Volunteer Profile");

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VolunteerProfileActivity.this, VolunteerOwnProfileActivity.class);
                startActivity(intent);
                Toast.makeText(VolunteerProfileActivity.this, "My Profile Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VolunteerProfileActivity.this, WebTestActivity.class);
                intent.putExtra("url" ,"192.168.0.105:1215/VolunteerRegistration/PResetPass");
                startActivity(intent);
                Toast.makeText(VolunteerProfileActivity.this, "Updated Password Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnAssignWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(VolunteerProfileActivity.this,VolunteerWorkAssignActivity.class);
                startActivity(intent);
                Toast.makeText(VolunteerProfileActivity.this, "Assign work Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VolunteerProfileActivity.this, VolunteerNoticeActivity.class);
                startActivity(intent);
                Toast.makeText(VolunteerProfileActivity.this, "Notice Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(VolunteerProfileActivity.this,VolunteerLogInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

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
        super.onBackPressed();
        finish();
    }
}

