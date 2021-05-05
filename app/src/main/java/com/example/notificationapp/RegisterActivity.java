package com.example.notificationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    RequestQueue queue;
    EditText ed_fName, ed_lName, ed_Email , ed_Pass;
    Button reg_btn;
    String token;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         queue = Volley.newRequestQueue(getApplicationContext());
        ed_fName = (EditText) findViewById(R.id.name);
        ed_lName = (EditText) findViewById(R.id.lName);
        ed_Email = (EditText) findViewById(R.id.email1);
        ed_Pass = (EditText) findViewById(R.id.password);
        reg_btn = (Button) findViewById(R.id.btn_register);
   login =findViewById(R.id.txt_login);
   login.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
           startActivity(intent);
       }
   });
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getReqToken();
                addUser();

            }
        });



         //signup //post
       //  login(); post
        //updateRegToken()//login //put

    }



    // هاد ال api token لكل يوزر بكون عشان اقدر ابعتله اشعار خاص فيه
//    private void getReqToken() {
//
//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
//            @Override
//            public void onComplete(@NonNull Task<String> task) {
//                if(!task.isSuccessful()){
//                    Log.e("msg","Failed to get token"+task.getException());
//                    return;
//                }
//                // هاد رح ابعته في الحالة التالته
//                token = task.getResult();
//                Log.d("msg","token : "+token);
//
//            }
//        });
//
//
//    }

    private void addUser() {
        final String email = ed_Email.getText().toString();
        final String pass = ed_Pass.getText().toString();
        final String fName = ed_fName.getText().toString();
        final String lName = ed_lName.getText().toString();
        if (TextUtils.isEmpty(email)) {
            ed_Email.setError("Please enter your email");
            ed_Email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            ed_Pass.setError("Please enter password");
            ed_Pass.requestFocus();
            return;
        }
        String URL="https://mcc-users-api.herokuapp.com/add_new_user";


        StringRequest postRequest = new StringRequest(Request.Method.POST,URL,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objres = new JSONObject(response);
                    Log.d("TAG", "SuccessOnResponse: "+objres.toString());
                } catch (JSONException e) {
                    Log.d("TAG", "Server Error ");
                }
                //Log.i("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                //Log.v("VOLLEY", error.toString());
            }
        }) {


            @Override
            protected Map<String, String> getParams()
            {
                HashMap<String, String> params = new HashMap<>();
                params.put("firstName", fName);
                params.put("secondName", lName);
                params.put("email", email);
                params.put("password", pass);
//              params.put("token",token);
                return params;
            }



        };
        ed_fName.setText("");
        ed_lName.setText("");
        ed_Email.setText("");
        ed_Pass.setText("");
        queue.add(postRequest);
    }









}
