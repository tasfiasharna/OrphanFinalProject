package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.chrisbanes.photoview.PhotoView;

public class MomentsFullScreenActivity extends AppCompatActivity {

    PhotoView photoView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moments_full_screen);

        photoView2=(PhotoView)findViewById(R.id.myPhotoView2);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

        Intent i=getIntent();

        int position= i.getExtras().getInt("id");


        MomentsActivity.MomentsAdapter momentsAdapter=new MomentsActivity.MomentsAdapter(this);

        photoView2.setImageResource(momentsAdapter.imgId[position]);

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

