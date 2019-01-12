package com.mypushtak.app.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.Bean.MySignleton;
import com.mypushtak.app.DatabaseHelper.CityDatabaseHelper;
import com.mypushtak.app.DatabaseHelper.StateDatabaseHelper;
import com.mypushtak.app.DatabaseHelper.ZipcodeDatabaseHelper;
import com.mypushtak.app.Fragment.CityFragment;
import com.mypushtak.app.Fragment.StateFragment;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.ProfileDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddAddress extends AppCompatActivity {

    Toolbar mToolbar;
    TextView mTextView;

    EditText receiver_name;
    EditText address;
    EditText landmark;
    EditText state;
    EditText city;
    EditText pincode;
    EditText contact;

    Button add_address;

    ImageView statearrow_down;
    ImageView cityarrow_down;

    FragmentManager fm = getSupportFragmentManager();
    StateFragment stateFragment;
    CityFragment cityFragment;
    int state_id;
    int city_id;

    ZipcodeDatabaseHelper zipcodeDatabaseHelper;
    StateDatabaseHelper stateDatabaseHelper;
    CityDatabaseHelper cityDatabaseHelper;

    @Override
    protected void onStart() {
        super.onStart();
        stateDatabaseHelper=new StateDatabaseHelper(this);
        if(stateDatabaseHelper.getStatesCount()==0)
            stateApi();

        zipcodeDatabaseHelper = new ZipcodeDatabaseHelper(this);
        if(zipcodeDatabaseHelper.getZipcodeCount()==0)
            zipcodeDatabaseHelper.insertZipcode("123456");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        cityDatabaseHelper=new CityDatabaseHelper(this);
        if(cityDatabaseHelper.getCitiesCount()==0){
            ApiCall apiCall=new ApiCall();
            apiCall.execute();
        }

        mToolbar=findViewById(R.id.maintop);
        receiver_name=findViewById(R.id.reciever_name_edittext);
        address=findViewById(R.id.address_edittext);
        landmark=findViewById(R.id.landmark_edittext);
        state=findViewById(R.id.state_edittext);
        city=findViewById(R.id.city_edittext);
        pincode=findViewById(R.id.pincode_edittext);
        contact=findViewById(R.id.contact_edittext);
        add_address=findViewById(R.id.add_address);
        statearrow_down = findViewById(R.id.statearrow_down);
        cityarrow_down = findViewById(R.id.cityarrow_down);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTextView=findViewById(R.id.change_password);
        mTextView.setText("ADD NEW ADDRESS");

        statearrow_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateFragment = new StateFragment().newInstance(0);
                stateFragment.show(fm, "Dialog Fragment");
            }
        });

        cityarrow_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state.getText().toString().equals(""))
                    state.setError("select a state");
                else {
                    cityFragment = new CityFragment().newInstance(state_id,0);
                    cityFragment.show(fm, "Dialog Fragment");
                }
            }
        });


        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (receiver_name.getText().toString().equals(""))
                    receiver_name.setError("cant be blank");
                else if (address.getText().toString().equals(""))
                    address.setError("cant be blank");
                else if (landmark.getText().toString().equals(""))
                    landmark.setError("cant be blank");
                else if (state.getText().toString().equals(""))
                    state.setError("cant be blank");
                else if (city.getText().toString().equals(""))
                    city.setError("cant be blank");
                else if (pincode.getText().toString().equals(""))
                    pincode.setError("can't be blank");
                else if (!pincode.getText().toString().matches("[0-9]+"))
                    pincode.setError("only numbers are allowed");
                else if (pincode.getText().toString().length() != 6)
                    pincode.setError("should contain only 6 digits");
                else if (contact.getText().toString().equals(""))
                    contact.setError("cant be blank");
                else if (pincode.getText().toString().length() == 6) {
                    if (!zipcodeDatabaseHelper.getSearch(pincode.getText().toString()))
                        pincode.setError("service not available");
                    else
                        addAddress();
                }
            }
        });
    }

    private void addAddress() {
        ProfileDetails pd=new ProfileDetails();
        int id=pd.getId();
        RequestQueue donationqueue = Volley.newRequestQueue(AddAddress.this);
        String donor_url = ConstantUrl.URL+"address_insert/"+id+"/"+receiver_name.getText().toString().replace(" ","")+"/"+Integer.parseInt(pincode.getText().toString())+"/" +address.getText().toString().replace(" ","")+"/"+landmark.getText().toString().replace(" ","")+"/"+state.getText().toString()+"/" +city.getText().toString()+"/"+Long.parseLong(contact.getText().toString().trim());





        StringRequest stringRequest = new StringRequest(Request.Method.POST, donor_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent i=new Intent(AddAddress.this,CaptchaConfirmation.class);
                startActivity(i);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
                Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
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

    public void onUserStateSelectValue(String selectValue, int id) {
        state.setText(selectValue);
        state_id = id;
        Log.d("onUserSelectValue", "stateId" + state_id);
        stateFragment.dismiss();
    }

    public void onUserCitySelectValue(String selectValue, int id) {
        city.setText(selectValue);
        city_id=id;
        cityFragment.dismiss();
    }

    public void stateApi(){

        RequestQueue queue = Volley.newRequestQueue(AddAddress.this);
        String url = ConstantUrl.URL+"getState";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject state_object=jsonArray.getJSONObject(i);
                        String state_name=state_object.getString("stateName");
                        stateDatabaseHelper.insertNote(state_name);
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


    class ApiCall extends AsyncTask<Integer,Integer,Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            cityApi();
            return null;
        }
    }

    private void cityApi(){
        RequestQueue queue = Volley.newRequestQueue(AddAddress.this);
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
