package com.mypushtak.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Signup extends AppCompatActivity {

    EditText email;
    EditText password;
    ImageView showVisible;
    ImageView showVisibleoff;
    Button signup;
    ProgressBar progressBar;

    int id;

    String name;
    String contact_no;
    String pincode;
    String address;
    String landmark;
    String state;
    String city;
    String approx_no_of_books;
    String no_of_cartoons;
    String preferred_pickup_date;
    String donated_book_category;
    String from_where;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        showVisible=findViewById(R.id.showvisible);
        showVisibleoff=findViewById(R.id.showvisibleoff);
        signup=findViewById(R.id.signup);
        progressBar=findViewById(R.id.progressBar);

        String emailid=getIntent().getStringExtra("signup");
        name=getIntent().getStringExtra("name");
        contact_no=getIntent().getStringExtra("contact_no");
        pincode=getIntent().getStringExtra("pincode");
        address=getIntent().getStringExtra("address");
        landmark=getIntent().getStringExtra("landmark");
        state=getIntent().getStringExtra("state");
        city=getIntent().getStringExtra("city");
        approx_no_of_books=getIntent().getStringExtra("approx_no_of_books");
        no_of_cartoons=getIntent().getStringExtra("no_of_cartoons");
        preferred_pickup_date=getIntent().getStringExtra("preferred_pickup_date");
        donated_book_category=getIntent().getStringExtra("donated_book_category");
        from_where=getIntent().getStringExtra("from_where");
        email.setText(emailid);

        showVisibleoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                showVisible.setVisibility(View.VISIBLE);
                showVisibleoff.setVisibility(View.GONE);
            }
        });

        showVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                showVisibleoff.setVisibility(View.VISIBLE);
                showVisible.setVisibility(View.GONE);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(""))
                    password.setError("cant be blank");
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    try {
                        String pass=getMD5(password.getText().toString().trim());
                        createNewUser(pass);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void createNewUser(String pass){
        RequestQueue queue = Volley.newRequestQueue(Signup.this);
        String donor_url = ConstantUrl.URL+"userpost/" + email.getText().toString();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("first_name", name);
            jsonBody.put("last_name", ".com");
            jsonBody.put("alternative_email", "smallboy7311@gmail.com");
            jsonBody.put("password", pass);
            jsonBody.put("user_role_id", "1");
            jsonBody.put("avatar", "12345678.jpg");
            jsonBody.put("birth_date", "04/13/2012");
            jsonBody.put("communication_address", address);
            jsonBody.put("mobile", contact_no);
            jsonBody.put("contact_no", contact_no);
            jsonBody.put("profession", "null");
            jsonBody.put("contribution", "null");
            jsonBody.put("profile_percentage", "100");
            jsonBody.put("is_volunteer", "N");
            jsonBody.put("is_donor", "N");
            jsonBody.put("is_customer", "N");
            jsonBody.put("is_verified", "Y");
            jsonBody.put("is_deleted", "N");
            jsonBody.put("is_active", "Y");
            jsonBody.put("i_date", "12345678");
            jsonBody.put("i_by", "1");
            jsonBody.put("u_date", "1459164134");
            jsonBody.put("u_by", "1");
            jsonBody.put("registered_date", "2015-10-09");
            jsonBody.put("wallet_amount", "0");

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, donor_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.i("VOLLEY", response);
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
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        getUserId();
                        // can get more details such as response.headers
                        Log.d("onResponseString", "response: " + responseString);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            queue.add(stringRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getUserId() {
        RequestQueue getUserqueue = Volley.newRequestQueue(Signup.this);
        String getUser_url = ConstantUrl.URL+"getUsers/" + email.getText().toString();
        StringRequest postRequest = new StringRequest(Request.Method.GET, getUser_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonBody = new JSONObject(response);
                    id = jsonBody.getInt("id");
                    Log.d("getUserId","id"+id);
                    donationpost();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("onResponsegetUser", "response: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        getUserqueue.add(postRequest);
        return id;
    }

    public void donationpost(){
        RequestQueue donationqueue = Volley.newRequestQueue(Signup.this);
        String donor_url = ConstantUrl.URL+"donationpost/" + id;
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("volunteer_id", "2222");
            jsonBody.put("track_url", "null");
            jsonBody.put("declaration_form", "null");
            jsonBody.put("awb_attachment", "null");
            jsonBody.put("status", "1");
            jsonBody.put("address", address);
            jsonBody.put("country", "101");
            jsonBody.put("state", state);
            jsonBody.put("city", city);
            jsonBody.put("zipcode", pincode);
            jsonBody.put("no_of_book", approx_no_of_books);
            jsonBody.put("no_of_cartons", no_of_cartoons);
            jsonBody.put("app_books_weight", "25");
            jsonBody.put("donated_book_category", donated_book_category);
            jsonBody.put("pickup_date_time", "1459222380");
            jsonBody.put("donation_image", "0528475001459164128.jpg");
            jsonBody.put("how_do_u_know_abt_us", from_where);
            jsonBody.put("wastage", "90");
            jsonBody.put("document_mail_sent", "N");
            jsonBody.put("is_blocked", "N");
            jsonBody.put("i_date", "1459164134");
            jsonBody.put("u_date", "1459164134");
            jsonBody.put("tracking_no", "");
            jsonBody.put("is_paid_donation", "N");
            jsonBody.put("paymrnt_url", "null");
            jsonBody.put("payment_id", "null");
            jsonBody.put("mobile", contact_no);
            jsonBody.put("landmark", landmark);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, donor_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
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
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                        Log.d("onResponseDonation", "response: " + responseString);
                        progressBar.setVisibility(View.GONE);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            donationqueue.add(stringRequest);
            Toast.makeText(this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Signup.this,donationlastpart.class);
            i.putExtra("signup_user_id",id);
            i.putExtra("signup_activity",1);
            startActivity(i);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getMD5(String data) throws NoSuchAlgorithmException
    {
        MessageDigest messageDigest=MessageDigest.getInstance("MD5");

        messageDigest.update(data.getBytes());
        byte[] digest=messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        return sb.toString();
    }
}
