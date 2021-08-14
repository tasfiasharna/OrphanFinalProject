package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VolunteerOwnProfileActivity extends AppCompatActivity {

    TextView txtEditProfile;
    TextView txtName, txtName2,  txtCell,txtLocation,txtEmail, txtProfession,  txtBlood;
    private ProgressDialog loading;
    ImageView profilePic;
    VolunteerModel volunteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_own_profile);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Profile");

        txtName = findViewById(R.id.txtName);
        txtName2 = findViewById(R.id.txtName2);
        txtCell = findViewById(R.id.txtCell);
        txtLocation = findViewById(R.id.txtLocation);
        txtEmail = findViewById(R.id.txtEmail);
        txtProfession = findViewById(R.id.txtProfession);
        txtBlood = findViewById(R.id.txtBlood);
        profilePic = findViewById(R.id.profile_image);

        txtEditProfile = findViewById(R.id.txtEdit);

        txtEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerOwnProfileActivity.this, VolunteerEditProfileActivity.class);
                LocalStorage.storedEncodedImage = volunteer.getImage();
                volunteer.setImage(null);
                intent.putExtra("volunteerData", volunteer);
                startActivity(intent);
            }

        });

        retrieveData();
    }

    public void retrieveData(){
        loading = new ProgressDialog(VolunteerOwnProfileActivity.this);
        loading.setMessage("Please wait....");
        loading.show();

        PreferenceManager pm = new PreferenceManager(VolunteerOwnProfileActivity.this);

        String url = "http://192.168.0.105:8080/api/volunteer/profile/"+pm.getSignInData(PreferenceManager.USER_NAME_KEY)+"/"+pm.getSignInData(PreferenceManager.USER_PASSWORD_KEY);

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
                                volunteer = new GsonBuilder().create().fromJson(object.toString(), VolunteerModel.class);

                                txtName.setText(volunteer.getName());
                                txtName2.setText(volunteer.getName());
                                txtCell.setText(volunteer.getCell());
                                txtLocation.setText(volunteer.getAddress());
                                txtEmail.setText(volunteer.getEmail());
                                txtProfession.setText(volunteer.getProfession());
                                txtBlood.setText(volunteer.getBlood());

                                if(!TextUtils.isEmpty(volunteer.getImage())){
                                    try{
                                        byte[] byteImage = Base64.decode(volunteer.getImage(), Base64.DEFAULT);
                                        Bitmap bmp1 =BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                                        profilePic.setImageBitmap(bmp1);
                                    }
                                    catch(Exception ex){
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VolunteerOwnProfileActivity.this, "No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(VolunteerOwnProfileActivity.this);
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