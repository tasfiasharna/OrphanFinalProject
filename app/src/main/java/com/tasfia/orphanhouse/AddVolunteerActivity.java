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
import android.os.Build;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class AddVolunteerActivity extends AppCompatActivity {


    EditText txtName,txtAddress,txtEmail,txtProfession,txtDate,txtContactNo,txtReason;

    Button btn_registration;
    Button btnChooseImage;
    ImageView imageView;
    Bitmap bitmap;
    String encodedImage=null;
    RadioGroup grpGender, grpListen,grpBlood;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_volunteer);

        txtName = findViewById(R.id.edtName);
        txtAddress = findViewById(R.id.edtAddress);
        txtDate= findViewById(R.id.edtDate);
        txtEmail = findViewById(R.id.edtEmail);
        txtProfession = findViewById(R.id.edtProfession);
        grpGender = findViewById(R.id.grpGender);
        txtContactNo = findViewById(R.id.edtPhone);
        grpListen = findViewById(R.id.grpListen);
        txtReason = findViewById(R.id.edtReason);
        grpBlood = findViewById(R.id.grpBlood);
        imageView = findViewById(R.id.imView);
        btnChooseImage = findViewById(R.id.btnChooseImage);
        btn_registration = findViewById(R.id.btnRegistration);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Volunteer Registration");

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(AddVolunteerActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();


            }
        });

        txtDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setDate(txtDate);
                }
                return false;
            }
        });


        btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AddVolunteerActivity.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK && data!=null){

            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

                imageStore(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void imageStore(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);

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
        } else if (profession.length() > 25) {
            txtProfession.setError("Profession too long");
            return false;
        } else {
            txtProfession.setError(null);
            return true;
        }
        return true;
    }

    private boolean validateDate() {
        String date = txtDate.getText().toString().trim();

        if (date.isEmpty()) {
            txtDate.setError("Field can't be empty");
            return false;
        } else {
            txtDate.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String phone = txtContactNo.getText().toString().trim();

        if (phone.isEmpty()) {
            txtContactNo.setError("Field can't be empty");
            return false;
        } else if (!(Pattern.matches("(018|014|012|013|015|017|016|019)[0-9]{8}+", phone))) {
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

    private boolean validateReason() {
        String reason = txtReason.getText().toString().trim();

        if (reason.isEmpty()) {
            txtReason.setError("Field can't be empty");
            return false;
        } else if (!reason.matches("[a-zA-Z ]+")) {
            txtReason.requestFocus();
            txtReason.setError("Enter only alphabetical character");
        } else if (reason.length() > 30) {
            txtReason.setError("Reason too long");
            return false;
        } else {
            txtReason.setError(null);
            return true;
        }
        return true;
    }

    private void setDate(final EditText date_Input) {
        final DatePicker datePicker = new DatePicker(AddVolunteerActivity.this);
        final int currentDay = datePicker.getDayOfMonth();
        final int currentMonth = datePicker.getMonth();
        final int currentYear = datePicker.getYear();

        int theme =  Build.VERSION.SDK_INT < 23 ? AlertDialog.THEME_HOLO_DARK : android.R.style.Theme_Holo_Dialog;

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddVolunteerActivity.this, theme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                date_Input.setText(date);
            }
        }, currentYear, currentMonth, currentDay);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }

    private void insertData() {

        if ( !validateUsername()  |!validateAddress() | !validateEmail() | !validateProfession() | !validateDate()| ! validatePhone() | !validateReason())  {
            return;
        }

        int selectedGender = grpGender.getCheckedRadioButtonId();
        String gender;
        if (selectedGender == R.id.female)
            gender = "Female";
        else
            gender = "Male";

        int selectedListen = grpListen.getCheckedRadioButtonId();
        String listen;
        if (selectedListen == R.id.facebook)
            listen = "Facebook";
        else if (selectedListen == R.id.friends)
            listen = "Friends";
        else if (selectedListen == R.id.newsPaper)
            listen = "NewsPaper";
        else
            listen = "Others";

        int selectedBlood = grpBlood.getCheckedRadioButtonId();
        String blood;
        if (selectedBlood == R.id.aPositive)
            blood = "A+";
        else if (selectedBlood == R.id.aNegative)
            blood = "A-";
        else if (selectedBlood == R.id.bPositive)
            blood = "B+";
        else if (selectedBlood == R.id.bNegative)
            blood = "B-";
        else if (selectedBlood == R.id.abPositive)
            blood = "AB+";
        else if (selectedBlood == R.id.abNegative)
            blood = "AB-";
        else if (selectedBlood == R.id.oPositive)
            blood = "O+";
        else
            blood = "O-";


        dataRequest( txtName.getText().toString(),
                txtAddress.getText().toString(),
                txtEmail.getText().toString(),
                txtProfession.getText().toString(),
                txtDate.getText().toString(),
                txtContactNo.getText().toString(),
                txtReason.getText().toString(),
                gender,listen,blood);

    }
    private void dataRequest(final String name, final String address, final String email,final String profession, final String date, final String phone, final String reason,final String gender,final String listen,final String blood) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(name.isEmpty()){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(address.isEmpty()){
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(email.isEmpty()){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(profession.isEmpty()){
            Toast.makeText(this, "Enter Your Profession", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(date.isEmpty()){
            Toast.makeText(this, "Enter Your Date Of Birth", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(phone.isEmpty()){
            Toast.makeText(this, "Enter Your Contact Number", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(reason.isEmpty()){
            Toast.makeText(this, "Enter the Reason of Your Volunteering", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(gender.isEmpty()){
            Toast.makeText(this, "Enter Gender", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(listen.isEmpty()){
            Toast.makeText(this, "From Where You Listen About Us?", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(blood.isEmpty()){
            Toast.makeText(this, "Enter Blood Group", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(encodedImage == null){
            Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.wait_icon);
//            loading.setTitle("Data Inserted");
            loading.setMessage("Please wait....");
            loading.show();

            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.105:8080/api/volunteer/admin",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.equals("Registration Successful")) {

                                loading.dismiss();

                                Intent intent = new Intent(AddVolunteerActivity.this, AdminDetailsActivity.class);
                                Toast.makeText(AddVolunteerActivity.this, " Registration Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }

                            else if (response.equals("Failed")) {

                                loading.dismiss();
                                Toast.makeText(AddVolunteerActivity.this, " Registration Fail!", Toast.LENGTH_SHORT).show();

                            } else {

                                loading.dismiss();
                                Toast.makeText(AddVolunteerActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddVolunteerActivity.this, "No Internet Connection !!!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("VName",name);
                    params.put("Address",address);
                    params.put("DateOfBirth",date);
                    params.put("Email",email);
                    params.put("Profession",profession);
                    params.put("Gender",gender);
                    params.put("ContactNo",phone);
                    params.put("FromWhereYouLearnAboutUs",listen);
                    params.put("TheReasonOfYourVolunteering",reason);
                    params.put("Uimg",encodedImage);
                    params.put("Blood",blood);

                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(AddVolunteerActivity.this);
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



