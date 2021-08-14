package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.ByteArrayOutputStream;

public class OrphanDetailActivity extends AppCompatActivity {


    TextView tvId,tvName,tvAge,tvHistory,tvGender,tvDefect,tvKind,tvMental,tvBlood;
    ImageView imageView;
    Button btnAdopt;
    int position;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orphan_detail);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

        tvId = findViewById(R.id.txtid);
        tvName = findViewById(R.id.txtname);
        tvAge = findViewById(R.id.txtage);
        tvHistory = findViewById(R.id.txthistory);
        tvGender = findViewById(R.id.txtgender);
        tvDefect = findViewById(R.id.txtdefect);
        tvKind = findViewById(R.id.txtkind);
        tvMental = findViewById(R.id.txtmental);
        tvBlood = findViewById(R.id.txtblood);
        imageView= findViewById(R.id.imageView);
        btnAdopt= findViewById(R.id.btnAdopt);


        final Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        btnAdopt.setVisibility(intent.getBooleanExtra("ButtonVisibility", false) ? View.VISIBLE : View.GONE);

        btnAdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(OrphanDetailActivity.this, AdoptionFormActivity.class);
                mIntent.putExtra("AId", intent.getStringExtra("Name"));
                startActivity(mIntent);
            }
        });


        tvId.setText("Id: "+ViewOrphanDetailsActivity.orphanArrayList.get(position).getId());
        tvName.setText("Name: "+ViewOrphanDetailsActivity.orphanArrayList.get(position).getName());
        tvAge.setText("Age: "+ViewOrphanDetailsActivity.orphanArrayList.get(position).getAge());
        tvHistory.setText("Background History: "+ ViewOrphanDetailsActivity.orphanArrayList.get(position).getHistory());
        tvGender.setText("Gender: "+ViewOrphanDetailsActivity.orphanArrayList.get(position).getGender());
        tvDefect.setText("Physical Defect: " +ViewOrphanDetailsActivity.orphanArrayList.get(position).getPhysicalDefect());
        tvKind.setText("What Kind of Physical Defect: " +ViewOrphanDetailsActivity.orphanArrayList.get(position).getWhatKindOfPhysicalDefect());
        tvMental.setText("Mental Status: " + ViewOrphanDetailsActivity.orphanArrayList.get(position).getMentalStatus());
        tvBlood.setText("Blood Group: " + ViewOrphanDetailsActivity.orphanArrayList.get(position).getBloodGroup());


        switch (ViewOrphanDetailsActivity.orphanArrayList.get(position).getPhysicalDefect()){
            case "Yes": tvKind.setVisibility(View.VISIBLE);
                break;

            default:
            case "No": tvKind.setVisibility(View.GONE);
                break;
        }


        try{
            byte[] byteImage = Base64.decode(ViewOrphanDetailsActivity.orphanArrayList.get(position).getImage(), Base64.DEFAULT);
            Bitmap bmp1 =BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
            imageView.setImageBitmap(bmp1);
        }
        catch(Exception ex){
            ex.printStackTrace();
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
