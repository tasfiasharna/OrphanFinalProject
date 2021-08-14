package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContactInfoActivity extends AppCompatActivity {

    Button btnTouch,btnStaff,btnVolunteers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        btnTouch=findViewById(R.id.btn_touch);
        btnStaff=findViewById(R.id.btn_staff);
        btnVolunteers=findViewById(R.id.btn_volunteers);

        btnTouch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(ContactInfoActivity.this,ContactTouchActivity.class);
                intent.putExtra("id" ,"one");
                startActivity(intent);
                Toast.makeText(ContactInfoActivity.this, "Get In Touch", Toast.LENGTH_SHORT).show();
            }
        });

        btnStaff.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(ContactInfoActivity.this,ContactStaffActivity.class);
                intent.putExtra("id" ,"two");
                startActivity(intent);
                Toast.makeText(ContactInfoActivity.this, "Our Team", Toast.LENGTH_SHORT).show();
            }
        });

        btnVolunteers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(ContactInfoActivity.this,OurVolunteersActivity.class);
                intent.putExtra("id" ,"three");
                startActivity(intent);
                Toast.makeText(ContactInfoActivity.this, "Our Volunteers", Toast.LENGTH_SHORT).show();
            }
        });

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact");

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
