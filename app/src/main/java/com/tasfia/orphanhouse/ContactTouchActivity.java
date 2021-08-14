package com.tasfia.orphanhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ContactTouchActivity extends AppCompatActivity {

    Button btnContact,btnMail;
    RelativeLayout btnFacebook,btnInstagram,btnYouTube;

    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_touch);

        btnContact = findViewById(R.id.btn_contact);
        btnMail = findViewById(R.id.btn_mail);
        btnFacebook = findViewById(R.id.btn_facebook);
        btnInstagram = findViewById(R.id.btn_instagram);
        btnYouTube = findViewById(R.id.btn_youtube);

        btnFacebook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(ContactTouchActivity.this, WebTestActivity.class);
                intent.putExtra("url" ,"www.facebook.com/Orphans-Hope-105859980990579/");
                intent.putExtra("id" ,"three");
                startActivity(intent);
                Toast.makeText(ContactTouchActivity.this, "Facebook Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnInstagram.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(ContactTouchActivity.this, WebTestActivity.class);
                intent.putExtra("url" ,"www.instagram.com/orphanhouse150/");
                intent.putExtra("id" ,"four");
                startActivity(intent);
                Toast.makeText(ContactTouchActivity.this, "Instagram Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnYouTube.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(ContactTouchActivity.this, WebTestActivity.class);
                intent.putExtra("url" ,"www.youtube.com/channel/UCb8NTW8SH8yG8EB5fVmX7VQ?view_as=subscriber&fbclid=IwAR33j7gONTp2bsOz1qXEyjTFKTCLvdQl7jTRsgbQJPhZRRJTTqZbdySKfOQ");
                intent.putExtra("id" ,"five");
                startActivity(intent);
                Toast.makeText(ContactTouchActivity.this, "YouTube Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMail();
            }

            protected void sendMail() {
                Log.i("Send email", "");
                String[] TO = {"orphanhousebd@gmail.com"};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
                emailIntent.putExtra("id", "two");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Finished sending email.", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ContactTouchActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makePhoneCall();
            }
        });

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Get In Touch");
    }
    private void makePhoneCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.putExtra("id" ,"one");
        String number = btnContact.toString();
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(ContactTouchActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ContactTouchActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:+8801518324591";
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                Toast.makeText(ContactTouchActivity.this, "Contact Clicked", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
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
