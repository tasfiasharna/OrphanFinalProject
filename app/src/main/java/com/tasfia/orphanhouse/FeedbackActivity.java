package com.tasfia.orphanhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FeedbackActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    Button btnSubmit;
    EditText etxtName, etxtEmail, etxtFeedback;
    private ProgressDialog loading;

    String url = "http://192.168.0.105:8080/api/feedback/user/android/post";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        btnSubmit = (Button) findViewById(R.id.btn_submit);
        etxtName = (EditText) findViewById(R.id.etxt_name);
        etxtEmail = (EditText) findViewById(R.id.etxt_email);
        etxtFeedback = (EditText) findViewById(R.id.etxt_feedback);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(FeedbackActivity.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Want to Submit Feedback?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                SaveFeedback();
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

    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Feedback");

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

    private boolean validateUsername() {
        String name = etxtName.getText().toString().trim();

        if (name.isEmpty()) {
            etxtName.setError("Field can't be empty");
            return false;
        } else if(!name.matches("[a-zA-Z ]+")) {
            etxtName.requestFocus();
            etxtName.setError("Enter only alphabetical character");
        }  else if (name.length() > 20 ) {
            etxtName.setError("Name too long");
            return false;
        } else if (name.length() <3 ) {
            etxtName.setError("Name too sort");
            return false;
        }
        else {
            etxtName.setError(null);
            return true;
        }
        return true;
    }

        private boolean validateFeedback() {
        String feedback = etxtFeedback.getText().toString().trim();

        if (feedback.isEmpty()) {
            etxtFeedback.setError("Field can't be empty");
            return false;
        } else if (feedback.length() <2 ) {
            etxtFeedback.setError("Please give your feedback");
            return false;
        }
        else {
            etxtFeedback.setError(null);
            return true;
        }

    }


    public void  SaveFeedback()
    {

        if (!validateEmail() | !validateUsername() | !validateFeedback()) {
            return;
        }

        final String name=etxtName.getText().toString().trim();
        final String email=etxtEmail.getText().toString().trim();
        final String feedback=etxtFeedback.getText().toString().trim();

        if (name.isEmpty())
        {
            Toast.makeText(this, "Name Can't Empty", Toast.LENGTH_SHORT).show();
        }
        else if(email.isEmpty())
        {
            Toast.makeText(this, "Email Can't Empty", Toast.LENGTH_SHORT).show();
        }
        else if(feedback.isEmpty())
        {
            Toast.makeText(this, "Feedback Can't Empty", Toast.LENGTH_SHORT).show();
        }
        else {
            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.wait_icon);
            loading.setTitle("Submit");
            loading.setMessage("Please wait....");
            loading.show();

         }

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.equals("success")) {

                                loading.dismiss();

                                Intent intent = new Intent(FeedbackActivity.this, HomeActivity.class);
                                Toast.makeText(FeedbackActivity.this, " Successfully Submitted!", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }

                            else if (response.equals("failure")) {

                                loading.dismiss();
                                Toast.makeText(FeedbackActivity.this, " Submit fail!", Toast.LENGTH_SHORT).show();

                            } else {

                                loading.dismiss();
                                Toast.makeText(FeedbackActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                            }
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(FeedbackActivity.this, "No Internet Connection  !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    params.put("Name", name);
                    params.put("Email", email);
                    params.put("Comments", feedback);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(FeedbackActivity.this);
            requestQueue.add(stringRequest);
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

            Intent intent=new Intent(FeedbackActivity.this, HomeActivity.class);
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
