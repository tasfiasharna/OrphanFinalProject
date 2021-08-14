package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoDetailsActivity extends AppCompatActivity {

    VideoView video;
    MediaController controller;

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_details);

        video=findViewById(R.id.videoView);

        controller=new MediaController(this);
        video.setMediaController(controller);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");


        String getId = getIntent().getExtras().getString("id");
        if (getId.equals("one")) {
            video.setVideoURI(Uri.parse("android.resource://com.tasfia.orphanhouse/raw/demo"));
            video.start();

        }

        if (getId.equals("two")) {
            video.setVideoURI(Uri.parse("android.resource://com.tasfia.orphanhouse/raw/moments"));
            video.start();

        }

        if (getId.equals("three")) {
            video.setVideoURI(Uri.parse("android.resource://com.tasfia.orphanhouse/raw/donate"));
            video.start();

        }

        if (getId.equals("four")) {
            video.setVideoURI(Uri.parse("android.resource://com.tasfia.orphanhouse/raw/dream"));
            video.start();

        }

        if (getId.equals("five")) {
            video.setVideoURI(Uri.parse("android.resource://com.tasfia.orphanhouse/raw/happy"));
            video.start();

        }

        if (getId.equals("six")) {
            video.setVideoURI(Uri.parse("android.resource://com.tasfia.orphanhouse/raw/heart"));
            video.start();

        }

        if (getId.equals("seven")) {
            video.setVideoURI(Uri.parse("android.resource://com.tasfia.orphanhouse/raw/laugh"));
            video.start();

        }

        if (getId.equals("eight")) {
            video.setVideoURI(Uri.parse("android.resource://com.tasfia.orphanhouse/raw/life"));
            video.start();

        }

        if (getId.equals("nine")) {
            video.setVideoURI(Uri.parse("android.resource://com.tasfia.orphanhouse/raw/project"));
            video.start();

        }

        if (getId.equals("ten")) {
            video.setVideoURI(Uri.parse("android.resource://com.tasfia.orphanhouse/raw/street"));
            video.start();

        }
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

            Intent intent=new Intent(VideoDetailsActivity.this, VideosActivity.class);
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
