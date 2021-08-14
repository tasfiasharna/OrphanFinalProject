package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {


    Button btnAbout,btnSponsor,btnContact;

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btnAbout=findViewById(R.id.btn_about);
        btnSponsor=findViewById(R.id.btn_sponsor);
        btnContact=findViewById(R.id.btn_contact);

        btnAbout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(InfoActivity.this, AboutInfoActivity.class);
                intent.putExtra("id" ,"one");
                startActivity(intent);

                Toast.makeText(InfoActivity.this, "About Clicked", Toast.LENGTH_SHORT).show();

            }
        });
        btnSponsor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(InfoActivity.this, SpnsorActivity.class);
                intent.putExtra("id" ,"two");
                startActivity(intent);

                Toast.makeText(InfoActivity.this, "Sponsor Clicked", Toast.LENGTH_SHORT).show();

            }
        });
        btnContact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(InfoActivity.this, ContactInfoActivity.class);
                intent.putExtra("id" ,"three");
                startActivity(intent);

                Toast.makeText(InfoActivity.this, "Contact Clicked", Toast.LENGTH_SHORT).show();

            }
        });

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Info");//for actionbar title

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

    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {

            Intent intent=new Intent(InfoActivity.this, HomeActivity.class);
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
