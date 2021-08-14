package com.tasfia.orphanhouse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
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
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class CreateEventActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    Button btnCreate;
    EditText etxtTitle, etxtDate, etxtTime,etxtVenue, etxtwriteSomething;
    Button btnChooseImage;
    ImageView imageView;
    Bitmap bitmap;
    String encodedImage=null;
    private ProgressDialog loading;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        btnCreate = (Button) findViewById(R.id.btn_create);
        btnChooseImage = findViewById(R.id.btnChooseImage);
        etxtTitle = (EditText) findViewById(R.id.etxt_title);
        etxtDate = (EditText) findViewById(R.id.etxt_date);
        etxtDate.setInputType(InputType.TYPE_NULL);
        etxtTime = (EditText) findViewById(R.id.etxt_time);
        etxtTime.setInputType(InputType.TYPE_NULL);
        etxtVenue = (EditText) findViewById(R.id.etxt_venue);
        etxtwriteSomething = (EditText) findViewById(R.id.etxt_details);
        imageView = findViewById(R.id.imView);


        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(CreateEventActivity.this)
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

        etxtDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setDate(etxtDate);
                }
                return false;
            }
        });

        etxtTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setTime(etxtTime);
                }
                return false;
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Want to Create Event?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                SaveEvent();
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
        getSupportActionBar().setTitle("Create Event");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK && data!=null){

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
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);

    }

    private boolean validateTitle() {
        String title = etxtTitle.getText().toString().trim();

        if (title.isEmpty()) {
            etxtTitle.setError("Field can't be empty");
            return false;
        } else if(!title.matches("[a-zA-Z ]+")) {
            etxtTitle.requestFocus();
            etxtTitle.setError("Enter only alphabetical character");
        } else if (title.length() > 25 ) {
            etxtTitle.setError("Name too long");
            return false;
        }
        else {
            etxtTitle.setError(null);
            return true;
        }
        return true;
    }

    private boolean validateDate() {
        String date = etxtDate.getText().toString().trim();

        if (date.isEmpty()) {
            etxtDate.setError("Field can't be empty");
            return false;
        }
        else {
            etxtDate.setError(null);
            return true;
        }

    }

    private boolean validateTime() {
        String time = etxtTime.getText().toString().trim();

        if (time.isEmpty()) {
            etxtTime.setError("Field can't be empty");
            return false;
        }
        else {
            etxtTime.setError(null);
            return true;
        }
    }

    private boolean validateVenue() {
        String venue = etxtVenue.getText().toString().trim();

        if (venue.isEmpty()) {
            etxtVenue.setError("Field can't be empty");
            return false;
        }
        else {
            etxtVenue.setError(null);
            return true;
        }
    }

    private boolean validateDetails() {
        String details = etxtwriteSomething.getText().toString().trim();

        if (details.isEmpty()) {
            etxtwriteSomething.setError("Field can't be empty");
            return false;
        } else if (details.length() <3) {
            etxtwriteSomething.setError("Please details about Event");
            return false;
        }
        else {
            etxtwriteSomething.setError(null);
            return true;
        }
    }


    private void setDate(final EditText date_Input) {
        final DatePicker datePicker = new DatePicker(CreateEventActivity.this);
        final int currentDay = datePicker.getDayOfMonth();
        final int currentMonth = datePicker.getMonth();
        final int currentYear = datePicker.getYear();

        DatePickerDialog datePickerDialog = new DatePickerDialog(CreateEventActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                date_Input.setText(date);
            }
        }, currentYear, currentMonth, currentDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void setTime(final EditText time_Input) {
        TimePicker timePicker = new TimePicker(CreateEventActivity.this);
        int currentHour = timePicker.getCurrentHour();
        int currentMinute = timePicker.getCurrentMinute();

        TimePickerDialog timePickerDialog = new TimePickerDialog(CreateEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                time_Input.setText(getTime(hourOfDay, minute));
            }
        }, currentHour, currentMinute, false);

        timePickerDialog.show();
    }


    private String getTime(int hr,int min) {
        Time tme = new Time(hr,min,0);
        Format formatter = new SimpleDateFormat("hh.mm aa", Locale.getDefault());
        return formatter.format(tme);

    }

    public void  SaveEvent()
    {
        if (!validateTitle() | !validateDate() | !validateTime() | !validateVenue() | !validateDetails()) {
            return;
        }

        final String title=etxtTitle.getText().toString().trim();
        final String date=etxtDate.getText().toString().trim();
        final String time=etxtTime.getText().toString().trim();
        final String venue=etxtVenue.getText().toString().trim();
        final String details=etxtwriteSomething.getText().toString().trim();

        if (title.isEmpty())
        {
            Toast.makeText(this, "Title Can't Empty", Toast.LENGTH_SHORT).show();
        }
        else if (date.isEmpty())
        {
            Toast.makeText(this, "Date Can't Empty", Toast.LENGTH_SHORT).show();
        }
        else if(time.isEmpty())
        {
            Toast.makeText(this, "Time Can't Empty", Toast.LENGTH_SHORT).show();
        }
        else if(venue.isEmpty())
        {
            Toast.makeText(this, "Venue Can't Empty", Toast.LENGTH_SHORT).show();
        }
        else if(details.isEmpty())
        {
            Toast.makeText(this, "Details Can't Empty", Toast.LENGTH_SHORT).show();
        }
        else if(encodedImage == null){
            Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
            return;
        }


        else {
            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.wait_icon);
            loading.setTitle("Create");
            loading.setMessage("Please wait....");
            loading.show();


        }

        StringRequest request = new StringRequest(Request.Method.POST,"http://192.168.0.105:8080/api/event/user",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if (response.trim().equalsIgnoreCase("success")) {

                            loading.dismiss();

                            Intent intent = new Intent(CreateEventActivity.this, AdminDetailsActivity.class);
                            Toast.makeText(CreateEventActivity.this, " Successfully Create Event !", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        }

                        else if (response.trim().equalsIgnoreCase("failure")) {

                            loading.dismiss();
                            Toast.makeText(CreateEventActivity.this, " Create Event fail!", Toast.LENGTH_SHORT).show();

                        } else {

                            loading.dismiss();
                            Toast.makeText(CreateEventActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(CreateEventActivity.this, "No Internet Connection !!!", Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("Title", title);
                params.put("Date", date);
                params.put("Time", time);
                params.put("Venu", venue);
                params.put("WriteSomething", details);
                params.put("Image",encodedImage);

                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(CreateEventActivity.this);
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

            Intent intent=new Intent(CreateEventActivity.this, AdminDetailsActivity.class);
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

