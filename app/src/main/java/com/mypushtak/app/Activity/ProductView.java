package com.mypushtak.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mypushtak.app.Adapters.GridAdapter;
import com.mypushtak.app.Bean.MySignleton;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.ProductViewSingleton;
import com.mypushtak.app.Singleton.ProductviewSignleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductView extends AppCompatActivity {


    GridView gridView;
    ImageView home_back;
    GridAdapter adapter;
    private String s;
    private List<ProductviewSignleton> productlist=new ArrayList<ProductviewSignleton>();
    private ProgressBar progressBar;
    private TextView actionname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        gridView=(GridView)findViewById(R.id.gridView1);
        home_back=findViewById(R.id.navigationnn);
        progressBar=findViewById(R.id.product_view_progress);
        actionname=findViewById(R.id.action_name);





        progressBar.setVisibility(View.VISIBLE);
        String url=getIntent().getStringExtra("category");
        s=getIntent().getStringExtra("categoryname");

        ProductViewSingleton productViewSingleton=new ProductViewSingleton();
        productViewSingleton.setUrl(url);
        productViewSingleton.setCategory(s);


        actionname.setText(s);
//        Log.d("unique1",url);
        fetchData(url);

        adapter=new GridAdapter(ProductView.this,productlist);

        gridView.setAdapter(adapter);





        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ProductView.this, "selected", Toast.LENGTH_SHORT).show();
            }
        });


        home_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToHomeActivity();
            }
        });
    }

    private void fetchData(String url) {


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
                                int s3=jsonObject.optInt("book_id");
                                int s4=jsonObject.optInt("price");
                                int s5=jsonObject.optInt("category");
                                int s6=jsonObject.optInt("discount");

                                ProductviewSignleton productviewSignleton=new ProductviewSignleton(s3,s2,s1,s,s4,s5,s6,""+s);

                                productlist.add(productviewSignleton);
                                adapter.notifyDataSetChanged();

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

    private void sendToHomeActivity() {

        Intent i=new Intent(ProductView.this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();

    }

}

