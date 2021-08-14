package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

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

public class MomentsActivity extends AppCompatActivity {

    GridView gridView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moments);

        gridView2 = findViewById(R.id.gridview2);

        gridView2.setAdapter(new MomentsAdapter(this));

        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MomentsFullScreenActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
                // ToDo Auto-generated method stub
                Toast.makeText(getBaseContext(),
                        "Happy Moments " + (position + 1) + " selected",
                        Toast.LENGTH_SHORT).show();
            }
        });


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Happy Moments");
    }

    public static class MomentsAdapter extends BaseAdapter {
        private Context context;

        Integer[] imgId = {
                R.drawable.gallery1, R.drawable.gallery2, R.drawable.gallery3,
                R.drawable.gallery4, R.drawable.gallery5, R.drawable.gallery6,
                R.drawable.gallery7, R.drawable.gallery8, R.drawable.gallery9,
                R.drawable.gallery10, R.drawable.gallery11, R.drawable.gallery12
        };

        public MomentsAdapter(Context c) {
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
