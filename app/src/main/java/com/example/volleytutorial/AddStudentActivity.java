package com.example.volleytutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AddStudentActivity extends AppCompatActivity {
    private static final String TAG = "AddStudentActivity";
    EditText name,age,sex;
    Button addStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        sex=findViewById(R.id.sex);
Context context;

        addStudent=findViewById(R.id.addStudent);
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String name_add = name.getText().toString();
                final String age_add = age.getText().toString();
                final String sex_add = sex.getText().toString();
                String url = "http://192.168.1.38:8080/student";
                JSONObject object = null;
                try {
                    object = new JSONObject("{}");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    //input your API parameters
                    object.put("age", age_add);
                    object.put("name", name_add);
                    object.put("sex", sex_add);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,object, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                       showAlertDialog(v.getContext());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: "+error.getMessage());
                    }
                }){

                   /* @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("age", age_add);
                        params.put("name", name_add);
                        params.put("sex", sex_add);
                        return params;
                    }*/
                   /* @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;

                    }
*/
                };
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }

        });
    }

    private void showAlertDialog(Context context) {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(context);

        alertDialog.setTitle("Server Responce");
        alertDialog.setMessage("Response : ");
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name.setText("");
                age.setText("");
                sex.setText("");
            }

        });
        AlertDialog at= alertDialog.create();
        at.show();
       /* Toast.makeText(context, "Atlest i can show", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "showAlertDialog: ");*/
    }
}
