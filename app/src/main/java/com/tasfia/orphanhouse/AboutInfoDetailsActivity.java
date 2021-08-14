package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;

public class AboutInfoDetailsActivity extends AppCompatActivity {

    KenBurnsView imgAbout;
    TextView txtName, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_info_details);

        imgAbout =findViewById(R.id.img_about);
        txtName = findViewById(R.id.txt_name);
        txtDescription = findViewById(R.id.txt_description);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");


        String getId = getIntent().getExtras().getString("id");
        if (getId.equals("one")) {
            imgAbout.setImageResource(R.drawable.gallery8);
            txtName.setText("Our Mission");
            txtDescription.setText(R.string.mission);

        }

        if (getId.equals("two")) {
            imgAbout.setImageResource(R.drawable.story);
            txtName.setText("Our Story");
            txtDescription.setText(R.string.history);

        }

        if (getId.equals("three")) {
            imgAbout.setImageResource(R.drawable.projects10);
            txtName.setText("Our Approach");
            txtDescription.setText(R.string.approach);

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
