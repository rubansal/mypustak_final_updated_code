package com.mypushtak.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mypushtak.app.R;

public class DonationStatus extends AppCompatActivity {

    Toolbar mToolbar;
    TextView mTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_status);
        mToolbar=findViewById(R.id.donation_status_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTextview=findViewById(R.id.change_password);
        mTextview.setText("DONATION STATUS");

    }
}
