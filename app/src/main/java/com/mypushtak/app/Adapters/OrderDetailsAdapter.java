package com.mypushtak.app.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.CartItems;
import com.mypushtak.app.Singleton.OrderDetailsData;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {

    private Context context;
    private List<OrderDetailsData> order_details;

    public OrderDetailsAdapter(Context context, List<OrderDetailsData> order_details) {
        this.context = context;
        this.order_details = order_details;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details_container,parent,false);

        return new OrderDetailsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final OrderDetailsData my_order_details=order_details.get(position);




        int shipping_cost=my_order_details.getShipping_cost()+my_order_details.getHandling_charge();
        Uri uri= Uri.parse("https://s3.amazonaws.com/mypustak_new/uploads/books/"+my_order_details.getThumb());


        Picasso.get().load(uri).fit().into(holder.thumb);
        holder.bookname.setText(my_order_details.getTitle());
        holder.mrp.setText(""+context.getResources().getString(R.string.Rs)+my_order_details.getPrice());
        holder.qty.setText(""+my_order_details.getQty());
        holder.condition.setText(""+my_order_details.getBook_condition());
        holder.shipping.setText(""+context.getResources().getString(R.string.Rs)+shipping_cost);



    }

    @Override
    public int getItemCount() {
        return order_details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookname,mrp,qty,condition,shipping;
        ImageView thumb;
        public ViewHolder(View itemView) {
            super(itemView);
            bookname=itemView.findViewById(R.id.order_details_container_book_name);
            mrp=itemView.findViewById(R.id.order_details_container_book_mrp);
            thumb=itemView.findViewById(R.id.orders_details_book_image);
            qty=itemView.findViewById(R.id.order_details_container_book_qty);
            condition=itemView.findViewById(R.id.order_details_container_book_condition);
            shipping=itemView.findViewById(R.id.order_details_container_book_pay);

        }
    }
}
