package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class VolunteerLogInActivity extends AppCompatActivity {


    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    EditText ed_name, ed_password;
    String str_name, str_password;
    Button btn_forget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_log_in);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Volunteer Panel");

        ed_name = findViewById(R.id.ed_name);
        ed_password = findViewById(R.id.ed_password);
        btn_forget=findViewById(R.id.btn_forget);

        btn_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VolunteerLogInActivity.this, WebTestActivity.class);
                intent.putExtra("url", "192.168.0.105:1215/VolunteerRegistration/ForgetPassword");
                startActivity(intent);

                Toast.makeText(VolunteerLogInActivity.this, "Forget Password Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateUsername() {
        String name = ed_name.getText().toString().trim();

        if (name.isEmpty()) {
            ed_name.setError("Field can't be empty");
            return false;
        }
        else {
            ed_name.setError(null);
            return true;
        }
    }


    private boolean validatePassword() {
        String password = ed_password.getText().toString().trim();

        if (password.isEmpty()) {
            ed_password.setError("Field can't be empty");
            return false;
        }
        else {
            ed_password.setError(null);
            return true;
        }
    }


    public void Login(View view) {
        if (!validateUsername() | !validatePassword()) {
            return;
        } else if (ed_name.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        } else if (ed_password.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else {


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");

            progressDialog.show();

            str_name = ed_name.getText().toString().trim();
            str_password = ed_password.getText().toString().trim();

            String url = "http://192.168.0.105:8080/api/volunteer/login/android/"+str_name+"/"+str_password;
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if (response.equalsIgnoreCase("logged in successfully")) {

                        ed_name.setText("");
                        ed_password.setText("");
                        PreferenceManager pm = new PreferenceManager(VolunteerLogInActivity.this);
                        pm.setSignInData(PreferenceManager.USER_NAME_KEY, str_name);
                        pm.setSignInData(PreferenceManager.USER_PASSWORD_KEY, str_password);
                        pm.setUserLoggedIn(true);
                        startActivity(new Intent(getApplicationContext(), VolunteerProfileActivity.class));
                        Toast.makeText(VolunteerLogInActivity.this, response, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(VolunteerLogInActivity.this, response, Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(VolunteerLogInActivity.this, "No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("UserName", str_name);
                    params.put("Password", str_password);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(VolunteerLogInActivity.this);
            requestQueue.add(request);

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

    @Override
    public void onBackPressed() {
        if (back_pressed+TIME_DELAY > System.currentTimeMillis()) {

            Intent intent = new Intent(VolunteerLogInActivity.this, ProfileActivity.class);
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

    public void moveToRegistration(View view) {
        startActivity(new Intent(getApplicationContext(), VolunteerRegistrationActivity.class));
        finish();

    }
}
