package com.mypushtak.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mypushtak.app.R;

public class AdditionalDeliveryAddress extends AppCompatActivity {

    Button add_another_address;
    Button add_delivery_edit;
    Toolbar mToolbar;
    TextView mTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_delivery_address);

        add_another_address=findViewById(R.id.add_another_address);
        add_delivery_edit=findViewById(R.id.additional_delivery_edit);
        mToolbar=findViewById(R.id.additional_delivery_toolbar);


        mTextview=findViewById(R.id.change_password);
        mTextview.setText("DELIVERY ADDRESS");


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add_another_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdditionalDeliveryAddress.this,AddAddress.class);
                startActivity(i);
            }
        });

        add_delivery_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdditionalDeliveryAddress.this, EditAddress.class);
                startActivity(i);
            }
        });
    }
}
