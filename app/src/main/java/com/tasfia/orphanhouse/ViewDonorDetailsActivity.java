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

public class ViewDonorDetailsActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    ListView listView;
    Donator.MyAdapter adapter;
    public static ArrayList<Donator> donatorArrayList;
    AppCompatEditText searchDonator;

    String url = "http://192.168.0.105:8080/api/dreg/user/android/get";
    Donator donator;
    private ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donor_details);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

        donatorArrayList = new ArrayList<>();

        listView = findViewById(R.id.myListView);
        adapter = new Donator.MyAdapter(this, donatorArrayList);
        listView.setAdapter(adapter);

        searchDonator = findViewById(R.id.searchDonator);

        searchDonator.addTextChangedListener(new TextWatcher() {
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

                CharSequence[] dialogItem = {"Donor Details",  "Delete Donor"};
                builder.setTitle(donatorArrayList.get(position).getName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i) {

                            case 0:

                                startActivity(new Intent(getApplicationContext(), DonorDetailsActivity.class)
                                        .putExtra("position", position));

                                break;

                            case 1:

                                deleteData(donatorArrayList.get(position).getId());

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

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.105:8080/api/dreg/user/android/delete/"+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equals("deleted Successfully")) {

                            Intent intent = new Intent(ViewDonorDetailsActivity.this, ViewDonorDetailsActivity.class);
                            Toast.makeText(ViewDonorDetailsActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        } else if (response.equals("err")) {

                            Toast.makeText(ViewDonorDetailsActivity.this, " Data Not Deleted ", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(ViewDonorDetailsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewDonorDetailsActivity.this, "No Internet Connection !!!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("Id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


    public void retrieveData () {

        loading = new ProgressDialog(ViewDonorDetailsActivity.this);
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

                                    String id = object.getString("Id");
                                    String name = object.getString("Name");
                                    String gender = object.getString("Gender");
                                    String address = object.getString("Address");
                                    String city = object.getString("City");
                                    String profession = object.getString("Profession");
                                    String contactNo = object.getString("ContactNo");
                                    String email = object.getString("Email");

                                    donator=new Donator(id,name,gender,address,city,profession,contactNo,email);

                                    donatorArrayList.add(donator);
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
                Toast.makeText(ViewDonorDetailsActivity.this, "No Internet Connection  !!!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
    public void btn_add_activity (View view){
        startActivity(new Intent(getApplicationContext(), AddDonatorActivity.class));
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

            Intent intent=new Intent(ViewDonorDetailsActivity.this, AdminDetailsActivity.class);
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




