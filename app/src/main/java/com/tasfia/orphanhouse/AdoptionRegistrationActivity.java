
package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class AdoptionRegistrationActivity extends AppCompatActivity {

    EditText ed_username,ed_email,ed_password;
    //    String str_name,str_email,str_password;
    String url = "https://orphanbd.000webhostapp.com/adoptionregister.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_registration);

        ed_email = findViewById(R.id.ed_email);
        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);

    }

    public void moveToLogin(View view) {

        startActivity(new Intent(getApplicationContext(),AdoptionLoginActivity.class));
        finish();
    }

    private boolean validateEmail() {
        String email = ed_email.getText().toString().trim();

        if (email.isEmpty()) {
            ed_email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ed_email.setError("Please enter a valid email address");
            return false;
        } else {
            ed_email.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String name = ed_username.getText().toString().trim();

        if (name.isEmpty()) {
            ed_username.setError("Field can't be empty");
            return false;
        } else if (!name.matches("[a-zA-Z ]+")) {
            ed_username.requestFocus();
            ed_username.setError("Enter only alphabetical character");
        } else if (name.length() > 15) {
            ed_username.setError("Name too long");
            return false;
        } else if (name.length() < 5) {
            ed_username.setError("Name too sort");
            return false;
        } else {
            ed_username.setError(null);
            return true;
        }
        return true;
    }

    private boolean validatePassword() {
        String password = ed_password.getText().toString().trim();

        if (password.isEmpty()) {
            ed_password.setError("Field can't be empty");
            return false;
        } else if (password.length() > 15) {
            ed_password.setError("Password too long");
            return false;
        } else if (password.length() <5 ) {
            ed_password.setError("Password too sort");
            return false;
        } else {
            ed_password.setError(null);
            return true;
        }
    }

    public void Register(View view) {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }

        final String str_name=ed_username.getText().toString().trim();
        final String str_email=ed_email.getText().toString().trim();
        final String str_password=ed_password.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");

        if(ed_username.getText().toString().equals("")){
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }
        else if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{

            progressDialog.show();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    ed_username.setText("");
                    ed_email.setText("");
                    ed_password.setText("");
                    Toast.makeText(AdoptionRegistrationActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(AdoptionRegistrationActivity.this,  "No Internet Connection  !!!", Toast.LENGTH_LONG).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("Name",str_name);
                    params.put("Email",str_email);
                    params.put("Password",str_password);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(AdoptionRegistrationActivity.this);
            requestQueue.add(request);

        }
    }
}
