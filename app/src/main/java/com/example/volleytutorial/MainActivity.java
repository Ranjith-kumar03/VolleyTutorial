package com.example.volleytutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
  //  private RequestQueue requestQueue;
    TextView rollnumber,name,age,sex;
    EditText enterRollNumber;
    Button startcmd,addStudent;
    String id;
    String url;
    private ListView listview;
    ArrayList<Student> students =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollnumber=findViewById(R.id.rollnumber);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        sex=findViewById(R.id.sex);
        enterRollNumber=findViewById(R.id.enterRollNumber);
        startcmd=findViewById(R.id.startcmd);
        addStudent=findViewById(R.id.addStudent);
        listview=findViewById(R.id.listviewId);


 // Cache cache=new DiskBasedCache(getCacheDir(),1024*1024);  ///Making Custom RequestQueue
  //BasicNetwork network= new BasicNetwork(new HurlStack());  ///Making Custom RequestQueue
 // requestQueue=new RequestQueue(cache,network);
       // requestQueue.start();
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddStudentActivity.class));
            }
        });
        startcmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               BackGround backGround=new BackGround(MainActivity.this);
               students =  backGround.CollectAllData();
                if(students.size()>0) {


                    Toast.makeText(MainActivity.this, "Please see the size of the student" + students.size(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Please see the size of the student" + students.size());
                }else
                {
                    Toast.makeText(MainActivity.this, "iAM stupid", Toast.LENGTH_SHORT).show();
                }

                //  ArrayAdapter<Student> adapter=new ArrayAdapter<Student>(MainActivity.this,R.layout.listviewlayout,students);
             // listview.setAdapter(adapter);
            }

        });

    }



}
