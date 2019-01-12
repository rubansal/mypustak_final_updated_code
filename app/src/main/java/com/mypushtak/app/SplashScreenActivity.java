package com.mypushtak.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.mypushtak.app.Activity.AddAddress;
import com.mypushtak.app.Activity.AdditionalDeliveryAddress;
import com.mypushtak.app.Activity.ChangePassword;
import com.mypushtak.app.Activity.DeliveryAddress;
import com.mypushtak.app.Activity.EditAddress;
import com.mypushtak.app.Activity.MainActivity;
import com.mypushtak.app.Activity.MyCoupons;
import com.mypushtak.app.Activity.MyOrders;
import com.mypushtak.app.Activity.OfferZone;
import com.mypushtak.app.Activity.OrderDetails;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timer = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(2000);
                    android.content.Intent intent = new android.content.Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    super.run();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        timer.start();
    }
    }

