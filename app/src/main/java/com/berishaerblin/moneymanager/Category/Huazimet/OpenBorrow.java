package com.berishaerblin.moneymanager.Category.Huazimet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Borrowing;
import com.berishaerblin.moneymanager.dataBase.model.Savings;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by berishaerblin on 1/17/17.
 */
public class OpenBorrow extends AppCompatActivity {

    Toolbar toolbar;
    DataBaseSource dataBaseSource;
    TextView title,date,value,interes;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openborrow);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.openborrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = (TextView) findViewById(R.id.titleofborrowing);
        date = (TextView) findViewById(R.id.dateofborrowing);
        value = (TextView) findViewById(R.id.valueofborrowing);
        interes = (TextView) findViewById(R.id.interesofborrowing);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.ho_add);

        dataBaseSource = new DataBaseSource(getApplicationContext());

        Intent i = getIntent();
        final int id = Integer.parseInt(i.getStringExtra("HuazimetItemID"));

        final Borrowing borrowing = dataBaseSource.getBorrowingById(id);
        SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sp1 = new SimpleDateFormat("MM");

        int muajiaktual = Integer.parseInt(sp1.format(new Date()));
        int muajiborgjit = Integer.parseInt(borrowing.getBorrowingDate().substring(3,5));
        double vlera = 0.0;

        Log.d("MuahiAktual:", muajiaktual+"");
        Log.d("MuajiBotgjit:", muajiborgjit+"");

        if(muajiaktual > muajiborgjit){
            int mm = muajiaktual-muajiborgjit;
            vlera = borrowing.getBorrowingValue() + (mm * (borrowing.getBorrowingValue() * (borrowing.getBorrowingInteres())) / 100);
        } else {
            vlera = borrowing.getBorrowingValue();
        }

        title.setText(borrowing.getBorrowingTitle());
        date.setText(borrowing.getBorrowingDate());
        value.setText(vlera + " €");
        interes.setText(String.valueOf(borrowing.getBorrowingInteres()) + " %");

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseSource.deleteBorrowing(id);
                Toast.makeText(OpenBorrow.this, "U fshie me sukses!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
