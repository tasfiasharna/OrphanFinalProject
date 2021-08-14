package com.tasfia.orphanhouse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

public class EditVolunteerActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    EditText edId, edName, edAddress, edEmail, edProfession,  edDate, edContactNo, edReason;
    ImageView imageView;
    private int position;
    Bitmap bitmap;
    String encodedImage=null;
    Button btnChooseImage;
    RadioGroup grpGender, grpListen,grpBlood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_volunteer);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

        edId = findViewById(R.id.ed_id);
        edName = findViewById(R.id.ed_name);
        edAddress = findViewById(R.id.ed_address);
        edEmail = findViewById(R.id.ed_email);
        edProfession = findViewById(R.id.ed_profession);
        edDate= findViewById(R.id.ed_date);
        edContactNo = findViewById(R.id.ed_contactNo);
        edReason = findViewById(R.id.ed_reason);
        grpGender = findViewById(R.id.grpGender);
        grpListen = findViewById(R.id.grpListen);
        grpBlood = findViewById(R.id.grpBlood);
        imageView = findViewById(R.id.imView);
        btnChooseImage = findViewById(R.id.btnChooseImage);

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(EditVolunteerActivity.this)
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

        edDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setDate(edDate);
                }
                return false;
            }
        });


        final Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        edId.setText(ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getId());
        edName.setText(ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getName());
        edAddress.setText(ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getAddress());
        edEmail.setText(ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getEmail());
        edProfession.setText(ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getProfession());
        edDate.setText(ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getDate());
        edContactNo.setText(ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getContactNo());
        edReason.setText(ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getTheReasonOfYourVolunteering());


        switch (ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getGender()) {
            case "Male":
                ((RadioButton) grpGender.getChildAt(0)).setChecked(true);
                break;

            case "Female":
                ((RadioButton) grpGender.getChildAt(1)).setChecked(true);
                break;
        }


        switch (ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getFromWhereYouListenAboutUs()) {
            case "Facebook":
                ((RadioButton) grpListen.getChildAt(0)).setChecked(true);
                break;

            case "Friends":
                ((RadioButton) grpListen.getChildAt(1)).setChecked(true);
                break;

            case "NewsPaper":
                ((RadioButton) grpListen.getChildAt(2)).setChecked(true);
                break;

            case "Others":
                ((RadioButton) grpListen.getChildAt(3)).setChecked(true);
                break;
        }


        switch (ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getBloodGroup()) {
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

        encodedImage = ViewVolunteerDetailsActivity.volunteerArrayList.get(position).getImage();
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
        } else if (name.length() > 25) {
            edName.setError("Name too long");
            return false;
        } else if (name.length() < 3) {
            edName.setError("Name too sort");
            return false;
        } else {
            edName.setError(null);
            return true;
        }
        return true;
    }

    private boolean validateAddress() {
        String address = edAddress.getText().toString().trim();

        if (address.isEmpty()) {
            edAddress.setError("Field can't be empty");
            return false;
        } else if (!address.matches("[a-zA-Z ]+")) {
            edAddress.requestFocus();
            edAddress.setError("Enter only alphabetical character");
        } else if (address.length() > 25) {
            edAddress.setError("Address too long");
            return false;
        } else {
            edAddress.setError(null);
            return true;
        }
        return true;
    }

    private boolean validateEmail() {
        String email = edEmail.getText().toString().trim();

        if (email.isEmpty()) {
            edEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.setError("Please enter a valid email address");
            return false;
        } else {
            edEmail.setError(null);
            return true;
        }
    }

    private boolean validateProfession() {
        String profession = edProfession.getText().toString().trim();

        if (profession.isEmpty()) {
            edProfession.setError("Field can't be empty");
            return false;
        } else if (!profession.matches("[a-zA-Z ]+")) {
            edProfession.requestFocus();
            edProfession.setError("Enter only alphabetical character");
        } else if (profession.length() > 25) {
            edProfession.setError("Profession too long");
            return false;
        } else {
            edProfession.setError(null);
            return true;
        }
        return true;
    }

    private boolean validateDate() {
        String date = edDate.getText().toString().trim();

        if (date.isEmpty()) {
            edDate.setError("Field can't be empty");
            return false;
        } else {
            edDate.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String phone = edContactNo.getText().toString().trim();

        if (phone.isEmpty()) {
            edContactNo.setError("Field can't be empty");
            return false;
        } else if (!(Pattern.matches("(018|014|012|013|015|017|016|019)[0-9]{8}+", phone))) {
            edContactNo.requestFocus();
            edContactNo.setError("Please enter valid contact number");
            return false;
        } else if (!(phone.length() == 11)) {
            edContactNo.requestFocus();
            edContactNo.setError("Please enter valid contact number");
            return false;
        }
        else {
            edContactNo.setError(null);
            return true;
        }
    }

    private boolean validateReason() {
        String reason = edReason.getText().toString().trim();

        if (reason.isEmpty()) {
            edReason.setError("Field can't be empty");
            return false;
        } else if (!reason.matches("[a-zA-Z ]+")) {
            edReason.requestFocus();
            edReason.setError("Enter only alphabetical character");
        } else if (reason.length() > 30) {
            edReason.setError("Reason too long");
            return false;
        } else {
            edReason.setError(null);
            return true;
        }
        return true;
    }


    private void setDate(final EditText date_Input) {
        final DatePicker datePicker = new DatePicker(EditVolunteerActivity.this);
        final int currentDay = datePicker.getDayOfMonth();
        final int currentMonth = datePicker.getMonth();
        final int currentYear = datePicker.getYear();

//        DatePickerDialog datePickerDialog = new DatePickerDialog(EditVolunteerActivity.this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
//                String date = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
//                date_Input.setText(date);
//            }
//        }, currentYear, currentMonth, currentDay);
////////////
        int theme =  Build.VERSION.SDK_INT < 23 ? AlertDialog.THEME_HOLO_DARK : android.R.style.Theme_Holo_Dialog;

        DatePickerDialog datePickerDialog = new DatePickerDialog(EditVolunteerActivity.this, theme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                date_Input.setText(date);
            }
        }, currentYear, currentMonth, currentDay);
