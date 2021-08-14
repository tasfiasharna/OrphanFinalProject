package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AllSponsorDetailsActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    TextView tvName,tvOrphan, tvAddress,  tvContactNo, tvDonate, tvAmount,tvProfession,tvSponsor, tvTime;
    ImageView imageView;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sponsor_details);

        tvName = findViewById(R.id.txtName);
        tvOrphan = findViewById(R.id.txtOrphan);
        tvAddress = findViewById(R.id.txtLocation);
//        tvDonate = findViewById(R.id.txtDonate);
        tvProfession= findViewById(R.id.txtProfession);
        tvContactNo = findViewById(R.id.txtCell);
        tvAmount = findViewById(R.id.txtAmount);
        tvSponsor = findViewById(R.id.txtSponsor);
        tvTime = findViewById(R.id.txtTime);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sponsorship Details");

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvName.setText(AllSponsorActivity.sponsorArrayList.get(position).getName());
        tvOrphan.setText(AllSponsorActivity.sponsorArrayList.get(position).getSelectedChild());
        tvAddress.setText(AllSponsorActivity.sponsorArrayList.get(position).getAddress());
        tvContactNo.setText(AllSponsorActivity.sponsorArrayList.get(position).getContactNo());
        tvAmount.setText(AllSponsorActivity.sponsorArrayList.get(position).getAmount());
        tvSponsor.setText(AllSponsorActivity.sponsorArrayList.get(position).getSponsorship());
        tvProfession.setText(AllSponsorActivity.sponsorArrayList.get(position).getProfession());
        tvTime.setText(AllSponsorActivity.sponsorArrayList.get(position).getTime());

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


    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {

            Intent intent=new Intent(AllSponsorDetailsActivity.this, AdminDetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

}