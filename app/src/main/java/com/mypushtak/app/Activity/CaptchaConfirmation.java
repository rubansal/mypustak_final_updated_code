package com.mypushtak.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.EditAddressData;
import com.mypushtak.app.Singleton.ProfileDetails;

import java.util.Random;

public class CaptchaConfirmation extends AppCompatActivity {

    ProfileDetails pd=new ProfileDetails();
    int id=pd.getId();
    Toolbar mToolbar;
    TextView random1,random2,address,cost;
    EditText final_value;
    Button submit;
    int m,n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha_confirmation);

        mToolbar=findViewById(R.id.maintop);
        random1=findViewById(R.id.random1);
        random2=findViewById(R.id.random2);
        address=findViewById(R.id.captcha_address);
        cost=findViewById(R.id.captcha_Price);
        final_value=findViewById(R.id.captcha_edit);
        submit=findViewById(R.id.confirm_button);
        EditAddressData editAddressData=new EditAddressData();

        Log.d("delivery data11",""+editAddressData.getContact()+" "+editAddressData.getReciever()+" "+editAddressData.getPincode()+"  "+editAddressData.getTotal_amount());


        address.setText("Address: \n"+editAddressData.getReciever()+",\n"+editAddressData.getAddress()+",\nLandmark: "+editAddressData.getLandmark()+", City:"+editAddressData.getCity()
                +",\nState: "+editAddressData.getState()+", Country: India, \nPincode: "+editAddressData.getPincode()+", Phone no:"+editAddressData.getContact());

        cost.setText("Final Price: \n"+getApplicationContext().getResources().getString(R.string.Rs)+editAddressData.getTotal_amount());

        Random rand = new Random();

         n = rand.nextInt(50) + 1;
         m = rand.nextInt(99) + 1;

        random1.setText(""+n);
        random2.setText(""+m);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check=n+m;
                int value= Integer.parseInt(final_value.getText().toString());
                if(value==check)
                {

                }
            }
        });


    }
}