////////////////
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }

    public void btn_updateData(View view) {

        if ( !validateUsername() | !validateAddress() | !validateEmail() | !validateProfession() | !validateDate()| ! validatePhone() | !validateReason())  {
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


        dataRequest( edId.getText().toString(),
                edName.getText().toString(),
                edAddress.getText().toString(),
                edEmail.getText().toString(),
                edProfession.getText().toString(),
                edDate.getText().toString(),
                edContactNo.getText().toString(),
                edReason.getText().toString(),
                gender,listen,blood);

    }

    private void dataRequest(final String id, final String name, final String address, final String email,final String profession, final String date, final String phone, final String reason,final String gender,final String listen,final String blood) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

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
            Toast.makeText(this, "Enter Date Of Birth", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(gender.isEmpty()){
            Toast.makeText(this, "Enter Gender", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(phone.isEmpty()){
            Toast.makeText(this, "Enter Your Contact Number", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(listen.isEmpty()){
            Toast.makeText(this, "From Where You Listen About Us?", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(reason.isEmpty()){
            Toast.makeText(this, "Enter the Reason of Your Volunteering", Toast.LENGTH_SHORT).show();
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


        StringRequest request = new StringRequest(Request.Method.PUT, "http://192.168.0.105:8080/api/volunteer/user/update",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(EditVolunteerActivity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ViewVolunteerDetailsActivity.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(EditVolunteerActivity.this, "No Internet Connection !!!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("VId",id);
                params.put("VName",name);
                params.put("Address",address);
                params.put("Email",email);
                params.put("Gender",gender);
                params.put("DateOfBirth",date);
                params.put("ContactNo",phone);
                params.put("Profession",profession);
                params.put("FromWhereYouLearnAboutUs",listen);
                params.put("TheReasonOfYourVolunteering",reason);
                params.put("Uimg",encodedImage);
                params.put("Blood",blood);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EditVolunteerActivity.this);
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

            Intent intent=new Intent(EditVolunteerActivity.this, AdminDetailsActivity.class);
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
