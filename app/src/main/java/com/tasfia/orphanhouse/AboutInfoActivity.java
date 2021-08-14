package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutInfoActivity extends AppCompatActivity {

    Button btnMission,btnStory,btnApproach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_info);

        btnMission=findViewById(R.id.btn_mission);
        btnStory=findViewById(R.id.btn_story);
        btnApproach=findViewById(R.id.btn_approach);


        btnMission.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(AboutInfoActivity.this, AboutInfoDetailsActivity.class);
                intent.putExtra("id" ,"one");
                startActivity(intent);

                Toast.makeText(AboutInfoActivity.this, "Our Mission", Toast.LENGTH_SHORT).show();

            }
        });
        btnStory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(AboutInfoActivity.this, AboutInfoDetailsActivity.class);
                intent.putExtra("id" ,"two");
                startActivity(intent);

                Toast.makeText(AboutInfoActivity.this, " Our Story", Toast.LENGTH_SHORT).show();

            }
        });
        btnApproach.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(AboutInfoActivity.this, AboutInfoDetailsActivity.class);
                intent.putExtra("id" ,"three");
                startActivity(intent);

                Toast.makeText(AboutInfoActivity.this, "Our Approach", Toast.LENGTH_SHORT).show();

            }
        });

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About");

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
