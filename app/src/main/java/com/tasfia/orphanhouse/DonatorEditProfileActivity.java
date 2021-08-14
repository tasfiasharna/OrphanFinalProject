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
import android.text.TextUtils;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class DonatorEditProfileActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    EditText txtName, txtCell, txtLocation, txtEmail, txtProfession,txtCity;
    TextView txtName2, txtUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donator_edit_profile);

        txtName = findViewById(R.id.txt_name);
        txtCell = findViewById(R.id.txt_cell);
        txtLocation = findViewById(R.id.txt_location);
        txtName2 = findViewById(R.id.txt_name2);
        txtProfession=findViewById(R.id.txt_profession);
        txtEmail = findViewById(R.id.txt_email);
        txtUpdate = findViewById(R.id.txt_update);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Profile");

        String getName=getIntent().getExtras().getString("Name");
        String getEmail=getIntent().getExtras().getString("Email");
        String getUser=getIntent().getExtras().getString("UserName");
        String getLocation=getIntent().getExtras().getString("Address");
        String getCell=getIntent().getExtras().getString("ContactNo");
        String getProfession=getIntent().getExtras().getString("Profession");

        txtName.setText(getName);
        txtEmail.setText(getEmail);
        txtEmail.setEnabled(false);
        txtName2.setText(getUser);
        txtLocation.setText(getLocation);
        txtCell.setText(getCell);
        txtProfession.setText(getProfession);

        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] cityList={"Dhaka","Chittagong","Sylhet","Rajshahi","Barishal","Khulna","Rangpur","Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(DonatorEditProfileActivity.this);
                builder.setTitle("SELECT DIVISION");
                builder.setIcon(R.drawable.ic_location);

                builder.setCancelable(false);
                builder.setItems(cityList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                txtLocation.setText("Dhaka");
                                break;

                            case 1:

                                txtLocation.setText("Chittagong");
                                break;

                            case 2:

                                txtLocation.setText("Sylhet");
                                break;

                            case 3:

                                txtLocation.setText("Rajshahi");
                                break;

                            case 4:

                                txtLocation.setText("Barishal");
                                break;

                            case 5:

                                txtLocation.setText("Khulna");
                                break;

                            case 6:

                                txtLocation.setText("Rangpur");
                                break;

                            case 7:

                                txtLocation.setText("Mymensingh");
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                    }
                });

                AlertDialog locationTypeDialog = builder.create();
                locationTypeDialog.show();
            }
        });

        txtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DonatorEditProfileActivity.this);
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
        String address = txtLocation.getText().toString().trim();

        if (address.isEmpty()) {
            txtLocation.setError("Field can't be empty");
            return false;
        } else if (!address.matches("[a-zA-Z ]+")) {
            txtLocation.requestFocus();
            txtLocation.setError("Enter only alphabetical character");
        } else if (address.length() > 25) {
            txtLocation.setError("Address too long");
            return false;
        } else {
            txtLocation.setError(null);
            return true;
        }
        return true;
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

    private boolean validatePhone() {
        String phone = txtCell.getText().toString().trim();

        if (phone.isEmpty()) {
            txtCell.setError("Field can't be empty");
            return false;
        } else if (!(Pattern.matches("(018|014|012|013|015|017|016|019)[0-9]{8}+", phone))) {
            txtCell.requestFocus();
            txtCell.setError("Please enter valid contact number");
            return false;
        } else if (!(phone.length() == 11)) {
            txtCell.requestFocus();
            txtCell.setError("Please enter valid contact number");
            return false;
        }
        else {
            txtCell.setError(null);
            return true;
        }
    }

    public void   UpdateProfile(){

        if (!validateUsername() | !validateAddress()  | !validateProfession() | !validatePhone()) {
            return;
        }

        dataRequest(
                txtName.getText().toString(),
                txtName2.getText().toString(),
                txtLocation.getText().toString(),
                txtEmail.getText().toString(),
                txtProfession.getText().toString(),
                txtCell.getText().toString());

    }

    private void dataRequest( final String name, final String username, final String address, final String email,final String profession, final String phone) {

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
        else if(profession.isEmpty()){
            Toast.makeText(this, "Enter Your Profession", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(phone.isEmpty()){
            Toast.makeText(this, "Enter Your Contact Number", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferenceManager pm = new PreferenceManager(DonatorEditProfileActivity.this);
        StringRequest request = new StringRequest(Request.Method.PUT, "http://192.168.0.105:8080/api/donator/profile/update/"+name+"/"+address+"/"+email+"/"+phone+"/"+profession+"/"+pm.getSignInData(PreferenceManager.USER_NAME_KEY)+"/"+pm.getSignInData(PreferenceManager.USER_PASSWORD_KEY),

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equals("Data Updated")) {

                            progressDialog.dismiss();

                            Intent intent = new Intent(DonatorEditProfileActivity.this, DonatorOwnProfileActivity.class);
                            Toast.makeText(DonatorEditProfileActivity.this, " Data Updated!", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        }
                        else if (response.equals("Failed")) {

                            progressDialog.dismiss();
                            Toast.makeText(DonatorEditProfileActivity.this, " Data Updated Fail!", Toast.LENGTH_SHORT).show();

                        } else {

                            progressDialog.dismiss();
                            Toast.makeText(DonatorEditProfileActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DonatorEditProfileActivity.this,"No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }

        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("Name",name);
                params.put("UserName",username);
                params.put("Address",address);
                params.put("Email",email);
                params.put("ContactNo",phone);
                params.put("Profession",profession);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(DonatorEditProfileActivity.this);
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

            Intent intent=new Intent(DonatorEditProfileActivity.this, DonatorProfileActivity.class);
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


