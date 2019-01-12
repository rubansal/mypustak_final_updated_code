package com.mypushtak.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mypushtak.app.Adapters.CartItemsAdapter;
import com.mypushtak.app.Adapters.GridAdapter;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.Bean.MySignleton;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.CartItems;
import com.mypushtak.app.Singleton.Delivery_Address;
import com.mypushtak.app.Singleton.ProductviewSignleton;
import com.mypushtak.app.Singleton.ProfileDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartItemsActivity extends AppCompatActivity {

    private RecyclerView mRecylerView;
    private RecyclerView.Adapter mAdapter;
    private List<CartItems> cart_item_list=new ArrayList<CartItems>();
    private ProgressBar progressBar;
    private Button prepaid,faster,cod,select_adress;
    private ImageView back_button;
    private TextView cod_text,subtotal_text,faster_text;
    private int total_shipping_cost=0,total_handelling_cost=0,qty,subtotal;
    private CartItems cartItems=new CartItems();
    private ProfileDetails pd=new ProfileDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_items);

        initialisation();

        int id=pd.getId();
        String url= ConstantUrl.URL+"cart/"+id;

        fetchCartItems(url);

        mRecylerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLinearLayout=new LinearLayoutManager(this);
        mRecylerView.setLayoutManager(mLinearLayout);
        Log.d("unique","activity");
        mAdapter=new CartItemsAdapter(this,cart_item_list);
        mRecylerView.setAdapter(mAdapter);
        Log.d("unique","activity2");

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CartItemsActivity.this,productfullview.class);
                startActivity(i);
                finish();
            }
        });

        select_adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(CartItemsActivity.this, DeliveryAddress.class);
                i.putExtra("total_price",""+subtotal);

                startActivity(i);
            }
        });

        faster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faster_price();
            }
        });

        cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cod_price();
            }
        });

        prepaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                prepaid_price();


            }
        });
    }

    public void cod_price() {
        int price=0;
        int total_price=0;
       //List<CartItems> cart_item_list1=new ArrayList<CartItems>();



        for(int i=0;i<cart_item_list.size();i++)
        {
           // int qts=cart_item_list.get(i).getQty();
            qty=cart_item_list.get(i).getQty();
            Log.d("quantitys",""+qty);
            if(cart_item_list.get(i).getPrice()<=150)
            {
                total_price=total_price+(qty*70);
                int shipping=cart_item_list.get(i).getShipping();
                int handling=cart_item_list.get(i).getHandelling();
                price=price+qty*(shipping+handling);


//                cod_text.setText(""+getResources().getString(R.string.rs)+subtotal);
//                subtotal_text.setText(""+getResources().getString(R.string.rs)+subtotal);
//                faster_text.setText(""+getResources().getString(R.string.rs)+0);
            }
            else if(cart_item_list.get(i).getPrice()>150)
            {
                total_price=total_price+(qty*50);
                int shipping=cart_item_list.get(i).getShipping();
                int handling=cart_item_list.get(i).getHandelling();
                price=price+qty*(shipping+handling);
                //total_price=qty*(total_price+50);
//                cod_text.setText(""+getResources().getString(R.string.rs)+50);
//                subtotal_text.setText(""+getResources().getString(R.string.rs)+subtotal);
//                faster_text.setText(""+getResources().getString(R.string.rs)+0);
            }
        }

        subtotal=total_price+price;

        cod_text.setText(""+getResources().getString(R.string.Rs)+total_price);
        subtotal_text.setText(""+getResources().getString(R.string.Rs)+subtotal);
        faster_text.setText(""+getResources().getString(R.string.Rs)+0);




    }

    private void faster_price() {
        int faster=0;
        int price=0;

        for(int i=0;i<cart_item_list.size();i++)
        {
            int shipping=cart_item_list.get(i).getShipping();
            qty=cart_item_list.get(i).getQty();
            int handling=cart_item_list.get(i).getHandelling();
            price=price+qty*(shipping+handling);
            faster=faster+qty*2*shipping;


        }
        subtotal=faster+price;
        cod_text.setText(""+getResources().getString(R.string.Rs)+0);
        subtotal_text.setText(""+getResources().getString(R.string.Rs)+subtotal);
        faster_text.setText(""+getResources().getString(R.string.Rs)+faster);

    }

    private void prepaid_price() {

        //int price=total_shipping_cost+total_handelling_cost;
        int price=0;

        for(int i=0;i<cart_item_list.size();i++)
        {
            qty=cart_item_list.get(i).getQty();
            int shipping=cart_item_list.get(i).getShipping();
            int handling=cart_item_list.get(i).getHandelling();
            price=price+qty*(shipping+handling);

        }

         subtotal=price;
        cod_text.setText(""+getResources().getString(R.string.Rs)+0);
        subtotal_text.setText(""+getResources().getString(R.string.Rs)+subtotal);
        faster_text.setText(""+getResources().getString(R.string.Rs)+0);
    }

    private void initialisation() {
        mRecylerView=findViewById(R.id.cart_items_recycler);
        progressBar=findViewById(R.id.cart_items_progress);
        select_adress=findViewById(R.id.cart_items_delivery_address);
        back_button=findViewById(R.id.back_button);

        prepaid=findViewById(R.id.cart_items_prepaid_button);
        faster=findViewById(R.id.cart_items_faster_button);
        cod=findViewById(R.id.cart_items_cod_button);

        cod_text=findViewById(R.id.cart_items_cod);
        faster_text=findViewById(R.id.cart_items_faster);
        subtotal_text=findViewById(R.id.cart_items_subtotal);

        progressBar.setVisibility(View.VISIBLE);
    }

    private void fetchCartItems(String url) {

        Log.d("fetchCart",""+url);


        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("fetchCart",""+response.toString());
                        try {
                            JSONArray jsonArray=new JSONArray(response);


                            for (int i=0;i<jsonArray.length(); i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                String s=jsonObject.optString("author");
                                String s1=jsonObject.optString("thumb");
                                String s2=jsonObject.optString("title");
                                int s3=jsonObject.optInt("book_id");
                                int s4=jsonObject.optInt("price");
                                int s5=jsonObject.optInt("qty");
                                int s6=jsonObject.optInt("shipping_cost");
                                int s7=jsonObject.optInt("handelling_cost");

                                total_shipping_cost+=s6;
                                total_handelling_cost+=s7;

                                CartItems cartItems=new CartItems(s3,s4,s2,s1,s,s5,s6,s7);

                                cart_item_list.add(cartItems);
                                mAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);



                                Log.d("unique22",""+s3+"        "+s2+"    "+s1+"        "+s+"     "+s4+"        "+s5);




                            }
                            prepaid_price();

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