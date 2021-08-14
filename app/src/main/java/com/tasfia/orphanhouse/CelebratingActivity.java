package com.tasfia.orphanhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

public class CelebratingActivity extends AppCompatActivity {

    GridView gridView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrating);

        gridView3 = findViewById(R.id.gridview3);

        gridView3.setAdapter(new CelebratingActivity.CelebratingAdapter(this));

        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), CelebratingFullScreenActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
                // ToDo Auto-generated method stub
                Toast.makeText(getBaseContext(),
                        "16th December Celebration " + (position + 1) + " selected",
                        Toast.LENGTH_SHORT).show();
            }
        });

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Celebrating 16th December");
    }

    public static class CelebratingAdapter extends BaseAdapter {
        private Context context;

        Integer[] imgId = {
                R.drawable.victory1, R.drawable.victory2, R.drawable.victory3,
                R.drawable.victory5, R.drawable.victory6, R.drawable.victory7
        };

        public CelebratingAdapter(Context c) {
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

}

