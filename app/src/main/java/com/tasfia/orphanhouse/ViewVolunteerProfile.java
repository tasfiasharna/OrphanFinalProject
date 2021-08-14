package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewVolunteerProfile extends AppCompatActivity {


    TextView txtEditProfile;
//    String profileImage = "";

    TextView txtName, txtName2,  txtCell,txtLocation,txtEmail, txtProfession,  txtBlood;

    private ProgressDialog loading;

    ImageView profilePic;

    Volunteer.MyAdapter adapter;
    public static ArrayList<Volunteer> volunteerArrayList;

    Volunteer volunteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_volunteer_profile);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Profile");

        txtName = findViewById(R.id.txtName);
        txtName2 = findViewById(R.id.txtName2);
        txtCell = findViewById(R.id.txtCell);
        txtLocation = findViewById(R.id.txtLocation);
        txtEmail = findViewById(R.id.txtEmail);
        txtProfession = findViewById(R.id.txtProfession);
        txtBlood = findViewById(R.id.txtBlood);
        profilePic = findViewById(R.id.profile_image);


        txtEditProfile = findViewById(R.id.txtEdit);

        txtEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ViewVolunteerProfile.this,VolunteerEditProfileActivity.class);
                intent.putExtra("VName",txtName.getText());
                intent.putExtra("Address",txtCell.getText());
                intent.putExtra("Email",txtLocation.getText());
                intent.putExtra("Profession",txtCell.getText());
                intent.putExtra("Blood",txtLocation.getText());
                startActivity(intent);
            }
        });


        retrieveData();
    }


    public void retrieveData(){

        loading = new ProgressDialog(ViewVolunteerProfile.this);
        loading.setMessage("Please wait....");
        loading.show();


        String url = "http://192.168.0.105:8080/api/volunteer/profile/:name";


        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        showJSON(response);

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray result = jsonObject.getJSONArray("data");

                            if (success.equals("1")) {


                                for (int i = 0; i < result.length(); i++) {

                                    JSONObject object = result.getJSONObject(i);

                                    String id = object.getString("VId");
                                    String name = object.getString("VName");
                                    String address = object.getString("Address");
                                    String date = object.getString("DateOfBirth");
                                    String email = object.getString("Email");
                                    String gender = object.getString("Gender");
                                    String contactNo = object.getString("ContactNo");
                                    String profession = object.getString("Profession");
                                    String fromWhereYouListenAboutUs = object.getString("FromWhereYouLearnAboutUs");
                                    String theReasonOfYourVolunteering = object.getString("TheReasonOfYourVolunteering");
                                    String bloodGroup = object.getString("Blood");
                                    String image = object.getString("Uimg");

//                                        String Url = "https://orphanbd.000webhostapp.com/Volunteer/"+image;

                                    volunteer = new Volunteer(id, name,  address, date, email, gender, contactNo, profession, fromWhereYouListenAboutUs, theReasonOfYourVolunteering, bloodGroup, image);

                                    volunteerArrayList.add(volunteer);
                                    adapter.notifyDataSetChanged();

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewVolunteerProfile.this, "No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(ViewVolunteerProfile.this);
        requestQueue.add(request);

    }

    private void showJSON(String response) {

        Log.d("RESPONSE", response);

        String name = "";
        String cell = "";
        String address = "";
        String email = "";
        String profession = "";
        String blood = "";


        txtName.setText(name);
        txtName2.setText(name);
        txtCell.setText(cell);
        txtLocation.setText(address);
        txtEmail.setText(email);
        txtProfession.setText(profession);
        txtBlood.setText(blood);

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
}