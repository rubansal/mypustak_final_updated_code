package com.mypushtak.app.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mypushtak.app.R;

public class OfferZoneAdapter extends RecyclerView.Adapter<OfferZoneAdapter.ViewHolder>  {

    private Context context;

    private String letters[];

    public OfferZoneAdapter(Context context, String[] letters) {
        this.context = context;
        this.letters = letters;
    }

    @NonNull
    @Override
    public OfferZoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_zone_container,parent,false);

        return new OfferZoneAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferZoneAdapter.ViewHolder holder, int position) {

        holder.offers.setText(letters[position]);
    }

    @Override
    public int getItemCount() {
        return letters.length;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView offers;

        public ViewHolder(View itemView) {
            super(itemView);

            offers=itemView.findViewById(R.id.offer_container_textview);
        }
    }
}
