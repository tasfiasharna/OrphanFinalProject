package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.chrisbanes.photoview.PhotoView;

public class MehandiFullScreenActivity extends AppCompatActivity {

    PhotoView photoView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mehandi_full_screen);

        photoView1=(PhotoView)findViewById(R.id.myPhotoView1);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");


        Intent i=getIntent();

        int position= i.getExtras().getInt("id");

        MehandiActivity.MehandiAdapter mehandiAdapter=new MehandiActivity.MehandiAdapter(this);

        photoView1.setImageResource(mehandiAdapter.imgId[position]);

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

