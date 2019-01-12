package com.mypushtak.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ChangePassword extends AppCompatActivity {


    private ImageView back_button;

    Button changepassword;

    EditText old_password;
    EditText new_password;
    EditText confirm_password;

    String password;

    @Override
    protected void onStart() {
        super.onStart();
        RequestQueue queue = Volley.newRequestQueue(ChangePassword.this);

        String url = ConstantUrl.URL+"oldpass/30038";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonBody = jsonArray.getJSONObject(0);
                    password = jsonBody.getString("password");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("onResponse", "response: " + password);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(postRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        back_button = findViewById(R.id.navigationnn);
        changepassword = findViewById(R.id.changepassword);
        old_password = findViewById(R.id.old_password_edittext);
        new_password = findViewById(R.id.new_password_edittext);
        confirm_password = findViewById(R.id.confirm_password_edittext);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToBooksActivity();
            }
        });

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (old_password.getText().toString().equals(""))
                    old_password.setError("cant be blank");
                else if (new_password.getText().toString().equals(""))
                    new_password.setError("cant be blank");
                else if (confirm_password.getText().toString().equals(""))
                    confirm_password.setError("cant be blank");
                else {
                    if (old_password.getText().toString().equals(password)) {
                        if(new_password.getText().toString().equals(confirm_password.getText().toString())) {
                            updatePassword();
                            Toast.makeText(ChangePassword.this, "successfully changes", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            confirm_password.setError("not correct");
                        }
                    } else {
                        old_password.setError("not correct");
                    }
                }
            }
        });
    }

    private void sendToBooksActivity() {

        Intent i = new Intent(ChangePassword.this, ProfileDetailsActivity.class);
        startActivity(i);
        finish();
    }

    private void updatePassword() {
        RequestQueue donationqueue = Volley.newRequestQueue(ChangePassword.this);
        String donor_url = ConstantUrl.URL+"old_pass/30038/" + new_password.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, donor_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                    Log.d("onResponseString", "response: " + responseString);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        donationqueue.add(stringRequest);
    }
}

