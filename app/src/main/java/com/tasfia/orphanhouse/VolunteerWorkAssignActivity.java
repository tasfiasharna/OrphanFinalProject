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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VolunteerWorkAssignActivity extends AppCompatActivity {

    TextView txtDepartment;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_work_assign);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Assign Work");

        txtDepartment = findViewById(R.id.txtDepartment);

        retrieveData();
    }

    public void retrieveData(){
        loading = new ProgressDialog(VolunteerWorkAssignActivity.this);
        loading.setMessage("Please wait....");
        loading.show();

        PreferenceManager pm = new PreferenceManager(VolunteerWorkAssignActivity.this);
        String url = "http://192.168.0.105:8080/api/assignwork/volunteer/android/get/"+pm.getSignInData(PreferenceManager.USER_NAME_KEY);
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

                                String department = object.getString("DepName");

                                txtDepartment.setText(department);
                            }
                        }


                         catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VolunteerWorkAssignActivity.this, "No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(VolunteerWorkAssignActivity.this);
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