package com.mypushtak.app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mypushtak.app.Activity.AddAddress;
import com.mypushtak.app.Activity.CartItemsActivity;
import com.mypushtak.app.Activity.CartQuantityChanges;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.Bean.MySignleton;
import com.mypushtak.app.Constant;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.CartItems;
import com.mypushtak.app.Singleton.ProductFullView;
import com.mypushtak.app.Singleton.ProductviewSignleton;
import com.mypushtak.app.Singleton.ProfileDetails;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;


/*
 ****@author Anubhav Kumar
 * *****
 */

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {
    ProfileDetails pd=new ProfileDetails();
    int id=pd.getId();


    private Context context;
    private List<CartItems> cartItems;

    public CartItemsAdapter(){

    }

    public CartItemsAdapter(Context context, List<CartItems> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }


    @NonNull
    @Override
    public CartItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_container,parent,false);

        return new CartItemsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartItemsAdapter.ViewHolder holder, final int position) {

        final CartItems cartItem=cartItems.get(position);

        final String thumb=cartItem.getThumb();
        final int book_id=cartItem.getBook_id();
        final String name=cartItem.getTitle();
        final String authors=cartItem.getAuthor();
        final int price=cartItem.getPrice();
     final   int qtys=cartItem.getQty();
        int shipping=cartItem.getShipping();
        int handling=cartItem.getHandelling();

        final int ship_hand=shipping+handling;
        holder.title.setText(name);
        holder.mrp.setText("MRP: "+context.getResources().getString(R.string.Rs)+price);
        holder.shipping.setText("SHIPPING+HANDELLING: "+context.getResources().getString(R.string.Rs)+ship_hand);
        holder.qty.setText(""+qtys);


//        Log.d("adapters","  "+thumb+"  "+book_id+"   "+"   "+name+"   "+author+"    "+price);

        Uri uri= Uri.parse("https://s3.amazonaws.com/mypustak_new/uploads/books/"+thumb);

        Log.d("adapters22",""+book_id+"   "+uri+"          "+name+"   qty:   "+qtys);


//        icon.setImageURI(uri);


        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qtys>1) {
//                    String url = ConstantUrl.URL + "remove_book/" + book_id + "/214";
//                    int quantity = qtys - 1;
//                    holder.qty.setText(quantity);
//                    minusBook(url);

                    Intent i=new Intent(context, CartQuantityChanges.class);
                    i.putExtra("quantity",""+qtys);
                    i.putExtra("title",""+name);
                    i.putExtra("thumb",""+thumb);
                    i.putExtra("author",""+authors);
                    i.putExtra("price",""+price);
                    i.putExtra("ship",""+ship_hand);
                    i.putExtra("book_id",""+book_id);

                    Log.d("aajkal",""+thumb+"  "+authors+"  "+ship_hand+" book_id"+book_id);
                    context.startActivity(i);
                }
            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
           // int quantity=qtys;
            @Override
            public void onClick(View view) {
               //String url= ConstantUrl.URL+"cart_insert/214/"+book_id;

               // int k=quantity++;
                Intent i=new Intent(context, CartQuantityChanges.class);
                i.putExtra("quantity",""+qtys);
                i.putExtra("title",""+name);
                i.putExtra("author",""+authors);
                i.putExtra("price",""+price);
                i.putExtra("thumb",""+thumb);
                i.putExtra("book_id",""+book_id);
                i.putExtra("ship",""+ship_hand);

                Log.d("aajkal",""+thumb+"  "+authors+"  "+ship_hand+" book_id"+book_id);
                context.startActivity(i);
               // holder.qty.setText(""+quantity);

                //  plusBook(url);
            }
        });

        Picasso.get().load(uri).fit().into(holder.book_image);

        holder.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWishlist(book_id);
                cartItems.remove(cartItem);
                new CartItemsAdapter().notifyItemRemoved(position);
                notifyDataSetChanged();

            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove_cart(book_id);
                cartItems.remove(cartItem);
                new CartItemsAdapter().notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });

    }

//    private void minusBook(String url) {
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.d("Unique12",""+response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.d("MarketingError",error.toString());
//                error.printStackTrace();
//
//            }
//        });
//        MySignleton.getInstance(getApplicationContext()).addToRequestqueue(stringRequest);
//    }

//    private void plusBook(String url) {
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.d("Unique12",""+response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.d("MarketingError",error.toString());
//                error.printStackTrace();
//
//            }
//        });
//        MySignleton.getInstance(getApplicationContext()).addToRequestqueue(stringRequest);
//    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }



    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title,mrp,shipping,qty;
        private ImageView book_image;
        private Button remove,wishlist,plus,minus;

        public ViewHolder(View itemView) {
            super(itemView);

            plus=itemView.findViewById(R.id.plus_button);
            minus=itemView.findViewById(R.id.minus_button);
            title=itemView.findViewById(R.id.cart_container_title);
            mrp=itemView.findViewById(R.id.cart_container_mrp);
            shipping=itemView.findViewById(R.id.cart_container_shipping);
            qty=itemView.findViewById(R.id.cart_containers_quan);
            book_image=itemView.findViewById(R.id.cart_container_image);
            remove=itemView.findViewById(R.id.cart_container_remove);
            wishlist=itemView.findViewById(R.id.cart_container_wishlist);
        }
    }

    private void remove_cart(int book_id) {

        RequestQueue donationqueue = Volley.newRequestQueue(context);
        String donor_url = ConstantUrl.URL+"remove_cart/"+book_id+"/"+id;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, donor_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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


    private void addWishlist(int book_id) {
        RequestQueue donationqueue = Volley.newRequestQueue(context);
        String donor_url = ConstantUrl.URL+"add_wishlist/"+book_id+"/"+id;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, donor_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
}

