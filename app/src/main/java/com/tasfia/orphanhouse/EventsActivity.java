package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class EventsActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    private ProgressDialog loading;

    ListView listView;
    Events.MyAdapter adapter;

    public static ArrayList<Events> eventsArrayList = new ArrayList<>();


    String url = "http://192.168.0.105:8080/api/event/user";
    Events events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eventsArrayList = new ArrayList<>();

        listView = findViewById(R.id.myListView);
        adapter = new Events.MyAdapter(this, eventsArrayList);
        listView.setAdapter(adapter);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Our Events");//for actionbar title


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(eventsArrayList.get(position).getTitle());

            }
        });

        retrieveData();

    }

    public void retrieveData () {
        loading = new ProgressDialog(EventsActivity.this);
        loading.setMessage("Please wait....");
        loading.show();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            loading.dismiss();

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray result = jsonObject.getJSONArray("data");

                            if (success.equals("1")) {

                                for (int i = 0; i < result.length(); i++) {

                                    JSONObject object = result.getJSONObject(i);

                                    String id = object.getString("Id");
                                    String title = object.getString("Title");
                                    String date = object.getString("Date");
                                    String time = object.getString("Time");
                                    String venue = object.getString("Venu");
                                    String writeSomething = object.getString("WriteSomething");
                                    String image = object.getString("Image");

//                                    String Url = "https://orphanbd.000webhostapp.com/Events/"+image;
//                                    events = new Events(id, title, date, time, venue, writeSomething,   Url);
                                    events = new Events(id, title, date, time, venue, writeSomething,   image);

                                    eventsArrayList.add(events);
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

                Toast.makeText(EventsActivity.this, "No Internet Connection  !!!", Toast.LENGTH_LONG).show();
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

            Intent intent=new Intent(EventsActivity.this, HomeActivity.class);
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





