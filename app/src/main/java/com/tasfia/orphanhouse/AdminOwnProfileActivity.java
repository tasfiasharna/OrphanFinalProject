package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

public class AdminOwnProfileActivity extends AppCompatActivity {


    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    TextView txtEditProfile;
    TextView txtName, txtName2,  txtEmail, txtEducation,  txtSkills;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_own_profile);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Profile");

        txtName = findViewById(R.id.txtName);
        txtName2 = findViewById(R.id.txtName2);
        txtEmail = findViewById(R.id.txtEmail);
        txtEducation = findViewById(R.id.txtEdcation);
        txtSkills = findViewById(R.id.txtSkill);
        txtEditProfile = findViewById(R.id.txtEdit);

        txtEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(AdminOwnProfileActivity.this,AdminEditProfileActivity.class);
                intent.putExtra("UserName",txtName.getText());
                intent.putExtra("Email",txtEmail.getText());
                intent.putExtra("Skills",txtSkills.getText());
                intent.putExtra("Education",txtEducation.getText());
                startActivity(intent);
            }
        });

        retrieveData();
    }

    public void retrieveData(){
        loading = new ProgressDialog(AdminOwnProfileActivity.this);
        loading.setMessage("Please wait....");
        loading.show();

        PreferenceManager pm = new PreferenceManager(AdminOwnProfileActivity.this);
        String url = "http://192.168.0.105:8080/api/admin/profile/"+pm.getSignInData(PreferenceManager.USER_EMAIL_KEY);

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

                                String name = object.getString("UserName");
                                String email = object.getString("Email");
                                String education = object.getString("Education");
                                String skill = object.getString("Skills");

                                txtName.setText(name);
                                txtName2.setText(name);
                                txtEmail.setText(email);
                                txtEducation.setText(education);
                                txtSkills.setText(skill);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminOwnProfileActivity.this, "No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(AdminOwnProfileActivity.this);
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

    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {

            Intent intent=new Intent(AdminOwnProfileActivity.this, AdminDetailsActivity.class);
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

