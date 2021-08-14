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

public class MehandiActivity extends AppCompatActivity {

    GridView gridView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mehandi);

        gridView1 = findViewById(R.id.gridview1);
        gridView1.setAdapter(new MehandiAdapter(this));

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MehandiFullScreenActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
                // ToDo Auto-generated method stub
//                Toast.makeText(getBaseContext(),
//                        "image" + (position + 1) + " selected",
//                        Toast.LENGTH_SHORT).show();

                Toast.makeText(getBaseContext(),
                        "hena fest " + (position + 1) + " selected",
                        Toast.LENGTH_SHORT).show();
            }
        });

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Mehandi Festival");//for actionbar title
    }

    public static class MehandiAdapter extends BaseAdapter {
        private Context context;

        Integer[] imgId = {
                R.drawable.hena1, R.drawable.hena2, R.drawable.hena3,
                R.drawable.hena4, R.drawable.hena5, R.drawable.hena6,
                R.drawable.hena7, R.drawable.hena8, R.drawable.hena10,
                R.drawable.hena11, R.drawable.hena12, R.drawable.hena13,
                R.drawable.hena14, R.drawable.hena15, R.drawable.hena16,
                R.drawable.hena17, R.drawable.hena18, R.drawable.hena19

        };

        public MehandiAdapter(Context c) {
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
