package com.mypushtak.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mypushtak.app.Adapters.CartItemsAdapter;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.Bean.MySignleton;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.ProfileDetails;
import com.squareup.picasso.Picasso;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;

public class CartQuantityChanges extends AppCompatActivity {

    ImageView book_image;
    TextView title,author,price,shipping,quantity;
    Button plus,minus,confirm;
    int num;
    ProfileDetails pd=new ProfileDetails();
    int id=pd.getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_quantity_changes);

        book_image=findViewById(R.id.cart_quantity_image);
        title=findViewById(R.id.cart_quantity_title);
        author=findViewById(R.id.cart_quantity_author);
        price=findViewById(R.id.cart_quantity_price);
        shipping=findViewById(R.id.cart_quantity_shipping);
        quantity=findViewById(R.id.cart_quantity_text);
        plus=findViewById(R.id.cart_quantity_plus);
        minus=findViewById(R.id.cart_quantity_minus);
        confirm=findViewById(R.id.cart_quantity_confirm);

        String img=getIntent().getStringExtra("thumb");
        final int qty = Integer.parseInt(getIntent().getStringExtra("quantity"));
        String titledata=getIntent().getStringExtra("title");
        String authordata=getIntent().getStringExtra("author");
        String pricedata=getIntent().getStringExtra("price");
        String ship=getIntent().getStringExtra("ship");
        final String book_id=getIntent().getStringExtra("book_id");

        Log.d("aajkal1",""+img+"  "+authordata+"  "+ship+"  "+book_id);


        Uri uri= Uri.parse("https://s3.amazonaws.com/mypustak_new/uploads/books/"+img);

        Picasso.get().load(uri).fit().into(book_image);

        title.setText(""+titledata);
        author.setText("By: \n"+authordata);
        price.setText("MRP: "+getResources().getString(R.string.Rs)+pricedata);
        shipping.setText("Shipping+Handling: \n"+getResources().getString(R.string.Rs)+ship);

        quantity.setText("QTY: \n"+ qty);
        num=qty;


        plus.setOnClickListener(new View.OnClickListener() {
            //int k=qty;
            @Override
            public void onClick(View view) {
                num+=1;
                quantity.setText("QTY: \n"+num );
                String url= ConstantUrl.URL+"cart_insert/"+id+"/"+book_id;
                plusBook(url);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int k=qty;
                if(num>1) {
                    //int num=k--;
                    num-=1;
                    quantity.setText("QTY: \n"+num );
                    String url = ConstantUrl.URL + "remove_book/" + book_id + "/"+id;
                    minusBook(url);
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(CartQuantityChanges.this, CartItemsActivity.class);
                startActivity(i);
                finish();
            }
        });



    }

    private void minusBook(String url) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Unique12",""+response);
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

    private void plusBook(String url) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Unique12",""+response);
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
