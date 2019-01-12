package com.mypushtak.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.mypushtak.app.Adapters.OfferZoneAdapter;
import com.mypushtak.app.R;

public class OfferZone extends AppCompatActivity {


    Toolbar mToolbar;
    TextView mTextview;
    private RecyclerView mRecylerView;
    private RecyclerView.Adapter mAdapter;
    String letterlist[]= {"A","B","C","D","E","F","G","H","I","J","K","L"
            ,"M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_zone);
        mRecylerView=findViewById(R.id.offer_zone_recycler);
        mToolbar=findViewById(R.id.offer_zone_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextview=findViewById(R.id.change_password);
        mTextview.setText("OFFER ZONE");

        mRecylerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLinearLayout=new LinearLayoutManager(this);
        mRecylerView.setLayoutManager(mLinearLayout);
        Log.d("unique","activity");
        mAdapter=new OfferZoneAdapter(this,letterlist);
        mRecylerView.setAdapter(mAdapter);
        Log.d("unique","activity2");

    }
}
