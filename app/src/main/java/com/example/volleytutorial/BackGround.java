package com.example.volleytutorial;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BackGround {
    private static final String TAG = "MainActivity";
    ArrayList<Student> students = new ArrayList<>();
    private Context context;
    public BackGround(Context context)
    {
        this.context=context;
    }
    public ArrayList<Student> CollectAllData() {



      String  url = "http://192.168.1.38:8080/students";
        //Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();

//requestQueue=Volley.newRequestQueue(MainActivity.this) this is default request Queee
        //  requestQueue=    MySingleton.getInstance(getApplicationContext()).getRequestQueue();
               /* StringRequest stringRequest=new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
           viewID.setText(response);

                        Log.d(TAG, "onResponse: "+response);
         //   requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        viewID.setText(error.getMessage());
                        Log.d(TAG, "onResponse: errorrrrrr"+error.getMessage());
                      //  requestQueue.stop();
                    }
                });*/

                /*JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            rollnumber.setText(response.getString("rollNumber"));
                            name.setText(response.getString("name"));
                            age.setText(response.getString("age"));
                            sex.setText(response.getString("sex"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });*/
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                Log.d(TAG, "onResponse: " + response.length());
                while (count < response.length())
                    try {
                        JSONObject jsonObject = response.getJSONObject(count);
                        Student student = new Student(Integer.parseInt(jsonObject.getString("rollNumber")), jsonObject.getString("name"), Integer.parseInt(jsonObject.getString("age")), jsonObject.getString("sex"));
                        students.add(student);
                        // Log.d(TAG, "onResponse: "+students.size());
                        count++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
               /* for(Student s: students)
                {
                    Log.d(TAG, "Students Info "+s.toString());
                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        MySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjectRequest);

            return students;
    }
}
