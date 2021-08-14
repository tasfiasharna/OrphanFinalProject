package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VideosActivity extends AppCompatActivity {

    Button btnDemo,btnMoments,btnDonate,btnDream,btnHappy,btnHeart,btnLaugh,btnLife,btnProject,btnStreet;

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        btnDemo = findViewById(R.id.btn_demo);
        btnMoments = findViewById(R.id.btn_moments);
        btnDonate = findViewById(R.id.btn_donate);
        btnDream = findViewById(R.id.btn_dreams);
        btnHappy = findViewById(R.id.btn_happy);
        btnHeart = findViewById(R.id.btn_heart);
        btnLaugh = findViewById(R.id.btn_laugh);
        btnLife = findViewById(R.id.btn_life);
        btnProject = findViewById(R.id.btn_project);
        btnStreet = findViewById(R.id.btn_street);


        btnDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VideosActivity.this, VideoDetailsActivity.class);
                intent.putExtra("id", "one");
                startActivity(intent);

                Toast.makeText(VideosActivity.this, "Demo Video", Toast.LENGTH_SHORT).show();

            }
        });

        btnMoments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VideosActivity.this, VideoDetailsActivity.class);
                intent.putExtra("id", "two");
                startActivity(intent);

                Toast.makeText(VideosActivity.this, "Moments with Orphans", Toast.LENGTH_SHORT).show();

            }
        });

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VideosActivity.this, VideoDetailsActivity.class);
                intent.putExtra("id", "three");
                startActivity(intent);

                Toast.makeText(VideosActivity.this, "Donate for Orphans", Toast.LENGTH_SHORT).show();

            }
        });

        btnDream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VideosActivity.this, VideoDetailsActivity.class);
                intent.putExtra("id", "four");
                startActivity(intent);

                Toast.makeText(VideosActivity.this, "Dream of an Orphan Child", Toast.LENGTH_SHORT).show();

            }
        });

        btnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VideosActivity.this, VideoDetailsActivity.class);
                intent.putExtra("id", "five");
                startActivity(intent);

                Toast.makeText(VideosActivity.this, "Orphan Kid's Some Happpiest Moments", Toast.LENGTH_SHORT).show();

            }
        });

        btnHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VideosActivity.this, VideoDetailsActivity.class);
                intent.putExtra("id", "six");
                startActivity(intent);

                Toast.makeText(VideosActivity.this, "Orphans(Heart Touching Video)", Toast.LENGTH_SHORT).show();

            }
        });

        btnLaugh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VideosActivity.this, VideoDetailsActivity.class);
                intent.putExtra("id", "seven");
                startActivity(intent);

                Toast.makeText(VideosActivity.this, "Orphans Laugh Just Like Any Other Child", Toast.LENGTH_SHORT).show();

            }
        });

        btnLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VideosActivity.this, VideoDetailsActivity.class);
                intent.putExtra("id", "eight");
                startActivity(intent);

                Toast.makeText(VideosActivity.this, "Orphans Life", Toast.LENGTH_SHORT).show();

            }
        });

        btnProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VideosActivity.this, VideoDetailsActivity.class);
                intent.putExtra("id", "nine");
                startActivity(intent);

                Toast.makeText(VideosActivity.this, "Our Project", Toast.LENGTH_SHORT).show();

            }
        });

        btnStreet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VideosActivity.this, VideoDetailsActivity.class);
                intent.putExtra("id", "ten");
                startActivity(intent);

                Toast.makeText(VideosActivity.this, "Homeless Children", Toast.LENGTH_SHORT).show();

            }
        });

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Videos");
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

            Intent intent=new Intent(VideosActivity.this, HomeActivity.class);
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
