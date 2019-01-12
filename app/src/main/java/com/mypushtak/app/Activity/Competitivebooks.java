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

public class Competitivebooks extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    ImageView engandmed_image, government_image, international_image,schoollevel_image;

    ImageView school_image,university_image,fiction_image, home_icon;

    DrawerLayout drawerLayout;

    LinearLayout engineeringbooks_layout, governmentjobs_layout, internationalexams_layout, schoollevel_layout;
    ImageView cat,aiee,iit,engpg,med,state,engmedothers,knowledge,banking,railway,govtiit,teach,upst,govtothers,gmat,interrail,sat,inter,others,ntse,olympaid;
    ImageView navodya,sainik,schoolothers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competitivebooks);

        initialisation();

        engandmed_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                governmentjobs_layout.setVisibility(View.GONE);
                internationalexams_layout.setVisibility(View.GONE);
                schoollevel_layout.setVisibility(View.GONE);
                engineeringbooks_layout.setVisibility(View.VISIBLE);
            }
        });

        government_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engineeringbooks_layout.setVisibility(View.GONE);
                internationalexams_layout.setVisibility(View.GONE);
                schoollevel_layout.setVisibility(View.GONE);
                governmentjobs_layout.setVisibility(View.VISIBLE);
            }
        });

        international_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engineeringbooks_layout.setVisibility(View.GONE);
                governmentjobs_layout.setVisibility(View.GONE);
                schoollevel_layout.setVisibility(View.GONE);
                internationalexams_layout.setVisibility(View.VISIBLE);
            }
        });

        schoollevel_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engineeringbooks_layout.setVisibility(View.GONE);
                governmentjobs_layout.setVisibility(View.GONE);
                internationalexams_layout.setVisibility(View.GONE);
                schoollevel_layout.setVisibility(View.VISIBLE);
            }
        });

        school_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Competitivebooks.this,Schoolbooks.class);
                startActivity(i);
                finish();
            }
        });

        university_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Competitivebooks.this,Universitybooks.class);
                startActivity(i);
                finish();
            }
        });


        fiction_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Competitivebooks.this,Fictionbooks.class);
                startActivity(i);
                finish();
            }
        });

        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(Competitivebooks.this,DividerItemDecoration.VERTICAL));

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
        engandmed_image=findViewById(R.id.engandmed_image);
        government_image=findViewById(R.id.government_image);
        international_image=findViewById(R.id.international_image);
        schoollevel_image=findViewById(R.id.schoollevel_image);

        home_icon=findViewById(R.id.home_icon);

        school_image=findViewById(R.id.school_image);
        university_image=findViewById(R.id.university_image);
        fiction_image=findViewById(R.id.fiction_image);

        engineeringbooks_layout=findViewById(R.id.engineeringbooks_layout);
        governmentjobs_layout=findViewById(R.id.governmentjobs_layout);
        internationalexams_layout=findViewById(R.id.internationalexams_layout);
        schoollevel_layout=findViewById(R.id.schoollevel_layout);

        cat=findViewById(R.id.children_image);
        aiee=findViewById(R.id.aieee_image);
        engpg=findViewById(R.id.gate_image);
        iit=findViewById(R.id.iit_image);
        med=findViewById(R.id.med_image);
        state=findViewById(R.id.statelevel_image);
        engmedothers=findViewById(R.id.other2_image);
        knowledge=findViewById(R.id.dic_image);
        banking=findViewById(R.id.banking_image);
        railway=findViewById(R.id.railway_image);
        govtiit=findViewById(R.id.ssc_image);
        teach=findViewById(R.id.teaching_image);
        upst=findViewById(R.id.upsc_image);
        govtothers=findViewById(R.id.othergovernmentjob_image);
        gmat=findViewById(R.id.gmat_image);
        interrail=findViewById(R.id.gre_image);
        sat=findViewById(R.id.sat_image);
        others=findViewById(R.id.otherinternationalexam_image);
        ntse=findViewById(R.id.ntse_image);
        olympaid=findViewById(R.id.olympaid_image);
        navodya=findViewById(R.id.schoollevelparts_image);
        sainik=findViewById(R.id.sainikschool_image);
        schoolothers=findViewById(R.id.otherschoollevel_image);

        cat.setOnClickListener(this);
        aiee.setOnClickListener(this);
        engpg.setOnClickListener(this);
        iit.setOnClickListener(this);
        med.setOnClickListener(this);
        state.setOnClickListener(this);
        engmedothers.setOnClickListener(this);
        knowledge.setOnClickListener(this);
        banking.setOnClickListener(this);
        railway.setOnClickListener(this);
        iit.setOnClickListener(this);
        teach.setOnClickListener(this);
        upst.setOnClickListener(this);
        govtothers.setOnClickListener(this);
        gmat.setOnClickListener(this);
        interrail.setOnClickListener(this);
        sat.setOnClickListener(this);
        others.setOnClickListener(this);
        ntse.setOnClickListener(this);
        olympaid.setOnClickListener(this);
        navodya.setOnClickListener(this);
        sainik.setOnClickListener(this);
        schoolothers.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        String url= ConstantUrl.URL+"preview/";
        int id=view.getId();
        Log.d("unique",""+id);

        switch (id)
        {

            case R.id.children_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "361");
                i.putExtra("categoryname","CAT");
                startActivity(i);
                break;
            }

            case R.id.aieee_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "194");
                i.putExtra("categoryname","AIEEE");
                startActivity(i);
                break;
            }

            case R.id.gate_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "195");
                i.putExtra("categoryname","ENGINEERING PG");
                startActivity(i);
                break;
            }

            case R.id.iit_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "196");
                i.putExtra("categoryname","IIT");
                startActivity(i);
                break;
            }

            case R.id.medical_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "342");
                i.putExtra("categoryname","MEDICAL");
                startActivity(i);
                break;
            }

            case R.id.statelevel_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "197");
                i.putExtra("categoryname","STATE LEVEL");
                startActivity(i);
                break;
            }

            case R.id.othersengineering_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "198");
                i.putExtra("categoryname","OTHER BOOKS");
                startActivity(i);
                break;
            }

            case R.id.dic_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "263");
                i.putExtra("categoryname","KNOWLEDGE AND LEARNING");
                startActivity(i);
                break;
            }

            case R.id.banking_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "199");
                i.putExtra("categoryname","BANKING");
                startActivity(i);
                break;
            }

            case R.id.railway_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "201");
                i.putExtra("categoryname","RAILWAY");
                startActivity(i);
                break;
            }

            case R.id.ssc_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "202");
                i.putExtra("categoryname","SSC");
                startActivity(i);
                break;
            }

            case R.id.teaching_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "203");
                i.putExtra("categoryname","TEACHING RELATED");
                startActivity(i);
                break;
            }

            case R.id.upsc_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "204");
                i.putExtra("categoryname","UPSC");
                startActivity(i);
                break;
            }

            case R.id.othergovernmentjob_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "205");
                i.putExtra("categoryname","OTHER BOOKS");
                startActivity(i);
                break;
            }

            case R.id.gmat_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "206");
                i.putExtra("categoryname","GMAT");
                startActivity(i);
                break;
            }

            case R.id.gre_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "207");
                i.putExtra("categoryname","RAILWAY");
                startActivity(i);
                break;
            }

            case R.id.sat_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "209");
                i.putExtra("categoryname","SAT");
                startActivity(i);
                break;
            }

            case R.id.otherinternationalexam_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "210");
                i.putExtra("categoryname","OTHER BOOKS");
                startActivity(i);
                break;
            }

            case R.id.ntse_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "212");
                i.putExtra("categoryname","NTSE");
                startActivity(i);
                break;
            }

            case R.id.olympaid_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "213");
                i.putExtra("categoryname","OLYMPAID");
                startActivity(i);
                break;
            }

            case R.id.schoollevelparts_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "211");
                i.putExtra("categoryname","NAVODAYA");
                startActivity(i);
                break;
            }

            case R.id.sainikschool_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "214");
                i.putExtra("categoryname","SAINIK");
                startActivity(i);
                break;
            }

            case R.id.otherschoollevel_image: {
                Intent i = new Intent(Competitivebooks.this, ProductView.class);
                i.putExtra("category", url + "215");
                i.putExtra("categoryname","OTHER BOOKS");
                startActivity(i);
                break;
            }

        }
    }
}
