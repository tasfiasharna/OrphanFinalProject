package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.chrisbanes.photoview.PhotoView;

public class CelebratingFullScreenActivity extends AppCompatActivity {

    PhotoView photoView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrating_full_screen);

        photoView3=(PhotoView)findViewById(R.id.myPhotoView3);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

        Intent i=getIntent();

        int position= i.getExtras().getInt("id");

        CelebratingActivity.CelebratingAdapter celebratingAdapter=new CelebratingActivity.CelebratingAdapter(this);

        photoView3.setImageResource(celebratingAdapter.imgId[position]);

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


