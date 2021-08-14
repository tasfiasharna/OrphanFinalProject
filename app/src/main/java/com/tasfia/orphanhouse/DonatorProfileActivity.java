package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DonatorProfileActivity extends AppCompatActivity {

    Button btnProfile,btnPassword,btnHistory,btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donator_profile);

        btnProfile = findViewById(R.id.btn_profile);
        btnPassword = findViewById(R.id.btn_password);
        btnHistory = findViewById(R.id.btn_assignWork);
        btnLogOut = findViewById(R.id.btn_logOut);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Donator Profile");

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DonatorProfileActivity.this, DonatorOwnProfileActivity.class);
                startActivity(intent);
                Toast.makeText(DonatorProfileActivity.this, "My Profile Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DonatorProfileActivity.this, WebTestActivity.class);
                intent.putExtra("url" ,"192.168.0.105:1215/Donator/UpdatePassword");
                startActivity(intent);
                Toast.makeText(DonatorProfileActivity.this, "Updated Password Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DonatorProfileActivity.this, DonateHistoryListActivity.class);
                startActivity(intent);
                Toast.makeText(DonatorProfileActivity.this, "Donation History Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(DonatorProfileActivity.this,DonatorLogInActivity.class);
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

