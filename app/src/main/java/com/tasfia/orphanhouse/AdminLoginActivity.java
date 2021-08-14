package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
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
import java.util.regex.Pattern;

public class AdminLoginActivity extends AppCompatActivity {


    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    EditText etxtEmail,etxtPassword;
    String str_email,str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Admin Panel");

        etxtEmail = findViewById(R.id.ed_email);
        etxtPassword = findViewById(R.id.ed_password);
    }

    private boolean validateEmail() {
        String email = etxtEmail.getText().toString().trim();

        if (email.isEmpty()) {
            etxtEmail.setError("Field can't be empty");
            return false;
        } else {
            etxtEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = etxtPassword.getText().toString().trim();

        if (password.isEmpty()) {
            etxtPassword.setError("Field can't be empty");
            return false;
        } else {
            etxtPassword.setError(null);
            return true;
        }
    }

        public void Login(View view) {
            if (!validateEmail() | !validatePassword()) {
                return;
            } else if(etxtEmail.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        } else if(etxtPassword.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");
            progressDialog.show();

            str_email = etxtEmail.getText().toString().trim();
            str_password = etxtPassword.getText().toString().trim();

            String url = "http://192.168.0.105:8080/api/admin/user/android/"+str_email+"/"+str_password;
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if(response.equalsIgnoreCase("logged in successfully")){

                        etxtEmail.setText("");
                        etxtPassword.setText("");

                        PreferenceManager pm = new PreferenceManager(AdminLoginActivity.this);
                        pm.setSignInData(PreferenceManager.USER_EMAIL_KEY, str_email);
                        pm.setSignInData(PreferenceManager.USER_PASSWORD_KEY, str_password);
                        pm.setUserLoggedIn(true);

                        startActivity(new Intent(getApplicationContext(),AdminDetailsActivity.class));
                        Toast.makeText(AdminLoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(AdminLoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    }

                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(AdminLoginActivity.this, "No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Email",str_email);
                    params.put("aPassword",str_password);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(AdminLoginActivity.this);
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
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {

            Intent intent=new Intent(AdminLoginActivity.this, HomeActivity.class);
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
