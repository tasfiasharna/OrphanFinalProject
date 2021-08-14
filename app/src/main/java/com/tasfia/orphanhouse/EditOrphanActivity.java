package com.tasfia.orphanhouse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
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
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
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

public class EditOrphanActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    EditText edId, edName, edAge, edHistory, edKind;
    ImageView imageView;
    private int position;
    Bitmap bitmap;
    String encodedImage=null;
    Button btnChooseImage;
    RadioGroup grpGender, grpDefect, grpMental, grpBlood, grpAgeCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orphan_edit);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

        edId = findViewById(R.id.ed_id);
        edName = findViewById(R.id.ed_name);
        edAge = findViewById(R.id.ed_age);
        edHistory = findViewById(R.id.ed_history);
        grpGender = findViewById(R.id.grpGender);
        grpDefect = findViewById(R.id.grpDefect);
        edKind = findViewById(R.id.ed_kind);
        grpMental = findViewById(R.id.grpMental);
        grpBlood = findViewById(R.id.grpBlood);
        grpAgeCategory = findViewById(R.id.grpAgeCategory);
        imageView = findViewById(R.id.ed_imageView);
        btnChooseImage = findViewById(R.id.edt_btnChooseImage);

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(EditOrphanActivity.this)
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

        final Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        edId.setText(ViewOrphanDetailsActivity.orphanArrayList.get(position).getId());
        edName.setText(ViewOrphanDetailsActivity.orphanArrayList.get(position).getName());
        edAge.setText(ViewOrphanDetailsActivity.orphanArrayList.get(position).getAge());
        edHistory.setText(ViewOrphanDetailsActivity.orphanArrayList.get(position).getHistory());

        edAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(validateAge()) {
                    int age = Integer.parseInt(edAge.getText().toString().trim());
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


        switch (ViewOrphanDetailsActivity.orphanArrayList.get(position).getGender()) {
            case "Male":
                ((RadioButton) grpGender.getChildAt(0)).setChecked(true);
                break;

            case "Female":
                ((RadioButton) grpGender.getChildAt(1)).setChecked(true);
                break;
        }


        switch (ViewOrphanDetailsActivity.orphanArrayList.get(position).getPhysicalDefect()) {
            case "Yes":
                ((RadioButton) grpDefect.getChildAt(0)).setChecked(true);
                break;

            case "No":
                ((RadioButton) grpDefect.getChildAt(1)).setChecked(true);
                break;
        }

        edKind.setText(ViewOrphanDetailsActivity.orphanArrayList.get(position).getWhatKindOfPhysicalDefect());

        int radioButtonID = grpDefect.getCheckedRadioButtonId();
        View radioButton = grpDefect.findViewById(radioButtonID);
        int selectedPosition = grpDefect.indexOfChild(radioButton);
        edKind.setVisibility(selectedPosition == 0 ? View.VISIBLE : View.GONE);

        grpDefect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.yes: edKind.setVisibility(View.VISIBLE); break;
                    case R.id.no: edKind.setVisibility(View.GONE); break;
                }
            }
        });

        switch (ViewOrphanDetailsActivity.orphanArrayList.get(position).getMentalStatus()) {
            case "Good":
                ((RadioButton) grpMental.getChildAt(0)).setChecked(true);
                break;

            case "Not Good":
                ((RadioButton) grpMental.getChildAt(1)).setChecked(true);
                break;
        }

        switch (ViewOrphanDetailsActivity.orphanArrayList.get(position).getBloodGroup()) {
            case "A+":
                ((RadioButton) grpBlood.getChildAt(0)).setChecked(true);
                break;

            case "A-":
                ((RadioButton) grpBlood.getChildAt(1)).setChecked(true);
                break;

            case "B+":
            ((RadioButton) grpBlood.getChildAt(2)).setChecked(true);
            break;

            case "B-":
                ((RadioButton) grpBlood.getChildAt(3)).setChecked(true);
                break;

            case "AB+":
                ((RadioButton) grpBlood.getChildAt(4)).setChecked(true);
                break;

            case "AB-":
                ((RadioButton) grpBlood.getChildAt(5)).setChecked(true);
                break;

            case "O+":
                ((RadioButton) grpBlood.getChildAt(6)).setChecked(true);
                break;

            case "O-":
                ((RadioButton) grpBlood.getChildAt(7)).setChecked(true);
                break;
        }

        switch (ViewOrphanDetailsActivity.orphanArrayList.get(position).getAgeCategory()) {
            case "3 to 8":
                ((RadioButton) grpAgeCategory.getChildAt(0)).setChecked(true);
                break;

            case "9 to 12":
                ((RadioButton) grpAgeCategory.getChildAt(1)).setChecked(true);
                break;

            case "13 to 15":
                ((RadioButton) grpAgeCategory.getChildAt(2)).setChecked(true);
                break;
        }

        encodedImage = ViewOrphanDetailsActivity.orphanArrayList.get(position).getImage();
        if(encodedImage != null){
            try{
                byte[] byteImage = Base64.decode(encodedImage, Base64.DEFAULT);
                Bitmap bmp =BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                imageView.setImageBitmap(bmp);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

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
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] imageBytes = stream.toByteArray();

        encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);

    }

    private boolean validateUsername() {
        String name = edName.getText().toString().trim();

        if (name.isEmpty()) {
            edName.setError("Field can't be empty");
            return false;
        } else if (!name.matches("[a-zA-Z ]+")) {
            edName.requestFocus();
            edName.setError("Enter only alphabetical character");
        } else if (name.length() > 15) {
            edName.setError("Name too long");
            return false;
        } else if (name.length() < 5) {
            edName.setError("Name too sort");
            return false;
        } else {
            edName.setError(null);
            return true;
        }
        return true;
    }

    private boolean validateAge() {
        String age = edAge.getText().toString().trim();

        if (age.isEmpty()) {
            edAge.setError("Field can't be empty");
            return false;
        } else if (Integer.parseInt(age) < 3 || Integer.parseInt(age) > 15) {
            edAge.requestFocus();
            edAge.setError("Not allowed");
            return false;
        } else {
            edAge.setError(null);
            return true;
        }
    }

    private boolean validateHistory() {
        String history = edHistory.getText().toString().trim();

        if (history.isEmpty()) {
            edHistory.setError("Field can't be empty");
            return false;
        } else if (history.length() < 3) {
            edHistory.setError("Please give history");
            return false;
        } else {
            edHistory.setError(null);
            return true;
        }
    }

        public void btn_updateData(View view) {

        if (!validateUsername() | !validateAge()| !validateHistory() ) {
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


        dataRequest(edId.getText().toString(),
                edName.getText().toString(),
                edAge.getText().toString(),
                edHistory.getText().toString(),
                edKind.getText().toString(),
                gender, defect, mental, blood, category,cat2Id);

    }

    private void dataRequest(final String id, final String name, final String age, final String history, final String kind, final String gender, final String defect, final String mental, final String blood, final String category, final String cat2Id) {


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

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
        else if(blood.isEmpty()){
            Toast.makeText(this, "Enter Blood Group", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(category.isEmpty()){
            Toast.makeText(this, "Enter Age Category", Toast.LENGTH_SHORT).show();
            return;
        }


        StringRequest request = new StringRequest(Request.Method.PUT, "http://192.168.0.105:8080/api/orphan/user/update",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(EditOrphanActivity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ViewOrphanDetailsActivity.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(EditOrphanActivity.this, "No Internet Connection !!!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("Id", id);
                params.put("Name", name);
                params.put("RealAge", age);
                params.put("History", history);
                params.put("Gender", gender);
                params.put("PhysicalDefect", defect);
                params.put("WhatKindoFPhysicalDefect", kind);
                params.put("MentalStatus", mental);
                params.put("AgeCategoryId", cat2Id);
                params.put("Uimg", encodedImage);
                params.put("Blood", blood);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EditOrphanActivity.this);
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

            Intent intent=new Intent(EditOrphanActivity.this, AdminDetailsActivity.class);
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


