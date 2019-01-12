package com.mypushtak.app.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mypushtak.app.Activity.OrderDetails;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.CartItems;
import com.mypushtak.app.Singleton.my_orders;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    private Context context;
    private List<my_orders> myOrdersList;
    private int cod_part;

    public MyOrderAdapter(Context context, List<my_orders> myOrdersList) {
        this.context = context;
        this.myOrdersList=myOrdersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_container,parent,false);

        return new ViewHolder(v);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final my_orders myOrders=myOrdersList.get(position);

        final long order_id=myOrders.getOrder_id();
        long i_date=myOrders.getI_date();
        int coupon=myOrders.getCoupon_amount();
        int status=myOrders.getStatus();
        String payusing=myOrders.getPayusing();
        int book_id=myOrders.getBook_id();
        String thumb=myOrders.getThumb();
        int mrp=myOrders.getMrp();
        int shippin_cost=myOrders.getShipping_cost();
        int handelling_charge=myOrders.getHandling_charge();
        int faster=myOrders.getFast_delivery();

        int ship_handle=shippin_cost+handelling_charge;

        String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (i_date*1000));
        Uri uri= Uri.parse("https://s3.amazonaws.com/mypustak_new/uploads/books/"+thumb);

        holder.orderid.setText(""+order_id);
        holder.orderdate.setText(""+date);
        holder.coupon.setText(""+context.getResources().getString(R.string.Rs)+coupon);
        holder.mrp.setText(""+context.getResources().getString(R.string.Rs)+mrp);
        holder.shipping.setText(""+context.getResources().getString(R.string.Rs)+ship_handle);

        if(faster==0&&payusing=="cod")
        {
            if(mrp<=150)
            {
                holder.cod.setText(""+context.getResources().getString(R.string.Rs)+70);
                cod_part=70;
            }
            else if(mrp>150)
            {
                holder.cod.setText(""+context.getResources().getString(R.string.Rs)+50);
                cod_part=50;
            }
        }
        else
            if(faster==1)
            {
                holder.cod.setText(""+context.getResources().getString(R.string.Rs)+2*shippin_cost);
                cod_part=2*shippin_cost;
            }
            else {

            holder.cod.setText(""+context.getResources().getString(R.string.Rs)+0);
            cod_part=0;

            }

        Picasso.get().load(uri).fit().into(holder.bookphoto);


        if(status==0)
        {
            holder.status.setText("Processing");
            holder.thanks.setEnabled(false);
        }
        if(status==1)
        {
            holder.status.setText("Complete");
            holder.thanks.setEnabled(true);
        }
        else if(status==2)
        {
            holder.status.setText("Cancel");
            holder.thanks.setEnabled(false);
        }
        else if(status==3)
        {
            holder.status.setText("Refund");
            holder.thanks.setEnabled(false);
        }
        else
        {
            holder.status.setText("Fail");
            holder.thanks.setEnabled(false);
        }

        int total=cod_part+ship_handle-coupon;

        holder.total.setText(""+context.getResources().getString(R.string.rs)+total);

        holder.orderdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, OrderDetails.class);
                i.putExtra("order_id",""+order_id);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myOrdersList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView orderid,orderdate,deliveryby,mrp,shipping,cod,coupon,status,total,refundcost;
        private ImageView bookphoto;
        private Button thanks,orderdetail;
        public ViewHolder(View itemView) {
            super(itemView);

            orderid=itemView.findViewById(R.id.orders_orderid);
            orderdate=itemView.findViewById(R.id.orders_order_date);
            deliveryby=itemView.findViewById(R.id.orders_deliveryby);
            mrp=itemView.findViewById(R.id.orders_price);
            shipping=itemView.findViewById(R.id.orders_shipping);
            cod=itemView.findViewById(R.id.orders_cod);
            coupon=itemView.findViewById(R.id.orders_coupon);
            status=itemView.findViewById(R.id.orders_status);
            total=itemView.findViewById(R.id.orders_total);
            refundcost=itemView.findViewById(R.id.refund_mrp);
            bookphoto=itemView.findViewById(R.id.orders_book_image);
            thanks=itemView.findViewById(R.id.orders_thanks);
            orderdetail=itemView.findViewById(R.id.orders_order_details);


            thanks.setEnabled(false);



        }
    }
}
