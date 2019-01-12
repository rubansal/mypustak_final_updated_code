package com.mypushtak.app.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.DatabaseHelper.CityDatabaseHelper;
import com.mypushtak.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Donation extends AppCompatActivity {

    FrameLayout next_layout;
    Toolbar mToolbar;
    TextView mTextview;

    CityDatabaseHelper cityDatabaseHelper;

    @Override
    protected void onStart() {
        super.onStart();
        cityDatabaseHelper=new CityDatabaseHelper(this);
        if(cityDatabaseHelper.getCitiesCount()==0){
            ApiCall apiCall=new ApiCall();
            apiCall.execute();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        mToolbar=findViewById(R.id.donation_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTextview=findViewById(R.id.change_password);
        mTextview.setText("DONATION");

        next_layout=findViewById(R.id.next_layout);

        next_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Donation.this,Donationform.class);
                startActivity(i);
                finish();
            }
        });
    }

    class ApiCall extends AsyncTask<Integer,Integer,Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            cityApi();
            return null;
        }
    }

    private void cityApi(){
        RequestQueue queue = Volley.newRequestQueue(Donation.this);
        String url = ConstantUrl.URL+"getCity";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject city_object=jsonArray.getJSONObject(i);
                        String city=city_object.getString("city");
                        int state_id=city_object.getInt("stateId");
                        Log.d("onCreateDialog", "state_id"+state_id);
                        cityDatabaseHelper.insertCity(city, state_id);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("onResponse", "response: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(postRequest);
    }
}
