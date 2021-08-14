package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.chrisbanes.photoview.PhotoView;

public class ProjectsFullScreenActivity extends AppCompatActivity {

    PhotoView photoView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_full_screen);

        photoView4=(PhotoView)findViewById(R.id.myPhotoView4);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

        Intent i=getIntent();

        int position= i.getExtras().getInt("id");

        ProjectsActivity.ProjectsAdapter projectsAdapter=new ProjectsActivity.ProjectsAdapter(this);

        photoView4.setImageResource(projectsAdapter.imgId[position]);

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

