package com.mypushtak.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.Bean.MySignleton;
import com.mypushtak.app.Bean.PrefUtil;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.ProfileDetails;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    LinearLayout donatebook_layout;
    LinearLayout getbook_layout;
    CircleImageView profile_image;

    ImageView school_image;
    ImageView university_image;
    ImageView competitive_image;
    ImageView fiction_image;

    ImageView home_icon;

    DrawerLayout drawerLayout;
    private ProfileDetails pd=new ProfileDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donatebook_layout=findViewById(R.id.donatebook_layout);
        getbook_layout=findViewById(R.id.getbook_layout);
        profile_image=findViewById(R.id.profile_image);
        school_image=findViewById(R.id.school_image);
        university_image=findViewById(R.id.university_image);
        competitive_image=findViewById(R.id.competitive_image);
        fiction_image=findViewById(R.id.fiction_image);

        home_icon=findViewById(R.id.home_icon);

        donatebook_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Donation.class);
                startActivity(i);
            }
        });

        getbook_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,ProductView.class);
                i.putExtra("category",ConstantUrl.URL+"getbooks");
                i.putExtra("categoryname","All Books");
                startActivity(i);
            }
        });

        school_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Schoolbooks.class);
                startActivity(i);
            }
        });

        university_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Universitybooks.class);
                startActivity(i);
            }
        });

        competitive_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Competitivebooks.class);
                startActivity(i);
            }
        });

        fiction_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Fictionbooks.class);
                startActivity(i);
            }
        });


        //this is for right navigation bar
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
//        }

        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));

        navigationView.getMenu().getItem(1).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(2).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(4).setActionView(R.layout.menu_image);

        navigationView.setNavigationItemSelectedListener(this);

        home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.openDrawer(Gravity.START);
                else
                    drawerLayout.closeDrawer(Gravity.END);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.nav_account) {

            int user_id=pd.getId();
            if(user_id==0)
            {
                Toast.makeText(getApplicationContext(),"You have to login first",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,SignIn.class);
                startActivity(i);
            }
            else
            {
                Intent i1=new Intent(this,ProfileActivity.class);
                startActivity(i1);
                return true;
            }

        }else if(id == R.id.nav_school_bus){
            Intent i1=new Intent(this,Schoolbooks.class);
            startActivity(i1);
//            Intent cartIntent = new Intent(Home.this, Cart.class);
//            startActivity(cartIntent);

        }else if(id == R.id.nav_univ_books){
            Intent i1=new Intent(this,Universitybooks.class);
            startActivity(i1);
//            Intent orderIntent = new Intent(Home.this, OrderStatus.class);
//            startActivity(orderIntent);

        }else if(id == R.id.nav_compet_exams){
            Intent i1=new Intent(this,Competitivebooks.class);
            startActivity(i1);
//            AccountKit.logOut();
//            Intent signIn = new Intent(Home.this, ScreenOneActivity.class);
//            signIn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(signIn);
        }
        else if(id == R.id.nav_fiction_books){
            Intent i1=new Intent(this,Fictionbooks.class);
            startActivity(i1);
//            showChangePasswordDialog();

        }
        else if(id == R.id.nav_faqs){
//            startActivity(new Intent(Home.this,NearbyStore.class));
        }
        else if(id == R.id.nav_con_us){
//            showHomeAddressDialog();

        }
        else if(id == R.id.nav_rate_us){
//            showHomeSettingDialog();
        }
        else if(id == R.id.nav_abt_us){
//            startActivity(new Intent(Home.this,FavoritesActivity.class));
        }
        else if(id == R.id.nav_term_of_use){
//            startActivity(new Intent(Home.this,FavoritesActivity.class));
        }
        else if(id == R.id.nav_prd_donors){
//            startActivity(new Intent(Home.this,FavoritesActivity.class));
        }

        else if (id==R.id.log_out)
        {
//            PrefUtil pf=new PrefUtil(MainActivity.this);
//            pf.clearToken();
//            ProfileDetails pd=new ProfileDetails(Integer.parseInt(null),"","","","",Long.parseLong(null),"");

            int user_id=pd.getId();
            if(user_id==0)
            {
                Toast.makeText(getApplicationContext(),"You have to login first",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,SignIn.class);
                startActivity(i);
            }
            else
            {

            PrefUtil pf=new PrefUtil(MainActivity.this);
            pf.clearToken();
            ProfileDetails pd=new ProfileDetails(0,"","","","",Long.parseLong("0"),"");
            Intent i=new Intent(MainActivity.this,MainActivity.class);
            startActivity(i);
            finish();
            }



        }
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void movetoprofile(View view)
//    {
//        Intent i=new Intent(MainActivity.this,ProfileDetails.class);
//        startActivity(i);
//    }
    public void movetologin(View view)
    {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String first_name= prefs.getString("fb_first_name","");
        String last_name= prefs.getString("fb_last_name","");
        String email= prefs.getString("fb_email",null);

        if(email!=null)
        {
            Log.d("unique",""+email+"  "+first_name);
            Toast.makeText(getApplicationContext(),"You are already signed in",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent i=new Intent(MainActivity.this,SignIn.class);
            startActivity(i);
        }
    }

    public void movetosignup(View view)
    {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String first_name= prefs.getString("fb_first_name","");
        String last_name= prefs.getString("fb_last_name","");
        String email= prefs.getString("fb_email",null);

        if(email!=null)
        {
            Toast.makeText(getApplicationContext(),"You are already signed in",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent i=new Intent(MainActivity.this,Signup.class);
            startActivity(i);
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        PrefUtil prefUtil=new PrefUtil(MainActivity.this);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String first_name= prefs.getString("fb_first_name","");
            String last_name= prefs.getString("fb_last_name","");
            String email= prefs.getString("fb_email","");
        String password= prefs.getString("fb_password","");
        String url= ConstantUrl.URL+"user_signin/"+email;
        Log.d("onstartmein",""+first_name+email+password);
        signInFetch(url);

    }
    public void signInFetch(String url) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONArray jsonArray=new JSONArray(response);


                            for (int i=0;i<jsonArray.length(); i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                int id=jsonObject.optInt("id");
                                String name=jsonObject.optString("first_name");
                                String email=jsonObject.optString("emailid");
                                String password=jsonObject.optString("password");
                                String alternative_email=jsonObject.optString("alternative_email");
                                String avatar=jsonObject.optString("avatar");
                                Long number=jsonObject.optLong("mobile");


                                //Picasso.get().load(avatar).into(profile_image);

                                ProfileDetails profileDetails=new ProfileDetails(id,email,password,alternative_email,avatar,number,name);

                                Log.d("onstartknichey",""+id+" "+email+" "+password+"  "+profileDetails.getAvatar()+"  "+profileDetails.getEmail());


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("MarketingError",error.toString());
                error.printStackTrace();

            }
        });
        MySignleton.getInstance(getApplicationContext()).addToRequestqueue(stringRequest);
    }
}
