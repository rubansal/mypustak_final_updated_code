package com.mypushtak.app.Activity;

import android.content.Intent;
import android.media.Image;
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

public class Fictionbooks extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private ImageView home_icon, school_image, university_image, competitive_image;

    private ImageView fiction1_image, nonfiction_image, religion_image;

    private LinearLayout fiction_layout, nonfiction_layout, religion_layout;
    private ImageView comic, drama, horror, humour, literary, mystery, romance, sciencefiction, shortstories, teens, motivation;
    private ImageView astrology, business, health, history, sports, nonfictionothers;
    private ImageView holy, philosphy, religion, spiritual, religionothers, others;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fictionbooks);

        initialisation();

        fiction1_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                religion_layout.setVisibility(View.GONE);
                nonfiction_layout.setVisibility(View.GONE);
                fiction_layout.setVisibility(View.VISIBLE);
            }
        });

        nonfiction_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                religion_layout.setVisibility(View.GONE);
                fiction_layout.setVisibility(View.GONE);
                nonfiction_layout.setVisibility(View.VISIBLE);
            }
        });

        religion_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nonfiction_layout.setVisibility(View.GONE);
                fiction_layout.setVisibility(View.GONE);
                religion_layout.setVisibility(View.VISIBLE);
            }
        });

        school_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Fictionbooks.this, Schoolbooks.class);
                startActivity(i);
                finish();
            }
        });

        university_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Fictionbooks.this, Universitybooks.class);
                startActivity(i);
                finish();
            }
        });


        competitive_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Fictionbooks.this, Competitivebooks.class);
                startActivity(i);
                finish();
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(Fictionbooks.this, DividerItemDecoration.VERTICAL));

        navigationView.getMenu().getItem(1).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(2).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(4).setActionView(R.layout.menu_image);

        navigationView.setNavigationItemSelectedListener(this);

        home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawerLayout.isDrawerOpen(GravityCompat.START))
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
            Intent i1 = new Intent(this, ProfileActivity.class);
            startActivity(i1);
            return true;
        } else if (id == R.id.nav_school_bus) {
            Intent i1 = new Intent(this, ProductView.class);
            startActivity(i1);
//            Intent cartIntent = new Intent(Home.this, Cart.class);
//            startActivity(cartIntent);

        } else if (id == R.id.nav_univ_books) {
            Intent i1 = new Intent(this, ProfileDetailsActivity.class);
            startActivity(i1);
//            Intent orderIntent = new Intent(Home.this, OrderStatus.class);
//            startActivity(orderIntent);

        } else if (id == R.id.nav_compet_exams) {
            Intent i1 = new Intent(this, productfullview.class);
            startActivity(i1);
//            AccountKit.logOut();
//            Intent signIn = new Intent(Home.this, ScreenOneActivity.class);
//            signIn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(signIn);
        } else if (id == R.id.nav_fiction_books) {
//            showChangePasswordDialog();

        } else if (id == R.id.nav_faqs) {
//            startActivity(new Intent(Home.this,NearbyStore.class));
        } else if (id == R.id.nav_con_us) {
//            showHomeAddressDialog();

        } else if (id == R.id.nav_rate_us) {
//            showHomeSettingDialog();
        } else if (id == R.id.nav_abt_us) {
//            startActivity(new Intent(Home.this,FavoritesActivity.class));
        } else if (id == R.id.nav_term_of_use) {
//            startActivity(new Intent(Home.this,FavoritesActivity.class));
        } else if (id == R.id.nav_prd_donors) {
//            startActivity(new Intent(Home.this,FavoritesActivity.class));
        }
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initialisation() {
        home_icon = findViewById(R.id.home_icon);

        school_image = findViewById(R.id.school_image);
        university_image = findViewById(R.id.university_image);
        competitive_image = findViewById(R.id.competitive_image);

        fiction1_image = findViewById(R.id.fiction1_image);
        nonfiction_image = findViewById(R.id.nonfiction_image);
        religion_image = findViewById(R.id.religion_image);

        fiction_layout = findViewById(R.id.fiction_layout);
        nonfiction_layout = findViewById(R.id.nonfiction_layout);
        religion_layout = findViewById(R.id.religion_layout);

        comic = findViewById(R.id.comic_image);
        drama = findViewById(R.id.drama_image);
        horror = findViewById(R.id.horror_image);
        humour = findViewById(R.id.humor_image);
        literary = findViewById(R.id.lite_image);
        mystery = findViewById(R.id.mystry_image);
        romance = findViewById(R.id.romance_image);
        sciencefiction = findViewById(R.id.sifi_image);
        shortstories = findViewById(R.id.shortstories_image);
        teens = findViewById(R.id.teen_image);
        motivation = findViewById(R.id.class_image);
        astrology = findViewById(R.id.astrology_image);
        business = findViewById(R.id.business_image);
        health = findViewById(R.id.health_image);
        history = findViewById(R.id.historypolitics_image);
        sports = findViewById(R.id.sports_image);
        nonfictionothers = findViewById(R.id.othernonfiction_image);
        holy = findViewById(R.id.holybooks_image);
        philosphy = findViewById(R.id.philoshopy_image);
        religion = findViewById(R.id.religionpart_image);
        spiritual = findViewById(R.id.spiri_image);
        religionothers = findViewById(R.id.otherreligion_image);
        others = findViewById(R.id.others_image);



        horror.setOnClickListener(this);
        comic.setOnClickListener(this);
        drama.setOnClickListener(this);
        humour.setOnClickListener(this);
        literary.setOnClickListener(this);
        mystery.setOnClickListener(this);
        romance.setOnClickListener(this);
        sciencefiction.setOnClickListener(this);
        shortstories.setOnClickListener(this);
        teens.setOnClickListener(this);
        motivation.setOnClickListener(this);
        astrology.setOnClickListener(this);
        business.setOnClickListener(this);
        health.setOnClickListener(this);
        history.setOnClickListener(this);
        sports.setOnClickListener(this);
        nonfictionothers.setOnClickListener(this);
        holy.setOnClickListener(this);
        philosphy.setOnClickListener(this);
        religion.setOnClickListener(this);
        spiritual.setOnClickListener(this);
        religionothers.setOnClickListener(this);
        others.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String url =  ConstantUrl.URL+"preview/";
        int id = view.getId();
        Log.d("unique", "" + id);

        switch (id) {

            case R.id.comic_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "218");
                i.putExtra("categoryname","COMICS");
                startActivity(i);
                break;
            }
            case R.id.drama_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "219");
                i.putExtra("categoryname","DRAMA");
                startActivity(i);
                break;
            }
            case R.id.horror_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "220");
                i.putExtra("categoryname","HORROR");
                startActivity(i);
                break;
            }
            case R.id.humor_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "221");
                i.putExtra("categoryname","HUMOUR");
                startActivity(i);
                break;
            }
            case R.id.lite_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "222");
                i.putExtra("categoryname","LITERARY");
                startActivity(i);
                break;
            }
            case R.id.mystry_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "223");
                i.putExtra("categoryname","MYSTERY");
                startActivity(i);
                break;
            }
            case R.id.romance_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "224");
                i.putExtra("categoryname","ROMANCE");
                startActivity(i);
                break;
            }
            case R.id.sifi_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "225");
                i.putExtra("categoryname","SCIENCE FICTION");
                startActivity(i);
                break;
            }
            case R.id.shortstories_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "226");
                i.putExtra("categoryname","SHORT STORIES");
                startActivity(i);
                break;
            }
            case R.id.teen_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "227");
                i.putExtra("categoryname","TEENS");
                startActivity(i);
                break;
            }
            case R.id.class_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "350");
                i.putExtra("categoryname","MOTIVATION");
                startActivity(i);
                break;
            }
            case R.id.astrology_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "245");
                i.putExtra("categoryname","ASTROLOGY");
                startActivity(i);
                break;
            }
            case R.id.business_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "247");
                i.putExtra("categoryname","BUSINESS");
                startActivity(i);
                break;
            }
            case R.id.health_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "248");
                i.putExtra("categoryname","HEALTH");
                startActivity(i);
                break;
            }
            case R.id.historypolitics_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "249");
                i.putExtra("categoryname","HISTORY");
                startActivity(i);
                break;
            }
            case R.id.sports_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "250");
                i.putExtra("categoryname","SPORTS");
                startActivity(i);
                break;
            }
            case R.id.othernonfiction_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "251");
                i.putExtra("categoryname","OTHER BOOKS");
                startActivity(i);
                break;
            }
            case R.id.holybooks_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "239");
                i.putExtra("categoryname","HOLY BOOKS");
                startActivity(i);
                break;
            }
            case R.id.philoshopy_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "240");
                i.putExtra("categoryname","PHILOSOPHY");
                startActivity(i);
                break;
            }
            case R.id.religionpart_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "241");
                i.putExtra("categoryname","RELIGION");
                startActivity(i);
                break;
            }
            case R.id.spiri_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "242");
                i.putExtra("categoryname","SPIRITUAL");
                startActivity(i);
                break;
            }
            case R.id.otherreligion_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "243");
                i.putExtra("categoryname","OTHER BOOKS");
                startActivity(i);
                break;
            }
            case R.id.others_image: {
                Intent i = new Intent(Fictionbooks.this, ProductView.class);
                i.putExtra("category", url + "244");
                i.putExtra("categoryname","OTHER BOOKS");
                startActivity(i);
                break;
            }


        }
    }
}
