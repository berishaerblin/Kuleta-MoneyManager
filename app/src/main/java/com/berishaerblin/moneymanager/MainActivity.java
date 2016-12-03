package com.berishaerblin.moneymanager;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.berishaerblin.moneymanager.Category.Cilesimet;
import com.berishaerblin.moneymanager.Category.Histori;
import com.berishaerblin.moneymanager.Category.Huazimet;
import com.berishaerblin.moneymanager.Category.Kategoria;
import com.berishaerblin.moneymanager.Category.Kursimet;
import com.berishaerblin.moneymanager.Category.Pasqyra.Pasqyra;
import com.berishaerblin.moneymanager.Category.RrethNesh;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DataBaseSource ourDatabase;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    NavigationView navigationView;
    View navigationHeaderView;
    FragmentTransaction fragmentTransaction;

    TextView nameSurname;
    TextView totalsum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ourDatabase = new DataBaseSource(this);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationHeaderView = navigationView.inflateHeaderView(R.layout.navigation_drawer_header);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new Pasqyra());
        fragmentTransaction.commit();
        navigationView.setCheckedItem(R.id.pasqyra);
        getSupportActionBar().setTitle(R.string.app_name);


        nameSurname = (TextView)navigationHeaderView.findViewById(R.id.namesurname);
        totalsum = (TextView)navigationHeaderView.findViewById(R.id.totalS);

        nameSurname.setText("Filan Fisteku");
        totalsum.setText("€500.0");

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.pasqyra:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new Pasqyra());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.pasqyra);
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.kategoria:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new Kategoria());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.kategoria);
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.kursimet:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new Kursimet());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.kursimet);
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.huazimet:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new Huazimet());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.huazimet);
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.histori:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new Histori());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.histori);
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.cilesimet:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new Cilesimet());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.cilesimet);
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.ftoni_miqte:
                //Do te krijohet nje AlertDialog per shperndarje te aplikacionit.
                Toast.makeText(MainActivity.this, "Se Shpejti", Toast.LENGTH_SHORT).show();
                item.setCheckable(false);
                break;

            case R.id.rrethnesh:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new RrethNesh());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.rrethnesh);
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

}