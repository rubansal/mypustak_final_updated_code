package com.mypushtak.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mypushtak.app.Adapters.CartItemsAdapter;
import com.mypushtak.app.Adapters.OrderDetailsAdapter;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.Bean.MySignleton;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.CartItems;
import com.mypushtak.app.Singleton.OrderDetailsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderDetails extends AppCompatActivity {

    private ImageView back_button;
    private TextView placed_on,order_no,total_amount,payment_mode,book_nos,ship_address,you_pay,cod,faster,discount,total,final_saving,final_amount;
    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private List<OrderDetailsData> order_details_list=new ArrayList<OrderDetailsData>();
    private ProgressBar progressBar;
    int total_amount_value=0,total_cod=0,total_faster=0,total_mrp=0;
    private OrderDetailsData orderDetailsData=new OrderDetailsData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        initialisation();

        String order_id=getIntent().getStringExtra("order_id");

        String url= ConstantUrl.URL+"order_details/"+order_id;

        fetchOrderDetails(url);



        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLinearLayout=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayout);
        Log.d("unique","activity");
        mAdapter=new OrderDetailsAdapter(this,order_details_list);
        mRecyclerView.setAdapter(mAdapter);
        Log.d("unique","activity2");







        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToOrdersActivity();
            }
        });
    }

    private void initialisation() {


        back_button=findViewById(R.id.navigationnn);
        placed_on=findViewById(R.id.order_details_container_placedon);
        order_no=findViewById(R.id.order_details_container_orderno);
        total_amount=findViewById(R.id.order_details_container_total_amount);
        payment_mode=findViewById(R.id.order_details_container_payment);
        book_nos=findViewById(R.id.order_details_container_number_books);
        ship_address=findViewById(R.id.order_details_container_shipping);
        you_pay=findViewById(R.id.order_details_container_you_pay);
        cod=findViewById(R.id.order_details_container_total_cod);
        faster=findViewById(R.id.order_details_container_total_faster);
        discount=findViewById(R.id.order_details_container_discounts);
        total=findViewById(R.id.order_details_container_total);
        final_amount=findViewById(R.id.order_details_container_final_amount);
        final_saving=findViewById(R.id.order_details_container_final_saving);
        mRecyclerView=findViewById(R.id.order_details_recycler_view);
        progressBar=findViewById(R.id.order_details_progress);

        progressBar.setVisibility(View.VISIBLE);


    }

    private void fetchOrderDetails(String url) {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONArray jsonArray=new JSONArray(response);


                            for (int i=0;i<jsonArray.length(); i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                int book_id=jsonObject.optInt("book_id");
                                int book_inv_id=jsonObject.optInt("book_inv_id");
                                int coupon_amount=jsonObject.optInt("coupon_amount");
                                int cod_charge=jsonObject.optInt("cod_charge");
                                int order_book_id=jsonObject.optInt("order_book_id");
                                int order_id=jsonObject.optInt("order_id");
                                double i_date=jsonObject.optDouble("i_date");
                                int amount=jsonObject.optInt("amount");
                                String payusing=jsonObject.optString("payusing");
                                int fast_delivery=jsonObject.optInt("fast_delivery");
                                int qty=jsonObject.optInt("qty");
                                int no_of_book=jsonObject.optInt("no_of_book");
                                int shipping_cost=jsonObject.optInt("shipping_cost");
                                String title=jsonObject.optString("title");
                                int price=jsonObject.optInt("price");
                                String thumb=jsonObject.optString("thumb");
                                String book_condition=jsonObject.optString("book_condition");
                                int handling_charge=jsonObject.optInt("handling_charge");
                                double pincode=jsonObject.optDouble("pincode");
                                double phone_no=jsonObject.optDouble("phone_no");
                                String rec_name=jsonObject.optString("rec_name");
                                String address=jsonObject.optString("address");
                                String landmark=jsonObject.optString("landmark");
                                String country=jsonObject.optString("country");
                                String city=jsonObject.optString("city");
                                String state_name=jsonObject.optString("state_name");

                                total_amount_value=total_amount_value+shipping_cost+handling_charge+cod_charge+2*shipping_cost*fast_delivery;
                                total_cod+=cod_charge;
                                total_faster=total_faster+2*shipping_cost*fast_delivery;
                                total_mrp+=price;



//                                total_shipping_cost+=s6;
//                                total_handelling_cost+=s7;

                                Log.d("orderDetails",""+order_no+"   "+payment_mode);

                                OrderDetailsData orderDetailsData=new OrderDetailsData(book_id,book_inv_id,coupon_amount,cod_charge,order_book_id,order_id,i_date,amount,payusing,fast_delivery,qty,no_of_book,shipping_cost,title,price,thumb,book_condition,handling_charge,rec_name,pincode,phone_no,address,landmark,country,city,state_name);


                                String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date ((long) (orderDetailsData.getI_date()*1000)));
                                placed_on.setText(""+date);
                                order_no.setText(""+orderDetailsData.getOrder_id());
                                total_amount.setText(""+getResources().getString(R.string.Rs)+total_amount_value);
                                payment_mode.setText(""+orderDetailsData.getPayusing());
                                book_nos.setText(""+orderDetailsData.getNo_of_book());
                                ship_address.setText(""+orderDetailsData.getAddress()+"\n"+orderDetailsData.getCity()+"\t"+orderDetailsData.getState_name()+"\n"+orderDetailsData.getCountry()+"\t"+orderDetailsData.getPincode());

                                you_pay.setText(""+getResources().getString(R.string.Rs)+orderDetailsData.getAmount());
                                cod.setText(""+getResources().getString(R.string.Rs)+total_cod);
                                faster.setText(""+getResources().getString(R.string.Rs)+total_faster);
                                discount.setText(""+orderDetailsData.getCoupon_amount());
                                total.setText(""+getResources().getString(R.string.Rs)+total_mrp);

                                final_amount.setText(""+getResources().getString(R.string.Rs)+orderDetailsData.getAmount());

                                int savings=total_mrp-orderDetailsData.getAmount();
                                final_saving.setText(""+getResources().getString(R.string.Rs)+savings);


                                order_details_list.add(orderDetailsData);
                                mAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);


//
//                                String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date ((long) (i_date*1000)));
//                                placed_on.setText(""+date);
//                                order_no.setText(""+order_id);
//                                total_amount.setText(""+getResources().getString(R.string.Rs)+total_amount_value);
//                                payment_mode.setText(""+payusing);
//                                book_nos.setText(""+no_of_book);
//                                ship_address.setText(""+address+"\n"+city+"\t"+state_name+"\n"+country+"\t"+pincode);
//
//                                you_pay.setText(""+getResources().getString(R.string.Rs)+amount);
//                                cod.setText(""+getResources().getString(R.string.Rs)+total_cod);
//                                faster.setText(""+getResources().getString(R.string.Rs)+total_faster);
//                                discount.setText(""+getResources().getString(R.string.Rs)+coupon_amount);
//                                total.setText(""+getResources().getString(R.string.Rs)+total_mrp);
//
//                                final_amount.setText(""+getResources().getString(R.string.Rs)+amount);
//
//                                int savings=total_mrp-amount;
//                                final_saving.setText(""+getResources().getString(R.string.Rs)+savings);






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



    private void sendToOrdersActivity() {
        Intent i=new Intent(OrderDetails.this,MyOrders.class);
        startActivity(i);
    }
}
