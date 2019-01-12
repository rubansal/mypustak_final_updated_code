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
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.R;

public class Universitybooks extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private ImageView home_icon,school_image,competitive_image,fiction_image;

    private ImageView engineering_image, medical_image,scienceandarts_image,otheruniversity_image;

    private LinearLayout engineering_layout,medical_layout,scienceandarts_layout, otheruniversity_layout;

    private ImageView aeuronautical,biotech,chemical,civil,cse,electrical,electronic,marine,mechanical,engineerothers;
    private ImageView ayurveda,dental,mbbs,pharmacy,pg,medicalother;
    private ImageView ba,bca,bsc,ma,msc,mca;
    private ImageView bba,bcom,law,mba,mcom,phd,others,finance;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universitybooks);

       initialisation();

        engineering_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otheruniversity_layout.setVisibility(View.GONE);
                scienceandarts_layout.setVisibility(View.GONE);
                medical_layout.setVisibility(View.GONE);
                engineering_layout.setVisibility(View.VISIBLE);
            }
        });

        medical_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otheruniversity_layout.setVisibility(View.GONE);
                scienceandarts_layout.setVisibility(View.GONE);
                engineering_layout.setVisibility(View.GONE);
                medical_layout.setVisibility(View.VISIBLE);
            }
        });

        scienceandarts_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otheruniversity_layout.setVisibility(View.GONE);
                medical_layout.setVisibility(View.GONE);
                engineering_layout.setVisibility(View.GONE);
                scienceandarts_layout.setVisibility(View.VISIBLE);
            }
        });

        otheruniversity_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scienceandarts_layout.setVisibility(View.GONE);
                medical_layout.setVisibility(View.GONE);
                engineering_layout.setVisibility(View.GONE);
                otheruniversity_layout.setVisibility(View.VISIBLE);
            }
        });

        school_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Universitybooks.this,Schoolbooks.class);
                startActivity(i);
                finish();
            }
        });

        competitive_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Universitybooks.this,Competitivebooks.class);
                startActivity(i);
                finish();
            }
        });


        fiction_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Universitybooks.this,Fictionbooks.class);
                startActivity(i);
                finish();
            }
        });

        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(Universitybooks.this,DividerItemDecoration.VERTICAL));

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


    private void initialisation() {
        home_icon=findViewById(R.id.home_icon);
        school_image=findViewById(R.id.school_image);
        competitive_image=findViewById(R.id.competitive_image);
        fiction_image=findViewById(R.id.fiction_image);

        engineering_image=findViewById(R.id.engineering_image);
        medical_image=findViewById(R.id.medical_image);
        scienceandarts_image=findViewById(R.id.scienceandarts_image);
        otheruniversity_image=findViewById(R.id.otheruniversity_image);

        engineering_layout=findViewById(R.id.engineering_layout);
        medical_layout=findViewById(R.id.medical_layout);
        scienceandarts_layout=findViewById(R.id.scienceandarts_layout);
        otheruniversity_layout=findViewById(R.id.otheruniversity_layout);
        finance=findViewById(R.id.finance_image);

        aeuronautical=findViewById(R.id.aeronautical_image);
        biotech=findViewById(R.id.bio_image);
        chemical=findViewById(R.id.chem_image);
        civil=findViewById(R.id.civil_image);
        cse=findViewById(R.id.compsc_image);
        electrical=findViewById(R.id.electrical_image);
        electronic=findViewById(R.id.electronic_image);
        marine=findViewById(R.id.marine_image);
        mechanical=findViewById(R.id.mechanical_image);
        engineerothers=findViewById(R.id.othersengineering_image);

        ayurveda=findViewById(R.id.ayur_image);
        dental=findViewById(R.id.dental_image);
        mbbs=findViewById(R.id.mbbs_image);
        pharmacy=findViewById(R.id.pharm_image);
        pg=findViewById(R.id.pg_image);
        medicalother=findViewById(R.id.othermedical_image);

        ba=findViewById(R.id.ba_image);
        bca=findViewById(R.id.bca_image);
        bsc=findViewById(R.id.bsc_image);
        ma=findViewById(R.id.ma_image);
        msc=findViewById(R.id.msc_image);
        mca=findViewById(R.id.mca_image);

        bba=findViewById(R.id.bba_image);
        bcom=findViewById(R.id.bcom_image);
        law=findViewById(R.id.law_image);
        mba=findViewById(R.id.mba_image);
        mcom=findViewById(R.id.mcom_image);
        phd=findViewById(R.id.phd_image);
        others=findViewById(R.id.otheruniversitypart_image);


        aeuronautical.setOnClickListener(this);
        biotech.setOnClickListener(this);
        chemical.setOnClickListener(this);
        civil.setOnClickListener(this);
        cse.setOnClickListener(this);
        electronic.setOnClickListener(this);
        electrical.setOnClickListener(this);
        marine.setOnClickListener(this);
        mechanical.setOnClickListener(this);
        engineerothers.setOnClickListener(this);
        ayurveda.setOnClickListener(this);
        dental.setOnClickListener(this);
        mbbs.setOnClickListener(this);
        pharmacy.setOnClickListener(this);
        pg.setOnClickListener(this);
        medicalother.setOnClickListener(this);
        ba.setOnClickListener(this);
        bca.setOnClickListener(this);
        bsc.setOnClickListener(this);
        ma.setOnClickListener(this);
        msc.setOnClickListener(this);
        mca.setOnClickListener(this);
        bba.setOnClickListener(this);
        bcom.setOnClickListener(this);
        law.setOnClickListener(this);
        mba.setOnClickListener(this);
        mcom.setOnClickListener(this);
        phd.setOnClickListener(this);
        others.setOnClickListener(this);
        finance.setOnClickListener(this);






    }

    @Override
    public void onClick(View view) {

        String url= ConstantUrl.URL+"preview/";
        int id=view.getId();
        Log.d("unique",""+id);



        switch (id)
        {
            case R.id.finance_image: {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "360");
                i.putExtra("categoryname","Finance");
                startActivity(i);
                break;
            }

            case R.id.aeronautical_image: {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "305");
                i.putExtra("categoryname","Aeronautical");
                startActivity(i);
                break;
            }
            case R.id.bio_image: {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "306");
                i.putExtra("categoryname","Bio-Technology");
                startActivity(i);
            }
                break;
            case R.id.chem_image: {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "307");
                i.putExtra("categoryname","Chemical");
                startActivity(i);
                break;
            }
            case R.id.civil_image: {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "308");
                i.putExtra("categoryname","Civil");
                startActivity(i);
                break;
            }
            case R.id.compsc_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "309");
                i.putExtra("categoryname","Computer Science");
                startActivity(i);
                break;
            }
            case R.id.electrical_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "310");
                i.putExtra("categoryname","Electrical");
                startActivity(i);
                break;
            }
            case R.id.electronic_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "311");
                i.putExtra("categoryname","Electronics");
                startActivity(i);
                break;
            }
            case R.id.marine_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "312");
                i.putExtra("categoryname","Marine");
                startActivity(i);
                break;
            }
            case R.id.mechanical_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "313");
                i.putExtra("categoryname","Mechanical");
                startActivity(i);
                break;
            }
            case R.id.othersengineering_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "314");
                i.putExtra("categoryname","Other Books");
                startActivity(i);
                break;
            }
            case R.id.ayur_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "316");
                i.putExtra("categoryname","Ayurveda");
                startActivity(i);
                break;
            }
            case R.id.dental_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "317");
                i.putExtra("categoryname","Dental");
                startActivity(i);
                break;
            }
            case R.id.mbbs_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "318");
                i.putExtra("categoryname","MBBS");
                startActivity(i);
                break;
            }
            case R.id.pharm_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "319");
                i.putExtra("categoryname","Pharmacy");
                startActivity(i);
                break;
            }
            case R.id.pg_image:{
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "320");
                i.putExtra("categoryname","P.G.");
                startActivity(i);
                break;
            }
            case R.id.othermedical_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "321");
                i.putExtra("categoryname","Other Books");
                startActivity(i);
                break;
            }
            case R.id.ba_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "323");
                i.putExtra("categoryname","BA");
                startActivity(i);
                break;
            }
            case R.id.bca_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "324");
                i.putExtra("categoryname","BCA");
                startActivity(i);
                break;
            }
            case R.id.bsc_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "325");
                i.putExtra("categoryname","BSc");
                startActivity(i);
                break;
            }
            case R.id.ma_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "326");
                i.putExtra("categoryname","MA");
                startActivity(i);
                break;
            }
            case R.id.mca_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "327");
                i.putExtra("categoryname","MCA");
                startActivity(i);
                break;
            }
            case R.id.bba_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "330");
                i.putExtra("categoryname","BBA");
                startActivity(i);
                break;
            }
            case R.id.bcom_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "331");
                i.putExtra("categoryname","B.Com.");
                startActivity(i);
                break;
            }
            case R.id.law_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "332");
                i.putExtra("categoryname","LAW");
                startActivity(i);
                break;
            }
            case R.id.mba_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "333");
                i.putExtra("categoryname","M.B.A.");
                startActivity(i);
                break;
            }
            case R.id.mcom_image:{
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "334");
                i.putExtra("categoryname","M.Com.");
                startActivity(i);
                break;
            }
            case R.id.phd_image:{
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "335");
                i.putExtra("categoryname","PhD.");
                startActivity(i);
                break;
            }
            case R.id.otheruniversitypart_image:
            {
                Intent i = new Intent(Universitybooks.this, ProductView.class);
                i.putExtra("category", url + "336");
                i.putExtra("categoryname","OTHER BOOKS");
                startActivity(i);
                break;
            }






        }

    }
}
