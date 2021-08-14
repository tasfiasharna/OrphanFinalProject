package com.tasfia.orphanhouse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AddDonatorActivity extends AppCompatActivity {


    EditText txtName, txtAddress, txtCity, txtEmail, txtProfession, txtContactNo;
    Button btn_registration;
    RadioGroup grpGender;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donator);

        txtName = findViewById(R.id.edtName);
        grpGender = findViewById(R.id.grpGender);
        txtAddress = findViewById(R.id.edtAddress);
        txtCity = findViewById(R.id.edtCity);
        txtProfession = findViewById(R.id.edtProfession);
        txtContactNo = findViewById(R.id.edtPhone);
        txtEmail = findViewById(R.id.edtEmail);
        btn_registration = findViewById(R.id.btnRegistration);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Donor Registration");


        btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AddDonatorActivity.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Want to Registration?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                insertData();
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
        } else if (name.length() > 25) {
            txtName.setError("Name too long");
            return false;
        } else if (name.length() < 2) {
            txtName.setError("Name too sort");
            return false;
        } else {
            txtName.setError(null);
            return true;
        }
        return true;
    }


    private boolean validateAddress() {
        String address = txtAddress.getText().toString().trim();

        if (address.isEmpty()) {
            txtAddress.setError("Field can't be empty");
            return false;
        } else if (!address.matches("[a-zA-Z ]+")) {
            txtAddress.requestFocus();
            txtAddress.setError("Enter only alphabetical character");
        } else if (address.length() > 25) {
            txtAddress.setError("Address too long");
            return false;
        } else {
            txtAddress.setError(null);
            return true;
        }
        return true;
    }


    private boolean validateCity() {
        String city = txtCity.getText().toString().trim();

        if (city.isEmpty()) {
            txtCity.setError("Field can't be empty");
            return false;
        } else {
            txtCity.setError(null);
            return true;
        }
    }


    private boolean validateEmail() {
        String email = txtEmail.getText().toString().trim();

        if (email.isEmpty()) {
            txtEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError("Please enter a valid email address");
            return false;
        } else {
            txtEmail.setError(null);
            return true;
        }
    }


    private boolean validateProfession() {
        String profession = txtProfession.getText().toString().trim();

        if (profession.isEmpty()) {
            txtProfession.setError("Field can't be empty");
            return false;
        } else if (!profession.matches("[a-zA-Z ]+")) {
            txtProfession.requestFocus();
            txtProfession.setError("Enter only alphabetical character");
        } else if (profession.length() > 20) {
            txtProfession.setError("Profession too long");
            return false;
        } else {
            txtProfession.setError(null);
            return true;
        }
        return true;
    }


    private boolean validatePhone() {
        String phone = txtContactNo.getText().toString().trim();

        if (phone.isEmpty()) {
            txtContactNo.setError("Field can't be empty");
            return false;
        }
        else if (!(Pattern.matches("(018|014|012|013|015|017|016|019)[0-9]{8}+", phone))) {
            txtContactNo.requestFocus();
            txtContactNo.setError("Your contact number is incorrect");
            return false;
        } else if (!(phone.length() == 11)) {
            txtContactNo.requestFocus();
            txtContactNo.setError("Please enter valid contact number");
            return false;
        } else {
            txtContactNo.setError(null);
            return true;
        }
    }


    private void insertData() {

        if ( !validateUsername()  |!validateAddress() | !validateCity() | !validateEmail() | !validateProfession() | ! validatePhone() )  {
            return;
        }

        int selectedGender = grpGender.getCheckedRadioButtonId();
        String gender;
        if (selectedGender == R.id.female)
            gender = "Female";
        else
            gender = "Male";

        dataRequest( txtName.getText().toString(),
                txtAddress.getText().toString(),
                txtCity.getText().toString(),
                txtEmail.getText().toString(),
                txtProfession.getText().toString(),
                txtContactNo.getText().toString(),
                gender);
    }

    private void dataRequest(final String name, final String address,final String city, final String email,final String profession, final String phone, final String gender) {


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(name.isEmpty()){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(gender.isEmpty()){
            Toast.makeText(this, "Enter Gender", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(address.isEmpty()){
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(city.isEmpty()){
            Toast.makeText(this, "Enter Your City", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(profession.isEmpty()){
            Toast.makeText(this, "Enter Your Profession", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(phone.isEmpty()){
            Toast.makeText(this, "Enter Your Contact Number", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(email.isEmpty()){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.wait_icon);
//            loading.setTitle("Data Inserted");
            loading.setMessage("Please wait....");
            loading.show();
            StringRequest request = new StringRequest(Request.Method.POST,"http://192.168.0.105:8080/api/dreg/user/android/post/"+email+"/"+phone+"/"+name+"/"+gender+"/"+address+"/"+city+"/"+profession,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            if (response.equals("success")) {

                                Intent intent = new Intent(AddDonatorActivity.this, ViewDonorDetailsActivity.class);
                                Toast.makeText(AddDonatorActivity.this, " Registration Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }

                            else if (response.equals("failure")) {

                                loading.dismiss();
                                Toast.makeText(AddDonatorActivity.this, " Registration Fail!", Toast.LENGTH_SHORT).show();

                            } else {

                                loading.dismiss();
                                Toast.makeText(AddDonatorActivity.this, "Email or Contact No already exists!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddDonatorActivity.this, "No Internet Connection !!!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("Name",name);
                    params.put("Gender",gender);
                    params.put("Address",address);
                    params.put("City",city);
                    params.put("Profession",profession);
                    params.put("ContactNo",phone);
                    params.put("Email",email);

                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(AddDonatorActivity.this);
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
        super.onBackPressed();
        finish();
    }
}

