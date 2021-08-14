package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

public class DonorDetailsActivity extends AppCompatActivity {

    TextView tvId, tvName, tvAddress, tvCity, tvEmail, tvProfession, tvGender, tvContactNo;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donator_details);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

//        tvId = findViewById(R.id.txtid);
        tvName = findViewById(R.id.txtname);
        tvAddress = findViewById(R.id.txtaddress);
        tvCity = findViewById(R.id.txtcity);
        tvEmail = findViewById(R.id.txtemail);
        tvProfession = findViewById(R.id.txtprofession);
        tvGender = findViewById(R.id.txtgender);
        tvContactNo = findViewById(R.id.txtcontactNo);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

//        tvId.setText("ID: "+ViewDonorDetailsActivity.donatorArrayList.get(position).getId());
        tvName.setText("Name: "+ViewDonorDetailsActivity.donatorArrayList.get(position).getName());
        tvAddress.setText("Address: "+ViewDonorDetailsActivity.donatorArrayList.get(position).getAddress());
        tvCity.setText("City: "+ViewDonorDetailsActivity.donatorArrayList.get(position).getCity());
        tvEmail.setText("Email: "+ViewDonorDetailsActivity.donatorArrayList.get(position).getEmail());
        tvProfession.setText("Profession: "+ViewDonorDetailsActivity.donatorArrayList.get(position).getProfession());
        tvGender.setText("Gender: "+ViewDonorDetailsActivity.donatorArrayList.get(position).getGender());
        tvContactNo.setText("Contact No: "+ViewDonorDetailsActivity.donatorArrayList.get(position).getContactNo());

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
