package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.AlertDialog;
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

public class AdoptionChildActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    ListView listView;
    Orphan.MyAdapter adapter;
    public static ArrayList<Orphan> orphanArrayList = new ArrayList<>();
    AppCompatEditText searchOrphan;

//    String url = "https://orphanbd.000webhostapp.com/retrieve.php";
    String url ="http://192.168.0.105:8080/api/orphan/user";
    Orphan orphan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_child);

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

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adoption");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                CharSequence[] dialogItem = {"View Details"};
                builder.setTitle(orphanArrayList.get(position).getName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                Intent intent = new Intent(getApplicationContext(),OrphanDetailActivity.class);
                                Orphan mOrphan = orphanArrayList.get(position);
                                intent.putExtra("ButtonVisibility", true);
                                intent.putExtra("Id", mOrphan.getId());
                                intent.putExtra("Name", mOrphan.getName());
                                intent.putExtra("RealAge", mOrphan.getAge());
                                intent.putExtra("History", mOrphan.getHistory());
                                intent.putExtra("Gender", mOrphan.getGender());
                                intent.putExtra("PhysicalDefect", mOrphan.getPhysicalDefect());
                                intent.putExtra("WhatKindoFPhysicalDefect", mOrphan.getWhatKindOfPhysicalDefect());
                                intent.putExtra("MentalStatus", mOrphan.getMentalStatus());
                                intent.putExtra("AgeCategoryId", mOrphan.getAgeCategory());
                                intent.putExtra("Uimg", mOrphan.getImage());
                                intent.putExtra("Blood", mOrphan.getBloodGroup());
                                startActivity(intent);

                                break;
                        }
                    }
                });

                builder.create().show();

            }
        });

        retrieveData();
    }

    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
                Toast.makeText(AdoptionChildActivity.this, "No Internet Connection  !!!",  Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
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

            Intent intent=new Intent(AdoptionChildActivity.this, HomeActivity.class);
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
