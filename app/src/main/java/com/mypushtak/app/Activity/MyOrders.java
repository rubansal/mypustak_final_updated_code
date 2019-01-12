package com.mypushtak.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mypushtak.app.Adapters.CartItemsAdapter;
import com.mypushtak.app.Adapters.MyOrderAdapter;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.Bean.MySignleton;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.CartItems;
import com.mypushtak.app.Singleton.ProfileDetails;
import com.mypushtak.app.Singleton.my_orders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyOrders extends AppCompatActivity {

    private RecyclerView mRecylerView;
    private RecyclerView.Adapter mAdapter;
    private List<my_orders> order_list=new ArrayList<my_orders>();
    private ProgressBar progressBar;

    private ImageView back_button;
    private ProfileDetails pd=new ProfileDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        mRecylerView=findViewById(R.id.myorders_recylerview);
        back_button=findViewById(R.id.myorders_navigation);
        progressBar=findViewById(R.id.myorders_process);


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToMainActivity();
            }
        });

        String url= ConstantUrl.URL+"my_orders/"+pd.getId();
        progressBar.setVisibility(View.VISIBLE);

        fetchOrderlist(url);

        mRecylerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLinearLayout=new LinearLayoutManager(this);
        mRecylerView.setLayoutManager(mLinearLayout);
        Log.d("unique","activity");
        mAdapter=new MyOrderAdapter(this,order_list);
        mRecylerView.setAdapter(mAdapter);
        Log.d("unique","activity2");
    }

    private void fetchOrderlist(String url) {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONArray jsonArray=new JSONArray(response);


                            for (int i=0;i<jsonArray.length(); i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                long order_id=jsonObject.getInt("order_id");
                                long i_date=jsonObject.getLong("i_date");
                                int coupon=jsonObject.getInt("coupon");
                                String payusing=jsonObject.getString("payusing");
                                int status=jsonObject.getInt("status");
                                int book_id=jsonObject.getInt("book_id");
                                String thumb=jsonObject.getString("book_img");
                                int price=jsonObject.getInt("mrp");
                                int shipping=jsonObject.getInt("shipping");
                                int handling_charge=jsonObject.getInt("handling_charge");
                                int faster=jsonObject.getInt("fast_delivery");

                                my_orders myOrders=new my_orders(order_id,i_date,coupon,payusing,status,book_id,thumb,price,faster,shipping,handling_charge);

                                order_list.add(myOrders);
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


                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Your Order Cart is Empty",Toast.LENGTH_SHORT).show();

                Log.d("MarketingError",error.toString());
                error.printStackTrace();

            }
        });






        MySignleton.getInstance(getApplicationContext()).addToRequestqueue(stringRequest);

    }

    private void sendToMainActivity() {
        Intent i=new Intent(MyOrders.this,ProfileActivity.class);
        startActivity(i);
        finish();
    }
}
