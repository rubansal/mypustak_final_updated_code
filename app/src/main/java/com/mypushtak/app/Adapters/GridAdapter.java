package com.mypushtak.app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mypushtak.app.Activity.productfullview;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.ProductviewSignleton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class   GridAdapter extends BaseAdapter {


private List<ProductviewSignleton> productlist;
    private Context context;
    private LayoutInflater inflater;

    public GridAdapter( Context context,List<ProductviewSignleton> productlist) {
        this.productlist = productlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productlist.size();
    }

    @Override
    public Object getItem(int i) {
        return productlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
                View gridview =view;

        if (view==null)
        {
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridview=inflater.inflate(R.layout.custom_lyout,null);
        }

        final ImageView icon=gridview.findViewById(R.id.g1);
        TextView book_name=gridview.findViewById(R.id.bookname1);
        TextView mrp=gridview.findViewById(R.id.bookname2);
        TextView author=gridview.findViewById(R.id.bookname3);

        final ProductviewSignleton productviewSignleton=productlist.get(i);

        String thumb=productviewSignleton.getThumb();
        int book_id=productviewSignleton.getBook_id();
        String name=productviewSignleton.getTitle();
        String authors=productviewSignleton.getAuthor();
        final int price=productviewSignleton.getPrice();

        book_name.setText(name);
        author.setText("by "+authors);
        mrp.setText(context.getResources().getString(R.string.Rs)+price);

        Log.d("adapters","  "+thumb+"  "+book_id+"   "+"   "+name+"   "+author+"    "+price);

        Uri uri= Uri.parse("https://s3.amazonaws.com/mypustak_new/uploads/books/"+thumb);

//        Log.d("adapters",""+book_id+"   "+uri+"          "+name);
//        icon.setImageURI(uri);

        Picasso.get().load(uri).fit().into(icon);

//        letter.setText(productviewSignleton.getBook_id());
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,productfullview.class);
                i.putExtra("productfullview",""+productviewSignleton.getBook_id());
                ConstantUrl.setFull_product_view_price(price);
                context.startActivity(i);
            }
        });
        return gridview;
    }

//    public GridAdapter(Context context, int[] icons, String[] letters ) {
//        this.icons = icons;
//        this.letters = letters;
//        this.context = context;
//    }

//    @Override
//    public int getCount()
//    {
//        return letters.length;
//    }
//
//    @Override
//    public Object getItem(int position)
//    {
//        return letters[position];
//    }
//
//    @Override
//    public long getItemId(int position)
//    {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertview, ViewGroup parent)
//    {
//        View gridview =convertview;
//
//        if (convertview==null)
//        {
//            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            gridview=inflater.inflate(R.layout.custom_lyout,null);
//        }
//
//        ImageView icon=gridview.findViewById(R.id.g1);
//        TextView letter=(TextView)gridview.findViewById(R.id.txt);
//
//        icon.setImageResource(icons[position]);
//        letter.setText(letters[position]);
//        icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(context,productfullview.class);
//                context.startActivity(i);
//            }
//        });
//        return gridview;
//    }
}



