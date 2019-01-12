package com.mypushtak.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mypushtak.app.Adapters.DeliveryAddressAdapter;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.Bean.MySignleton;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.CartItems;
import com.mypushtak.app.Singleton.Delivery_Address;
import com.mypushtak.app.Singleton.EditAddressData;
import com.mypushtak.app.Singleton.ProfileDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DeliveryAddress extends AppCompatActivity {

    private RecyclerView mRecylerView;
    private RecyclerView.Adapter mAdapter;
    private List<Delivery_Address> delivery_addressList=new ArrayList<Delivery_Address>();
    private ProgressBar progressBar;
    private String total_price;
    private Button add_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);
        mRecylerView=findViewById(R.id.address_recyclerview);
        progressBar=findViewById(R.id.delivery_progress);
        progressBar.setVisibility(View.VISIBLE);
        add_address=findViewById(R.id.address_button);


        mRecylerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLinearLayout=new LinearLayoutManager(this);
        mRecylerView.setLayoutManager(mLinearLayout);
        Log.d("unique","activity");
        mAdapter=new DeliveryAddressAdapter(this,delivery_addressList);
        mRecylerView.setAdapter(mAdapter);
        Log.d("unique","activity2");

        total_price=getIntent().getStringExtra("total_price");
        EditAddressData ed=new EditAddressData();
        ed.setTotal_amount(""+total_price);
        ProfileDetails pd=new ProfileDetails();
        final int id=pd.getId();

        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DeliveryAddress.this,AddAddress.class);
                i.putExtra("user_id",id);
                startActivity(i);
            }
        });



        String url= ConstantUrl.URL+"selectadd/"+id;
        fetchDeliveryAddress(url);
    }

    private void fetchDeliveryAddress(String url) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONArray jsonArray=new JSONArray(response);


                            for (int i=0;i<jsonArray.length(); i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                int s1=jsonObject.optInt("address_id");
                                int s2=jsonObject.optInt("user_id");
                                String s3=jsonObject.optString("title");
                                String s4=jsonObject.optString("rec_name");
                                int s5=jsonObject.optInt("pincode");
                                String s6=jsonObject.optString("address");
                                String s7=jsonObject.optString("landmark");
                                int s8=jsonObject.optInt("country_id");
                                int s9=jsonObject.optInt("state_id");
                                int s10=jsonObject.optInt("city_id");
                                long s11=jsonObject.optLong("phone_no");
                                String s12=jsonObject.optString("country");
                                String s13=jsonObject.optString("city");
                                String s14=jsonObject.optString("state_name");


                                Delivery_Address address=new Delivery_Address(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14);

                                delivery_addressList.add(address);
                                mAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("MarketingError",error.toString());
                error.printStackTrace();

            }
        });






        MySignleton.getInstance(getApplicationContext()).addToRequestqueue(stringRequest);

    }
    }
