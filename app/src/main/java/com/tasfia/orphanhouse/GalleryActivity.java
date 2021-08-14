package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class GalleryActivity extends AppCompatActivity {

    Button btnCelebrating,btnMehandi,btnMoments,btnProjects;

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        btnCelebrating=findViewById(R.id.btn_celebrating);
        btnMehandi=findViewById(R.id.btn_mehandi);
        btnMoments=findViewById(R.id.btn_moments);
        btnProjects=findViewById(R.id.btn_projects);

        btnCelebrating.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(GalleryActivity.this, CelebratingActivity.class);
                intent.putExtra("id" ,"one");
                startActivity(intent);

                Toast.makeText(GalleryActivity.this, "Celebrating 16th December", Toast.LENGTH_SHORT).show();
            }
        });

        btnMehandi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(GalleryActivity.this, MehandiActivity.class);
                intent.putExtra("id" ,"two");
                startActivity(intent);

                Toast.makeText(GalleryActivity.this, "Mehandi Festival", Toast.LENGTH_SHORT).show();
            }
        });

        btnMoments.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(GalleryActivity.this, MomentsActivity.class);
                intent.putExtra("id" ,"three");
                startActivity(intent);

                Toast.makeText(GalleryActivity.this, "Happy Moments", Toast.LENGTH_SHORT).show();
            }
        });

        btnProjects.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(GalleryActivity.this, ProjectsActivity.class);
                intent.putExtra("id" ,"four");
                startActivity(intent);

                Toast.makeText(GalleryActivity.this, "Projects", Toast.LENGTH_SHORT).show();
            }
        });

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Gallery");//for actionbar title

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

            Intent intent=new Intent(GalleryActivity.this, HomeActivity.class);
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

