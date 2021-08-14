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

public class AdoptionFormActivity extends AppCompatActivity {


    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    RadioGroup grpGender, grpChildren, grpAdopt, grpBlood;
    EditText etxtName, etxtPhone, etxtEmail, etxtPresentAddress, etxtPermanentAddress, etxtProfession, etxtSalary, etxtNID, etxtReason;
    Button btnSubmit;
    Button btnImage;
    ImageView imageView;
    Bitmap bitmap;

    String encodedImage = null;
    private ProgressDialog loading;

    String url = "https://orphanbd.000webhostapp.com/adopt.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_form);

        etxtName = (EditText) findViewById(R.id.etxt_name);
        etxtPhone = (EditText) findViewById(R.id.etxt_phone);
        etxtEmail = (EditText) findViewById(R.id.etxt_email);
        etxtPresentAddress = (EditText) findViewById(R.id.etxt_presentAddress);
        etxtPermanentAddress = (EditText) findViewById(R.id.etxt_permanentAddress);
        etxtProfession = (EditText) findViewById(R.id.etxt_profession);
        etxtSalary = (EditText) findViewById(R.id.etxt_salary);
        etxtNID = (EditText) findViewById(R.id.etxt_nid);
        etxtReason = (EditText) findViewById(R.id.etxt_reason);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnImage = (Button) findViewById(R.id.btnChooseImage);
        grpGender = (RadioGroup) findViewById(R.id.grpGender);
        grpChildren = (RadioGroup) findViewById(R.id.grpChildren);
        grpAdopt = (RadioGroup) findViewById(R.id.grpAdopt);
        grpBlood = (RadioGroup)findViewById(R.id.grpBlood);
        imageView = findViewById(R.id.imageView);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(AdoptionFormActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), 1);
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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AdoptionFormActivity.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Want to Submit Adopt Form?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                SaveAdoptForm();
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

    private boolean validateName() {
        String name = etxtName.getText().toString().trim();

        if (name.isEmpty()) {
            etxtName.setError("Field can't be empty");
            return false;
        } else if (!name.matches("[a-zA-Z ]+")) {
            etxtName.requestFocus();
            etxtName.setError("Enter only alphabetical character");
        } else if (name.length() > 15) {
            etxtName.setError("Name too long");
            return false;
        } else if (name.length() < 5) {
            etxtName.setError("Name too sort");
            return false;
        } else {
            etxtName.setError(null);
            return true;
        }
        return true;
    }

    private boolean validateEmail() {
        String email = etxtEmail.getText().toString().trim();

        if (email.isEmpty()) {
            etxtEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etxtEmail.setError("Please enter a valid email address");
            return false;
        } else {
            etxtEmail.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String phone = etxtPhone.getText().toString().trim();

        if (phone.isEmpty()) {
            etxtPhone.setError("Field can't be empty");
            return false;
        } else if (!(Pattern.matches("(018|014|012|013|015|017|016|019)[0-9]{8}+", phone))) {
            etxtPhone.requestFocus();
            etxtPhone.setError("Please enter valid number");
            return false;
        } else if (!(phone.length() == 11)) {
            etxtPhone.requestFocus();
            etxtPhone.setError("Please enter valid number");
            return false;
        } else {
            etxtPhone.setError(null);
            return true;
        }

    }

    private boolean validatePresentAddress() {
        String presentAddress = etxtPresentAddress.getText().toString().trim();

        if (presentAddress.isEmpty()) {
            etxtPresentAddress.setError("Field can't be empty");
            return false;
        } else if (presentAddress.length() > 15) {
            etxtPresentAddress.setError("Address too long");
            return false;
        } else {
            etxtPresentAddress.setError(null);
            return true;
        }

    }

    private boolean validatePermanentAddress() {
        String permanentAddress = etxtPermanentAddress.getText().toString().trim();

        if (permanentAddress.isEmpty()) {
            etxtPermanentAddress.setError("Field can't be empty");
            return false;
        } else if (permanentAddress.length() > 15) {
            etxtPermanentAddress.setError("Address too long");
            return false;
        } else {
            etxtPermanentAddress.setError(null);
            return true;
        }

    }

    private boolean validateProfession() {
        String profession = etxtProfession.getText().toString().trim();

        if (profession.isEmpty()) {
            etxtProfession.setError("Field can't be empty");
            return false;
        } else if (!profession.matches("[a-zA-Z ]+")) {
            etxtProfession.requestFocus();
            etxtProfession.setError("Enter only alphabetical character");
        } else if (profession.length() > 20) {
            etxtProfession.setError("Profession too long");
            return false;
        } else {
            etxtProfession.setError(null);
            return true;
        }
        return true;
    }

    private boolean validateSalary() {
        String salary = etxtSalary.getText().toString().trim();

        if (salary.isEmpty()) {
            etxtSalary.setError("Field can't be empty");
            return false;
        } else if (salary.length() < 5) {
            etxtSalary.setError("Sorry! this salary not enough for adopt child");
            return false;
        }
        else {
            etxtSalary.setError(null);
            return true;
        }
    }

    private boolean validateReason() {
        String reason = etxtReason.getText().toString().trim();

        if (reason.isEmpty()) {
            etxtReason.setError("Field can't be empty");
            return false;
        } else if (reason.length() < 5) {
            etxtReason.setError("Please mention your Reason");
            return false;
        } else {
            etxtReason.setError(null);
            return true;
        }
    }

    private boolean validateNID() {
        String nid = etxtNID.getText().toString().trim();

        if (nid.isEmpty()) {
            etxtNID.setError("Field can't be empty");
            return false;
        } else if (!(nid.length() == 13)) {
            etxtNID.setError("Please give your NID number");
            return false;
        } else {
            etxtReason.setError(null);
            return true;
        }
    }

    public void  SaveAdoptForm() {

        if ( !validateName() | !validateEmail() | !validatePhone() | !validatePresentAddress() | !validatePermanentAddress()| !validateProfession() | !validateSalary() | !validateReason() |!validateNID() ) {
            return;
        }

        int selectedGender = grpGender.getCheckedRadioButtonId();
        String gender;
        if (selectedGender == R.id.female)
            gender = "Female";
        else
            gender = "Male";


        int selectedChildren = grpChildren.getCheckedRadioButtonId();
        String children;
        if (selectedChildren == R.id.one)
            children = "One";
        else if (selectedChildren == R.id.two)
            children = "Two";
        else if (selectedChildren == R.id.more)
            children = "More Than Two";
        else
            children = "None";

        int selectedAdopt = grpAdopt.getCheckedRadioButtonId();
        String adopt;
        if (selectedAdopt == R.id.yes)
            adopt = "Yes";
        else
            adopt = "No";

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

        submitRequest( etxtName.getText().toString(),
                etxtPhone.getText().toString(),
                etxtEmail.getText().toString(),
                etxtPresentAddress.getText().toString(),
                etxtPermanentAddress.getText().toString(),
                etxtProfession.getText().toString(),
                etxtSalary.getText().toString(),
                etxtNID.getText().toString(),
                blood,
                etxtReason.getText().toString(),
                gender,children,adopt);

    }
    private void submitRequest(final String name, final String phone, final String email, final String presentAddress, final String permanentAddress,final String profession, final String salary,final String nid,final String blood,final String reason,final String gender,final String children,final String adopt  ) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if (name.isEmpty()) {
            Toast.makeText(this, "Name Can't Empty", Toast.LENGTH_SHORT).show();
        } else if (phone.isEmpty()) {
            Toast.makeText(this, "Phone Can't Empty", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()) {
            Toast.makeText(this, "Email Can't Empty", Toast.LENGTH_SHORT).show();
        } else if (presentAddress.isEmpty()) {
            Toast.makeText(this, "Present Address Can't Empty", Toast.LENGTH_SHORT).show();
        } else if (permanentAddress.isEmpty()) {
            Toast.makeText(this, "Permanent Address Can't Empty", Toast.LENGTH_SHORT).show();
        } else if (profession.isEmpty()) {
            Toast.makeText(this, "Profession Can't Empty", Toast.LENGTH_SHORT).show();
        } else if (salary.isEmpty()) {
            Toast.makeText(this, "Salary Can't Empty", Toast.LENGTH_SHORT).show();
        } else if (nid.isEmpty()) {
            Toast.makeText(this, "NID Can't Empty", Toast.LENGTH_SHORT).show();
        } else if (blood.isEmpty()) {
            Toast.makeText(this, "Blood group Can't Empty", Toast.LENGTH_SHORT).show();
        } else if (reason.isEmpty()) {
            Toast.makeText(this, "Reason Can't Empty", Toast.LENGTH_SHORT).show();
        } else if (gender.isEmpty()) {
            Toast.makeText(this, "Please choose gender", Toast.LENGTH_SHORT).show();
        } else if (children.isEmpty()) {
            Toast.makeText(this, "Please choose from here", Toast.LENGTH_SHORT).show();
        } else if (adopt.isEmpty()) {
            Toast.makeText(this, "Please choose from here", Toast.LENGTH_SHORT).show();
        }else if(encodedImage == null){
            Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.wait_icon);
//            loading.setTitle("Submit");
            loading.setMessage("Please wait....");
            loading.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.trim().equalsIgnoreCase("success")) {

                                loading.dismiss();

                                Intent intent = new Intent(AdoptionFormActivity.this, HomeActivity.class);
                                Toast.makeText(AdoptionFormActivity.this, " Successfully Submitted!", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }

                            else if (response.trim().equalsIgnoreCase("failure")) {

                                loading.dismiss();
                                Toast.makeText(AdoptionFormActivity.this, " Submit fail!", Toast.LENGTH_SHORT).show();


                            } else {

                                loading.dismiss();
                                Toast.makeText(AdoptionFormActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(AdoptionFormActivity.this, "No Internet Connection !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }
            ){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    params.put("AName", name);
                    params.put("Gender", gender);
                    params.put("Email", email);
                    params.put("Phone", phone);
                    params.put("PresentAddress", presentAddress);
                    params.put("ParmanentAddress", permanentAddress);
                    params.put("Proffession", profession);
                    params.put("MonthlySalary", salary);
                    params.put("NID", nid);
                    params.put("Blood", blood);
                    params.put("Children", children);
                    params.put("Uimg", encodedImage);
                    params.put("Reason", reason);
                    params.put("AdoptAnyChild", adopt);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(AdoptionFormActivity.this);
            requestQueue.add(stringRequest);
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

            Intent intent=new Intent(AdoptionFormActivity.this, HomeActivity.class);
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


