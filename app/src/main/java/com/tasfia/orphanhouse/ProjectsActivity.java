package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class ProjectsActivity extends AppCompatActivity {

    GridView gridView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        gridView4 = findViewById(R.id.gridview4);

        gridView4.setAdapter(new ProjectsActivity.ProjectsAdapter(this));

        gridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProjectsFullScreenActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
                // ToDo Auto-generated method stub
                Toast.makeText(getBaseContext(),
                        "projects " + (position + 1) + " selected",
                        Toast.LENGTH_SHORT).show();
            }
        });


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Projects");
    }

    public static class ProjectsAdapter extends BaseAdapter {
        private Context context;

        Integer[] imgId = {
                R.drawable.projects1, R.drawable.projects2, R.drawable.projects3,
                R.drawable.projects4, R.drawable.projects5, R.drawable.projects6,
                R.drawable.projects7, R.drawable.projects8, R.drawable.projects9,
                R.drawable.projects10, R.drawable.projects11, R.drawable.projects12,


        };

        public ProjectsAdapter(Context c) {
            context = c;
        }

        //---returns the number of images---
        public int getCount() {
            return imgId.length;
        }

        //---returns the item---
        public Object getItem(int position) {
            return position;
        }

        //---returns the ID of an item---
        public long getItemId(int position) {
            return position;
        }

        //---returns an ImageView view---
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new
                        //   GridView.LayoutParams(85, 85));
                        GridView.LayoutParams(360, 370));
                imageView.setScaleType(
                        ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(0, 0, 0, 0);
//                imageView.setPadding(5, 5, 5, 5);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imgId[position]);
            return imageView;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}



