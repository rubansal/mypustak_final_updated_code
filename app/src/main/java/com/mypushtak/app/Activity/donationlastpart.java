package com.mypushtak.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mypushtak.app.R;

public class donationlastpart extends AppCompatActivity {

    Button makepayment;
    Button notmakepayment;

    int user_id;
    int i;

    Toolbar mToolbar;
    TextView mTextview;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationlastpart);

        mToolbar=findViewById(R.id.donationlastpart_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTextview=findViewById(R.id.change_password);
        mTextview.setText("DONATION");

        Toast.makeText(this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

        makepayment=findViewById(R.id.makepayment);
        notmakepayment=findViewById(R.id.notmakepayment);
        //home_back=findViewById(R.id.home_back);

        i=getIntent().getIntExtra("signup_activity",0);

        if(i==0)
            user_id=getIntent().getIntExtra("userid",0);
        else
            user_id=getIntent().getIntExtra("signup_user_id",0);
        Log.d("onCreate", "useriddonationlastpart"+user_id);
//        final int donation_reqs_id=getIntent().getIntExtra("donationreqsid",0);

//        home_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(donationlastpart.this,Donationform.class);
//                startActivity(i);
//                finish();
//            }
//        });

        notmakepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(donationlastpart.this, NoPayment.class);
                i.putExtra("donorid",user_id);
//                i.putExtra("donation_reqs_id",donation_reqs_id);
                startActivity(i);
            }
        });

        makepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
