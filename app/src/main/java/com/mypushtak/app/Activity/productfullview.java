package com.mypushtak.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.Bean.MySignleton;
import com.mypushtak.app.Constant;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.ProductFullView;
import com.mypushtak.app.Singleton.ProductViewSingleton;
import com.mypushtak.app.Singleton.ProductviewSignleton;
import com.mypushtak.app.Singleton.ProfileDetails;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class productfullview extends AppCompatActivity {


    Toolbar mToolbar;
    private TextView toolbar_name;
    private ImageView book_thumb;
    private TextView book_name,author,mrp,shipping,donated_by;
    private ProgressBar progressBar;

    Button add_to_cart;

    int s3;

    int id;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.action_cart:
                if(id==0)
                {
                    Toast.makeText(getApplicationContext(),"You have to login first",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(productfullview.this,SignIn.class);
                    startActivity(i);
                }
                else
                {
                    Intent i=new Intent(productfullview.this,CartItemsActivity.class);
                    startActivity(i);
                    finish();
                }
                break;
            case R.id.action_share:
                Intent sharingIntent=new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody="Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(sharingIntent,"Share via"));
        }
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productfullview);
        mToolbar=findViewById(R.id.productfullview_toolbar);
        toolbar_name=findViewById(R.id.change_password);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_name.setText("PRODUCT");



        initialisation();
        ProfileDetails pf=new ProfileDetails();
        id=pf.getId();



        String s=getIntent().getStringExtra("productfullview");
        Log.d("superunique",""+s);


        String url= ConstantUrl.URL+"productfullview/"+s;

        fetchFullProductView(url);

        final ProductViewSingleton productViewSingleton=new ProductViewSingleton();

//        back_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(productfullview.this,ProductView.class);
//                Log.d("uniquevalue",""+productViewSingleton.getCategory()+"       "+productViewSingleton.getCategory());
//                i.putExtra("category",productViewSingleton.getUrl());
//                i.putExtra("categoryname",productViewSingleton.getCategory());
//
//                startActivity(i);
//                finish();
//            }
//        });

//        cart_image=findViewById(R.id.cart_image);
//        cart_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(id==0)
//                {
//                    Toast.makeText(getApplicationContext(),"You have to login first",Toast.LENGTH_SHORT).show();
//                    Intent i=new Intent(productfullview.this,SignIn.class);
//                    startActivity(i);
//                }
//                else
//                {
//                    Intent i=new Intent(productfullview.this,CartItemsActivity.class);
//                    startActivity(i);
//                }
//
//
//            }
//        });

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String date=df.format(Calendar.getInstance().getTime());

                if(id==0)
                {
                    Toast.makeText(getApplicationContext(),"You have to login first",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(productfullview.this,SignIn.class);
                    startActivity(i);
                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);
                    addtocart(id);
                }
            }
        });
    }

    private void initialisation() {

        book_thumb=findViewById(R.id.restaurant_image);
        book_name=findViewById(R.id.title);
        author=findViewById(R.id.author);
        mrp=findViewById(R.id.mrp);
        shipping=findViewById(R.id.shippinghandling);
        donated_by=findViewById(R.id.donate);
        //back_button=findViewById(R.id.back_button);
        progressBar=findViewById(R.id.productfullview_progress);
        progressBar.setVisibility(View.VISIBLE);
        add_to_cart=findViewById(R.id.add_to_cart);

    }

    private void fetchFullProductView(String url) {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONArray jsonArray=new JSONArray(response);


                            for (int i=0;i<jsonArray.length(); i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                String s=jsonObject.optString("author");
                                String s1=jsonObject.optString("thumb");
                                String s2=jsonObject.optString("title");
                                s3=jsonObject.optInt("book_id");
                                int s4=jsonObject.optInt("shipping_cost");
                                int handeling=jsonObject.optInt("handling_charge");
                                int s5=jsonObject.optInt("book_inv_id");
                                int s6=jsonObject.optInt("donation_inv_id");
                                int s7=jsonObject.optInt("donor_id");
                                String s8=jsonObject.optString("first_name");
                                String s9=jsonObject.optString("last_name");
                                int s10=jsonObject.optInt("price");

                                int total_ship=s4+handeling;

                                Uri uri= Uri.parse("https://s3.amazonaws.com/mypustak_new/uploads/books/"+s1);

                                book_name.setText(s2);
                                shipping.setText("Shipping+Handling: "+getResources().getString(R.string.Rs)+" "+total_ship);
                                mrp.setText("MRP: "+getResources().getString(R.string.Rs)+" "+ConstantUrl.getFull_product_view_price());
                                donated_by.setText("Donated by: "+s8+" "+s9);
                                author.setText("Author: "+s);
                                Picasso.get().load(uri).fit().into(book_thumb);


                                ProductFullView productFullView=new ProductFullView(s3,s2,s1,s,s4,handeling,s5,s6,s7,s8,s9,s10);


                                progressBar.setVisibility(View.GONE);



                                Log.d("unique",""+s3+"        "+s2+"    "+s1+"        "+s+"     "+s4+"        "+s5);




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

//    private void addtocart(String date) {
//        RequestQueue donationqueue = Volley.newRequestQueue(productfullview.this);
//        Log.d("unique12",""+date+s3);
//        String donor_url = ConstantUrl.URL+"cart_insert/215/"+s3+"/"+date+"/N/N/N";
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, donor_url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("unique12","response hai");
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("VOLLEY", error.toString());
//            }
//        }) {
//            @Override
//            public String getBodyContentType() {
//                return "application/json; charset=utf-8";
//            }
//
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                Log.d("unique12",""+"parsewale");
//                String responseString = "";
//                if (response != null) {
//                    responseString = String.valueOf(response.statusCode);
//                    progressBar.setVisibility(View.GONE);
//                    // can get more details such as response.headers
//                    Log.d("onResponseString", "response: " + responseString);
//                }
//                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//            }
//        };
//
//        donationqueue.add(stringRequest);
//    }

    private void addtocart(int user_id) {

       // String donor_url = ConstantUrl.URL+"cart_insert/215/"+s3+"/"+date+"/N/N/N";
        String donor_url= ConstantUrl.URL+"cart_insert/"+user_id+"/"+s3;
        Log.d("unique12",""+donor_url);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, donor_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("unique12","updated");

                                progressBar.setVisibility(View.GONE);


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
