package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class AllDonationInfoDetailsActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    TextView tvName,tvName2, tvAddress, tvEmail,  tvContactNo, tvDonate, tvAmount,tvItem,  tvTime;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donation_info_details);

        tvName = findViewById(R.id.txtName);
        tvName2 = findViewById(R.id.txtName2);
        tvAddress = findViewById(R.id.txtLocation);
        tvEmail = findViewById(R.id.txtEmail);
        tvDonate = findViewById(R.id.txtDonate);
        tvItem = findViewById(R.id.txtItem);
        tvContactNo = findViewById(R.id.txtCell);
        tvAmount = findViewById(R.id.txtAmount);
        tvTime = findViewById(R.id.txtTime);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Donation Information");

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvName.setText(AllDonationInfoActivity.arrayListDonationInfo.get(position).getName());
        tvName2.setText(AllDonationInfoActivity.arrayListDonationInfo.get(position).getName());
        tvAddress.setText(AllDonationInfoActivity.arrayListDonationInfo.get(position).getAddress());
        tvEmail.setText(AllDonationInfoActivity.arrayListDonationInfo.get(position).getEmail());
        tvContactNo.setText(AllDonationInfoActivity.arrayListDonationInfo.get(position).getContactNo());
        tvDonate.setText(AllDonationInfoActivity.arrayListDonationInfo.get(position).getDonate());
        tvAmount.setText(AllDonationInfoActivity.arrayListDonationInfo.get(position).getAmount());
        tvItem.setText(AllDonationInfoActivity.arrayListDonationInfo.get(position).getItem());
        tvTime.setText(AllDonationInfoActivity.arrayListDonationInfo.get(position).getTime());

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

            Intent intent=new Intent(AllDonationInfoDetailsActivity.this, AdminDetailsActivity.class);
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