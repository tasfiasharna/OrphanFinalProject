package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DonatorOwnProfileActivity extends AppCompatActivity {

    TextView txtEditProfile;
    TextView txtName, txtName2,  txtCell,txtLocation,txtEmail, txtProfession,  txtCity;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donator_own_profile);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Profile");

        txtName = findViewById(R.id.txtName);
        txtName2 = findViewById(R.id.txtName2);
        txtCell = findViewById(R.id.txtCell);
        txtLocation = findViewById(R.id.txtLocation);
        txtEmail = findViewById(R.id.txtEmail);
        txtProfession = findViewById(R.id.txtProfession);
        txtCity = findViewById(R.id.txtCity);

        txtEditProfile = findViewById(R.id.txtEdit);

        txtEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(DonatorOwnProfileActivity.this,DonatorEditProfileActivity.class);
                intent.putExtra("Name",txtName.getText());
                intent.putExtra("Address",txtLocation.getText());
                intent.putExtra("ContactNo",txtCell.getText());
                intent.putExtra("Email",txtEmail.getText());
                intent.putExtra("Profession",txtProfession.getText());
                intent.putExtra("City",txtCity.getText());
                startActivity(intent);
            }
        });

        retrieveData();
    }

    public void retrieveData(){
        loading = new ProgressDialog(DonatorOwnProfileActivity.this);
        loading.setMessage("Please wait....");
        loading.show();

        PreferenceManager pm = new PreferenceManager(DonatorOwnProfileActivity.this);
        String url = "http://192.168.0.105:8080/api/donator/profile/"+pm.getSignInData(PreferenceManager.USER_NAME_KEY)+"/"+pm.getSignInData(PreferenceManager.USER_PASSWORD_KEY);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray result = jsonObject.getJSONArray("data");

                            if (success.equals("1")) {
                                JSONObject object = result.getJSONObject(0);

                                String name = object.getString("Name");
                                String address = object.getString("Address");
                                String email = object.getString("Email");
                                String contactNo = object.getString("ContactNo");
                                String profession = object.getString("Profession");
                                String city = object.getString("City");

                                txtName.setText(name);
                                txtName2.setText(name);
                                txtCell.setText(contactNo);
                                txtLocation.setText(address);
                                txtEmail.setText(email);
                                txtProfession.setText(profession);
                                txtCity.setText(city);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DonatorOwnProfileActivity.this, "No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(DonatorOwnProfileActivity.this);
        requestQueue.add(stringRequest);
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