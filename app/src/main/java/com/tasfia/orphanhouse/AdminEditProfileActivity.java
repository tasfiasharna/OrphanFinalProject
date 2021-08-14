package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class AdminEditProfileActivity extends AppCompatActivity {


    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    EditText txtName, txtSkills, txtEmail, txtEducation;
    TextView txtName2, txtUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_profile);

        txtName = findViewById(R.id.txt_name);
        txtSkills = findViewById(R.id.txt_skills);
        txtName2 = findViewById(R.id.txt_name2);
        txtEducation=findViewById(R.id.txt_education);
        txtEmail = findViewById(R.id.txt_email);
        txtUpdate = findViewById(R.id.txt_update);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Profile");

        String getName=getIntent().getExtras().getString("UserName");
        String getEmail=getIntent().getExtras().getString("Email");
        String getSkills=getIntent().getExtras().getString("Skills");
        String getEducation=getIntent().getExtras().getString("Education");

        txtName.setText(getName);
        txtEmail.setText(getEmail);
        txtEmail.setEnabled(false);
        txtName2.setText(getName);
        txtSkills.setText(getSkills);
        txtEducation.setText(getEducation);

        txtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminEditProfileActivity.this);
                builder.setMessage("Want to Update Profile?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                UpdateProfile();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        }).show();
            }
        });

    }

    private boolean validateUsername() {
        String name = txtName.getText().toString().trim();

        if (name.isEmpty()) {
            txtName.setError("Field can't be empty");
            return false;
        } else if (!name.matches("[a-zA-Z ]+")) {
            txtName.requestFocus();
            txtName.setError("Enter only alphabetical character");
        } else if (name.length() > 15) {
            txtName.setError("Name too long");
            return false;
        } else if (name.length() < 5) {
            txtName.setError("Name too sort");
            return false;
        } else {
            txtName.setError(null);
            return true;
        }
        return true;
    }

    private boolean validateSkills() {
        String skills = txtSkills.getText().toString().trim();

        if (skills.isEmpty()) {
            txtSkills.setError("Field can't be empty");
            return false;
        } else if (!skills.matches("[a-zA-Z ]+")) {
            txtSkills.requestFocus();
            txtSkills.setError("Enter only alphabetical character");
        } else {
            txtSkills.setError(null);
            return true;
        }
        return true;
    }


    private boolean validateEducation() {
        String education = txtEducation.getText().toString().trim();

        if (education.isEmpty()) {
            txtEducation.setError("Field can't be empty");
            return false;
        } else if (!education.matches("[a-zA-Z ]+")) {
            txtEducation.requestFocus();
            txtEducation.setError("Enter only alphabetical character");
        } else {
            txtEducation.setError(null);
            return true;
        }
        return true;
    }


    public void   UpdateProfile(){

        if (!validateUsername() | !validateSkills()  | !validateEducation() ) {
            return;
        }


        dataRequest(
                txtName.getText().toString(),
                txtName2.getText().toString(),
                txtSkills.getText().toString(),
                txtEmail.getText().toString(),
                txtEducation.getText().toString());

    }

    private void dataRequest( final String name, final String username, final String skills, final String email,final String education) {


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        if(name.isEmpty()){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(skills.isEmpty()){
            Toast.makeText(this, "Enter Your Skills", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(education.isEmpty()){
            Toast.makeText(this, "Enter Your Education", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferenceManager pm = new PreferenceManager(AdminEditProfileActivity.this);
        StringRequest request = new StringRequest(Request.Method.PUT, "http://192.168.0.105:8080/api/admin/profile/update/"+name+"/"+skills+"/"+education+"/"+pm.getSignInData(PreferenceManager.USER_EMAIL_KEY)+"/"+pm.getSignInData(PreferenceManager.USER_PASSWORD_KEY),

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equals("Data Updated")) {

                            progressDialog.dismiss();

                            Intent intent = new Intent(AdminEditProfileActivity.this, AdminOwnProfileActivity.class);
                            Toast.makeText(AdminEditProfileActivity.this, " Data Updated!", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        }

                        else if (response.equals("Failed")) {

                            progressDialog.dismiss();
                            Toast.makeText(AdminEditProfileActivity.this, " Data Updated Fail!", Toast.LENGTH_SHORT).show();

                        } else {

                            progressDialog.dismiss();
                            Toast.makeText(AdminEditProfileActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminEditProfileActivity.this,"No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }

        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("UserName",name);
                params.put("Skills",skills);
                params.put("Education",education);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AdminEditProfileActivity.this);
        requestQueue.add(request);
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

            Intent intent=new Intent(AdminEditProfileActivity.this, AdminOwnProfileActivity.class);
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

