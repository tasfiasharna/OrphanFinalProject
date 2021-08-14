//package com.tasfia.orphanhouse;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class VolunteersActivity extends AppCompatActivity {
//
//  ListView listView;
//  String mTitle[] = {"Md. A", "Md. B", "Md. C", "Md. D", "Md. E", "Md. F"};
//  String mWork[] = {"International Islamic University Chittagong", "Chittagong University", "Premier University", "East Delta University", "Chattogram College", "International Islamic University Chittagong"};
//  int images[] = {R.drawable.shanta, R.drawable.shanta, R.drawable.shanta, R.drawable.shanta, R.drawable.shanta, R.drawable.shanta};
//  // so our images and other things are set in array
//
//  // now paste some images in drawable
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_volunteers);
//
//    listView = findViewById(R.id.listView);
//    // now create an adapter class
//
//    VolunteersActivity.MyAdapter adapter = new VolunteersActivity.MyAdapter(this, mTitle, mWork, images);
//    listView.setAdapter(adapter);
//    // there is my mistake...
//    // now again check this..
//
//    // now set item click on list view
//    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//      @Override
//      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (position ==  0) {
//          Toast.makeText(VolunteersActivity.this, "International Islamic University Chittagong", Toast.LENGTH_SHORT).show();
//        }
//        if (position ==  0) {
//          Toast.makeText(VolunteersActivity.this, "Chittagong University", Toast.LENGTH_SHORT).show();
//        }
//        if (position ==  0) {
//          Toast.makeText(VolunteersActivity.this, "Premier University", Toast.LENGTH_SHORT).show();
//        }
//        if (position ==  0) {
//          Toast.makeText(VolunteersActivity.this, "East Delta University", Toast.LENGTH_SHORT).show();
//        }
//        if (position ==  0) {
//          Toast.makeText(VolunteersActivity.this, "Chattogram College", Toast.LENGTH_SHORT).show();
//        }
//        if (position ==  0) {
//          Toast.makeText(VolunteersActivity.this, "International Islamic University Chittegong", Toast.LENGTH_SHORT).show();
//        }
//      }
//    });
//    // so item click is done now check list view
//
//    getSupportActionBar().setHomeButtonEnabled(true); //for back button
//    getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
//    getSupportActionBar().setTitle("Volunteers");//for actionbar title
//  }
//
//  class MyAdapter extends ArrayAdapter<String> {
//
//    Context context;
//    String rTitle[];
//    String rWork[];
//    int rImgs[];
//
//    MyAdapter (Context c, String title[], String work[], int imgs[]) {
//      super(c, R.layout.row, R.id.textView1, title);
//      this.context = c;
//      this.rTitle = title;
//      this.rWork = work;
//      this.rImgs = imgs;
//
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//      LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//      View row = layoutInflater.inflate(R.layout.row, parent, false);
//      ImageView images = row.findViewById(R.id.image);
//      TextView myTitle = row.findViewById(R.id.textView1);
//      TextView myDescription = row.findViewById(R.id.textView2);
//
//      // now set our resources on views
//      images.setImageResource(rImgs[position]);
//      myTitle.setText(rTitle[position]);
//      myDescription.setText(rWork[position]);
//
//
//
//
//      return row;
//    }
//  }
//
//  public boolean onOptionsItemSelected(MenuItem item) {
//    switch (item.getItemId()) {
//      case android.R.id.home:
//        // app icon in action bar clicked; goto parent activity.
//        this.finish();
//        return true;
//      default:
//        return super.onOptionsItemSelected(item);
//    }
//  }
//}
