package com.tasfia.orphanhouse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AddOrphanActivity extends AppCompatActivity {


    EditText txtName, txtAge, txtHistory,  txtKind;
    Button btn_insert;
    Button btnChooseImage;
    ImageView imageView;
    Bitmap bitmap;
    String encodedImage=null;
    RadioGroup grpGender, grpDefect,grpMental,grpBlood, grpAgeCategory;
    private ProgressDialog loading;

    String url = "http://192.168.0.105:8080/api/orphan/user/android/post/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_orphan);

        txtName = findViewById(R.id.edtName);
        txtAge = findViewById(R.id.edtAge);
        txtHistory = findViewById(R.id.edtHistory);
        grpGender = findViewById(R.id.grpGender);
        grpDefect = findViewById(R.id.grpDefect);
        txtKind = findViewById(R.id.edtKind);
        grpMental = findViewById(R.id.grpMental);
        grpBlood = findViewById(R.id.grpBlood);
        grpAgeCategory = findViewById(R.id.grpAgeCategory);
        btnChooseImage = findViewById(R.id.btnChooseImage);
        btn_insert = findViewById(R.id.btnInsert);
        imageView = findViewById(R.id.imView);

        txtAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(validateAge()) {
                    int age = Integer.parseInt(txtAge.getText().toString().trim());
                    if(age >= 3 && age <= 8){
                        ((RadioButton) grpAgeCategory.getChildAt(0)).setChecked(true);
                    }
                    else if(age >= 9 && age <= 12){
                        ((RadioButton) grpAgeCategory.getChildAt(1)).setChecked(true);
                    }
                    else {
                        ((RadioButton) grpAgeCategory.getChildAt(2)).setChecked(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Orphan");

        int radioButtonID = grpDefect.getCheckedRadioButtonId();
        View radioButton = grpDefect.findViewById(radioButtonID);
        int selectedPosition = grpDefect.indexOfChild(radioButton);
        txtKind.setVisibility(selectedPosition == 0 ? View.VISIBLE : View.GONE);

        grpDefect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.yes: txtKind.setVisibility(View.VISIBLE); break;
                    case R.id.no: txtKind.setVisibility(View.GONE); break;
                }
            }
        });


        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(AddOrphanActivity.this)
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


        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AddOrphanActivity.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Want to Save Orphan?")
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


    private boolean validateAge() {
        String age = txtAge.getText().toString().trim();

        if (TextUtils.isEmpty(age)) {
            txtAge.setError("Field can't be empty");
            return false;
        } else if (Integer.parseInt(age) < 3 || Integer.parseInt(age) > 15) {
            txtAge.requestFocus();
            txtAge.setError("Not allowed");
            return false;
        } else {
            txtAge.setError(null);
            return true;
        }
    }


    private boolean validateHistory() {
        String history = txtHistory.getText().toString().trim();

        if (history.isEmpty()) {
            txtHistory.setError("Field can't be empty");
            return false;
        } else if (history.length() < 3) {
            txtHistory.setError("Please give history");
            return false;
        } else {
            txtHistory.setError(null);
            return true;
        }
    }


    private void insertData() {

        if ( !validateUsername() | !validateAge() |  !validateHistory()  ) {
            return;
        }

        int selectedGender = grpGender.getCheckedRadioButtonId();
        String gender;
        if (selectedGender == R.id.female)
            gender = "Female";
        else
            gender = "Male";

        int selectedDefect = grpDefect.getCheckedRadioButtonId();
        String defect;
        if (selectedDefect == R.id.yes)
            defect = "Yes";
        else
            defect = "No";

        int selectedMental = grpMental.getCheckedRadioButtonId();
        String mental;
        if (selectedMental == R.id.yes)
            mental = "Good";
        else
            mental = "Not Good";

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

        int selectedCategory = grpAgeCategory.getCheckedRadioButtonId();
        String category;
       String cat2Id;
        if (selectedCategory == R.id.junior) {
            category = "3 to 8";
            cat2Id = "1";
        }else if (selectedCategory == R.id.medium) {
            category = "9 to 12";
            cat2Id = "2";
        }else {
            category = "13 t0 15";
            cat2Id = "3";
        }


        dataRequest( txtName.getText().toString(),
                txtAge.getText().toString(),
                txtHistory.getText().toString(),
                txtKind.getText().toString(),

                gender,defect,mental,blood,category,cat2Id);

    }
    private void dataRequest(final String name, final String age, final String history, final String kind, final String gender, final String defect,final String mental, final String blood,final String category, final String cat2Id) {


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(name.isEmpty()){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(age.isEmpty()){
            Toast.makeText(this, "Enter Age", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(history.isEmpty()){
            Toast.makeText(this, "Enter History", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(gender.isEmpty()){
            Toast.makeText(this, "Enter Gender", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(defect.isEmpty()){
            Toast.makeText(this, "Enter Physical Defect", Toast.LENGTH_SHORT).show();
            return;
        }

        else if(mental.isEmpty()){
            Toast.makeText(this, "Enter Mental Status", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(blood.isEmpty()){
            Toast.makeText(this, "Enter Blood Group", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(category.isEmpty()){
            Toast.makeText(this, "Enter Age Category", Toast.LENGTH_SHORT).show();
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
            StringRequest request = new StringRequest(Request.Method.POST, url,

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.equals("Data Inserted")) {
                                loading.dismiss();

                                Intent intent = new Intent(AddOrphanActivity.this, ViewOrphanDetailsActivity.class);
                                Toast.makeText(AddOrphanActivity.this, " Data Inserted!", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            } else if (response.equals("failure")) {

                                loading.dismiss();
                                Toast.makeText(AddOrphanActivity.this, " Data Inserted Fail!", Toast.LENGTH_SHORT).show();

                            } else {

                                loading.dismiss();
                                Toast.makeText(AddOrphanActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                            }

                        }
                    },
                    new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AddOrphanActivity.this,"No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("Name",name);
                    params.put("RealAge",age);
                    params.put("History",history);
                    params.put("Gender",gender);
                    params.put("PhysicalDefect",defect);
                    params.put("WhatKindoFPhysicalDefect",kind);
                    params.put("MentalStatus",mental);
                    params.put("AgeCategoryId",cat2Id);
                    params.put("Uimg",encodedImage);
                    params.put("Blood",blood);

                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(AddOrphanActivity.this);
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

