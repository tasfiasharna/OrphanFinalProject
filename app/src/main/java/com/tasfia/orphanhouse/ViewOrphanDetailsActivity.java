package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
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

public class ViewOrphanDetailsActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    ListView listView;

    Orphan.MyAdapter adapter;
    public static ArrayList<Orphan> orphanArrayList = new ArrayList<>();
    AppCompatEditText searchOrphan;

    private ProgressDialog loading;


    String url = "http://192.168.0.105:8080/api/orphan/user";
    Orphan orphan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orphan_details);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");

        orphanArrayList = new ArrayList<>();
        listView = findViewById(R.id.myListView);
        adapter = new Orphan.MyAdapter(this, orphanArrayList);
        listView.setAdapter(adapter);

        searchOrphan = findViewById(R.id.searchOrphan);

        searchOrphan.addTextChangedListener(new TextWatcher() {
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

                CharSequence[] dialogItem = {"Details Orphan","Edit Orphan","Delete Orphan"};
                builder.setTitle(orphanArrayList.get(position).getName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(), OrphanDetailActivity.class)
                                        .putExtra("position", position));


                                break;


                            case 1:
                                startActivity(new Intent(getApplicationContext(), EditOrphanActivity.class)
                                        .putExtra("position",position));

                                break;

                            case 2:

                                deleteData(orphanArrayList.get(position).getId());

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

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.105:8080/api/orphan/user/android/delete/"+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if (response.equals("deleted Successfully")) {

                            Intent intent = new Intent(ViewOrphanDetailsActivity.this, ViewOrphanDetailsActivity.class);
                            Toast.makeText(ViewOrphanDetailsActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }

                        else if (response.equals("err")) {

                            Toast.makeText(ViewOrphanDetailsActivity.this, " Data Not Deleted ", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(ViewOrphanDetailsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ViewOrphanDetailsActivity.this, "No Internet Connection  !!!", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("Id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    public void retrieveData(){
        loading = new ProgressDialog(ViewOrphanDetailsActivity.this);
        loading.setMessage("Please wait....");
        loading.show();

        StringRequest request = new StringRequest(Request.Method.GET, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();

                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray result = jsonObject.getJSONArray("data");

                            if(success.equals("1")){

                                for(int i=0;i<result.length();i++){

                                    JSONObject object = result.getJSONObject(i);

                                    String id = object.getString("Id");
                                    String name = object.getString("Name");
                                    String age = object.getString("RealAge");
                                    String history = object.getString("History");
                                    String gender = object.getString("Gender");
                                    String physicalDefect = object.getString("PhysicalDefect");
                                    String whatKindOfPhysicalDefect = object.getString("WhatKindoFPhysicalDefect");
                                    String mentalStatus = object.getString("MentalStatus");
                                    String bloodGroup = object.getString("Blood");
                                    String image = object.getString("Uimg");
                                    String ageCategory = object.getString("AgeCategoryId");
//                                    String image = object.getString("Uimg");
//                                    String bloodGroup = object.getString("Blood");
//                                    String Url="https://orphanbd.000webhostapp.com/Images/"+image;

                                    orphan = new Orphan(id,name,age,history,gender,physicalDefect,whatKindOfPhysicalDefect,mentalStatus,bloodGroup,image,ageCategory);

                                    orphanArrayList.add(orphan);
                                    adapter.notifyDataSetChanged();

                                }
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewOrphanDetailsActivity.this, "No Internet Connection !!!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(),AddOrphanActivity.class));
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

            Intent intent=new Intent(ViewOrphanDetailsActivity.this, AdminDetailsActivity.class);
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

