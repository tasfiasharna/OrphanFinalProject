package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;

public class SpnsorActivity extends AppCompatActivity {

    KenBurnsView imgSponsor;
    TextView txtName, txtDescription;
    Button btnSponsorChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spnsor);

        imgSponsor =findViewById(R.id.img_sponsor);
        txtName =findViewById(R.id.txt_name);
        txtDescription =findViewById(R.id.txt_description);
        btnSponsorChild =findViewById(R.id.btn_sponsorChild);

        String getId = getIntent().getExtras().getString("id");

        if (getId.equals("one")) {
            imgSponsor.setImageResource(R.drawable.gallery8);
            txtName.setText("About Sponsorship");
            txtDescription.setText(R.string.sponsor);

        }

        btnSponsorChild.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SpnsorActivity.this, "Sponsor A Child", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SpnsorActivity.this, WebActivity.class);
                intent.putExtra("url", "http://192.168.0.105:1215/DetailsOrphan/DetailsOrphan");
                startActivity(intent);
            }
        });


    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Sponsorship");

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
