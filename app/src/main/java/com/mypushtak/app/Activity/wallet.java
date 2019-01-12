package com.mypushtak.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mypushtak.app.R;

public class wallet extends AppCompatActivity {

    TextView mTextview;

    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        mToolbar=findViewById(R.id.wallet_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTextview=findViewById(R.id.change_password);
        mTextview.setText("WALLET");
    }
}
