package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class ViewVolunteerDetailsActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    ListView listView;
    Volunteer.MyAdapter adapter;
    public static ArrayList<Volunteer> volunteerArrayList;
    AppCompatEditText searchVolunteer;

    private ProgressDialog loading;


    String url = "http://192.168.0.105:8080/api/volunteer/user";
    Volunteer volunteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_volunteer_details);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

            volunteerArrayList = new ArrayList<>();

            listView = findViewById(R.id.myListView);
            adapter = new Volunteer.MyAdapter(this, volunteerArrayList);
            listView.setAdapter(adapter);


        searchVolunteer = findViewById(R.id.searchVolunteer);

        searchVolunteer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    CharSequence[] dialogItem = {"Details Volunteer", "Edit Volunteer", "Delete Volunteer"};
                    builder.setTitle(volunteerArrayList.get(position).getName());
                    builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {

                            switch (i) {

                                case 0:

                                    startActivity(new Intent(getApplicationContext(), VolunteerDetailActivity.class)
                                            .putExtra("position", position));

                                    break;

                                case 1:
                                    startActivity(new Intent(getApplicationContext(), EditVolunteerActivity.class)
                                            .putExtra("position", position));

                                    break;

                                case 2:

                                    deleteData(volunteerArrayList.get(position).getId());

                                    break;

                            }

                        }
                    });


                    builder.create().show();


                }
            });

            retrieveData();

        }


    private void deleteData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.105:8080/api/volunteer/user/android/delete/"+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equals("deleted Successfully")) {

                            Intent intent = new Intent(ViewVolunteerDetailsActivity.this, ViewVolunteerDetailsActivity.class);
                            Toast.makeText(ViewVolunteerDetailsActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        }

                        else if (response.equals("err")) {

                            Toast.makeText(ViewVolunteerDetailsActivity.this, " Data Not Deleted ", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(ViewVolunteerDetailsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewVolunteerDetailsActivity.this, "No Internet Connection !!!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("VId", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void retrieveData () {
        loading = new ProgressDialog(ViewVolunteerDetailsActivity.this);
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
                    Toast.makeText(ViewVolunteerDetailsActivity.this, "No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
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

            Intent intent=new Intent(ViewVolunteerDetailsActivity.this, AdminDetailsActivity.class);
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


















