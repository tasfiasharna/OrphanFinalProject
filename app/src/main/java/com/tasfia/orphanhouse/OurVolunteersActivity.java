package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
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

public class OurVolunteersActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;


    ListView listView;
    Volunteer.MyAdapter adapter;
    public static ArrayList<Volunteer> volunteerArrayList;

    String url = "http://192.168.0.105:8080/api/volunteer/user";
    Volunteer volunteer;
    private ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_volunteers);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Our Volunteers");//for actionbar title

        volunteerArrayList = new ArrayList<>();

        listView = findViewById(R.id.myListView);
        adapter = new Volunteer.MyAdapter(this, volunteerArrayList);
        listView.setAdapter(adapter);


        retrieveData();
    }

    public void retrieveData () {
        loading = new ProgressDialog(OurVolunteersActivity.this);
        loading.setMessage("Please wait....");
        loading.show();


        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();

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
                                    String email = object.getString("Email");
                                    String gender = object.getString("Gender");;
                                    String date = object.getString("DateOfBirth");
                                    String contactNo = object.getString("ContactNo");
                                    String profession = object.getString("Profession");
                                    String fromWhereYouListenAboutUs = object.getString("FromWhereYouLearnAboutUs");
                                    String theReasonOfYourVolunteering = object.getString("TheReasonOfYourVolunteering");
                                    String bloodGroup = object.getString("Blood");
                                    String image = object.getString("Uimg");


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
                Toast.makeText(OurVolunteersActivity.this, "No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
    public void btn_add_activity (View view){
        startActivity(new Intent(getApplicationContext(), AddVolunteerActivity.class));
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

            Intent intent=new Intent(OurVolunteersActivity.this, HomeActivity.class);
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
