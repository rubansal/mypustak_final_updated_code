package com.mypushtak.app.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.Bean.MySignleton;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.ProductviewSignleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Schoolbooks extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private ImageView home_icon,university_image,competitive_image,fiction_image;

    private ImageView children_image,class_image,dic_image,ncert_image;

    private LinearLayout childrenbooks_layout,classes_layout,dictionary_layout,ncert_layout;
    private ImageView adventure,activity,literature,comics,disney,fun,mythology,knowledge,picture,others;

    private ImageView class1,class2,class3,class6,class4,class5,class7,class8,class9,class10,class11,class12;
    private ImageView engtoeng,foreign,engtohin,hintoeng,sub;
    private ImageView ncert6,ncert7,ncert8,ncert9,ncert10,ncert11,ncert12;
    Layout layout;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolbooks);



        initialization();



        children_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ncert_layout.setVisibility(View.GONE);
                classes_layout.setVisibility(View.GONE);
                dictionary_layout.setVisibility(View.GONE);
                childrenbooks_layout.setVisibility(View.VISIBLE);



            }
        });

        class_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ncert_layout.setVisibility(View.GONE);
                childrenbooks_layout.setVisibility(View.GONE);
                dictionary_layout.setVisibility(View.GONE);
                classes_layout.setVisibility(View.VISIBLE);
            }
        });

        dic_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ncert_layout.setVisibility(View.GONE);
                classes_layout.setVisibility(View.GONE);
                childrenbooks_layout.setVisibility(View.GONE);
                dictionary_layout.setVisibility(View.VISIBLE);
            }
        });

        ncert_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classes_layout.setVisibility(View.GONE);
                dictionary_layout.setVisibility(View.GONE);
                childrenbooks_layout.setVisibility(View.GONE);
                ncert_layout.setVisibility(View.VISIBLE);
            }
        });

        university_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Schoolbooks.this,Universitybooks.class);
                startActivity(i);
                finish();
            }
        });

        competitive_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Schoolbooks.this,Competitivebooks.class);
                startActivity(i);
                finish();
            }
        });


        fiction_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Schoolbooks.this,Fictionbooks.class);
                startActivity(i);
                finish();
            }
        });

        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(Schoolbooks.this,DividerItemDecoration.VERTICAL));

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            Intent i1=new Intent(this,ProfileActivity.class);
            startActivity(i1);
            return true;
        }else if(id == R.id.nav_school_bus){
            Intent i1=new Intent(this,ProductView.class);
            startActivity(i1);
//            Intent cartIntent = new Intent(Home.this, Cart.class);
//            startActivity(cartIntent);

        }else if(id == R.id.nav_univ_books){
            Intent i1=new Intent(this,ProfileDetailsActivity.class);
            startActivity(i1);
//            Intent orderIntent = new Intent(Home.this, OrderStatus.class);
//            startActivity(orderIntent);

        }else if(id == R.id.nav_compet_exams){
            Intent i1=new Intent(this,productfullview.class);
            startActivity(i1);
//            AccountKit.logOut();
//            Intent signIn = new Intent(Home.this, ScreenOneActivity.class);
//            signIn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(signIn);
        }
        else if(id == R.id.nav_fiction_books){
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
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//*****************************************INITIALIZATION *****************************************************
    private void initialization() {
        home_icon=findViewById(R.id.home_icon);
        university_image=findViewById(R.id.university_image);
        competitive_image=findViewById(R.id.competitive_image);
        fiction_image=findViewById(R.id.fiction_image);

        children_image=findViewById(R.id.children_image);
        class_image=findViewById(R.id.class_image);
        dic_image=findViewById(R.id.dic_image);
        ncert_image=findViewById(R.id.ncert_image);

        childrenbooks_layout=findViewById(R.id.childrenbooks_layout);
        classes_layout=findViewById(R.id.classes_layout);
        dictionary_layout=findViewById(R.id.dictionary_layout);
        ncert_layout=findViewById(R.id.ncert_layout);

        adventure=findViewById(R.id.adventure_image);
        activity=findViewById(R.id.activity_image);
        literature=findViewById(R.id.childrenlite_image);
        comics=findViewById(R.id.childrencomics_image);
        disney=findViewById(R.id.disney_image);
        fun=findViewById(R.id.fun_image);
        mythology=findViewById(R.id.mythologyg_image);
        knowledge=findViewById(R.id.childrenlearning_image);
        picture=findViewById(R.id.picturebook_image);
        others=findViewById(R.id.otherschildrenbooks_image);
        class1=findViewById(R.id.class1_image);
        class2=findViewById(R.id.class2_image);
        class3=findViewById(R.id.class3_image);
        class4=findViewById(R.id.class4_image);
        class5=findViewById(R.id.class5_image);
        class6=findViewById(R.id.class6_image);
        class7=findViewById(R.id.class7_image);
        class8=findViewById(R.id.class8_image);
        class9=findViewById(R.id.class9_image);
        class10=findViewById(R.id.class10_image);
        class11=findViewById(R.id.class11_image);
        class12=findViewById(R.id.class12_image);
        engtoeng=findViewById(R.id.engtoeng_image);
        foreign=findViewById(R.id.foreign_image);
        engtohin=findViewById(R.id.engtohin_image);
        hintoeng=findViewById(R.id.hintoeng_image);
        sub=findViewById(R.id.subjectwise_image);
        ncert6=findViewById(R.id.ncert6_image);
        ncert7=findViewById(R.id.ncert6_image);
        ncert8=findViewById(R.id.ncert6_image);
        ncert9=findViewById(R.id.ncert6_image);
        ncert10=findViewById(R.id.ncert6_image);
        ncert11=findViewById(R.id.ncert6_image);
        ncert12=findViewById(R.id.ncert6_image);





        adventure.setOnClickListener(this);
        activity.setOnClickListener(this);
        literature.setOnClickListener(this);
        comics.setOnClickListener(this);
        disney.setOnClickListener(this);
        fun.setOnClickListener(this);
        mythology.setOnClickListener(this);
        knowledge.setOnClickListener(this);
        picture.setOnClickListener(this);
        others.setOnClickListener(this);
        class1.setOnClickListener(this);
        class2.setOnClickListener(this);
        class3.setOnClickListener(this);
        class4.setOnClickListener(this);
        class5.setOnClickListener(this);
        class6.setOnClickListener(this);
        class7.setOnClickListener(this);
        class8.setOnClickListener(this);
        class9.setOnClickListener(this);
        class10.setOnClickListener(this);
        class11.setOnClickListener(this);
        class12.setOnClickListener(this);
        engtoeng.setOnClickListener(this);
        engtohin.setOnClickListener(this);
        foreign.setOnClickListener(this);
        hintoeng.setOnClickListener(this);
        sub.setOnClickListener(this);
        ncert6.setOnClickListener(this);
        ncert7.setOnClickListener(this);
        ncert8.setOnClickListener(this);
        ncert9.setOnClickListener(this);
        ncert10.setOnClickListener(this);
        ncert11.setOnClickListener(this);
        ncert12.setOnClickListener(this);

    }


    //*************************ONCLICK LISTENER*******************************************************************************

    @Override
    public void onClick(View view) {
        String url= ConstantUrl.URL+"preview/";
        int id=view.getId();
        Log.d("unique",""+id);

        switch (id)
        {
            case R.id.adventure_image: {


                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"256");
                i.putExtra("categoryname","Adventure");
                startActivity(i);
                break;
            }
            case R.id.activity_image: {
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"257");
                i.putExtra("categoryname","Activity");
                startActivity(i);
                break;
            }
            case R.id.childrenlite_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"258");
                i.putExtra("categoryname","Literature");
                startActivity(i);
                break;
            }

            case R.id.childrencomics_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"259");
                i.putExtra("categoryname","Comics");
                startActivity(i);
                break;
            }

            case R.id.disney_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"260");
                i.putExtra("categoryname","Disney");
                startActivity(i);
                break;
            }

            case R.id.fun_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"261");
                i.putExtra("categoryname","Humour");
                startActivity(i);
                break;
            }

            case R.id.mythologyg_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"262");
                i.putExtra("categoryname","History");
                startActivity(i);
                break;
            }

            case R.id.childrenlearning_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"263");
                i.putExtra("categoryname","Knowledge");
                startActivity(i);
                break;
            }

            case R.id.picturebook_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"264");
                i.putExtra("categoryname","Picture Book");
                startActivity(i);
                break;
            }

            case R.id.otherschildrenbooks_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"265");
                i.putExtra("categoryname","Others");
                startActivity(i);
                break;
            }

            case R.id.class1_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"268");
                i.putExtra("categoryname","Class 1");
                startActivity(i);
                break;
            }

            case R.id.class2_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"269");
                i.putExtra("categoryname","Class 2");
                startActivity(i);
                break;
            }

            case R.id.class3_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"270");
                i.putExtra("categoryname","Class 3");

                startActivity(i);
                break;
            }

            case R.id.class4_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"271");
                i.putExtra("categoryname","Class 4");
                startActivity(i);
                break;
            }

            case R.id.class5_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"272");
                i.putExtra("categoryname","Class 5");
                startActivity(i);
                break;
            }

            case R.id.class6_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"273");
                i.putExtra("categoryname","Class 6");
                startActivity(i);
                break;
            }

            case R.id.class7_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"274");
                i.putExtra("categoryname","Class 7");
                startActivity(i);
                break;
            }

            case R.id.class8_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"275");
                i.putExtra("categoryname","Class 8");
                startActivity(i);
                break;
            }

            case R.id.class9_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"276");
                i.putExtra("categoryname","Class 9");
                startActivity(i);
                break;
            }

            case R.id.class10_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"277");
                i.putExtra("categoryname","Class 10");
                startActivity(i);
                break;
            }

            case R.id.class11_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"278");
                i.putExtra("categoryname","Class 11");
                startActivity(i);
                break;
            }

            case R.id.class12_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"279");
                i.putExtra("categoryname","Class 10");
                startActivity(i);
                break;
            }

            case R.id.engtoeng_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"357");
                i.putExtra("categoryname","Class 11");
                startActivity(i);
                break;
            }

            case R.id.foreign_image:
            {
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"365");
                i.putExtra("categoryname","Class 12");
                startActivity(i);
                break;
            }
            case R.id.engtohin_image:
            {
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"358");
                i.putExtra("categoryname","English To Hindi");
                startActivity(i);
                break;
            }
            case R.id.hintoeng_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"359");
                i.putExtra("categoryname","Hindi To English");
                startActivity(i);
                break;
            }
            case R.id.subjectwise_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"356");
                i.putExtra("categoryname","Subjects");
                startActivity(i);
                break;
            }
            case R.id.ncert6_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"281");
                i.putExtra("categoryname","Class 6");
                startActivity(i);
                break;
            }
            case R.id.ncert7_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"282");
                i.putExtra("categoryname","Class 7");
                startActivity(i);
                break;
            }
            case R.id.ncert8_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"283");
                i.putExtra("categoryname","Class 8");
                startActivity(i);
                break;
            }
            case R.id.ncert9_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"284");
                i.putExtra("categoryname","Class 9");
                startActivity(i);
                break;
            }
            case R.id.ncert10_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"285");
                i.putExtra("categoryname","Class 10");
                startActivity(i);
                break;
            }
            case R.id.ncert11_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"286");
                i.putExtra("categoryname","Class 11");
                startActivity(i);
                break;
            }
            case R.id.ncert12_image:{
                Intent i = new Intent(Schoolbooks.this, ProductView.class);
                i.putExtra("category", url+"287");
                i.putExtra("categoryname","Class 12");
                startActivity(i);
                break;
            }

        }

    }



}
