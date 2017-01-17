package com.berishaerblin.moneymanager.Category.Kursimet;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Savings;
import com.berishaerblin.moneymanager.dataBase.model.SavingsItem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addItemintokursimet extends AppCompatActivity {

    DataBaseSource dataBaseSource;
    Toolbar toolbar;
    TextView title,date;
    TextView tkursyne,krejt,euroja;
    EditText value;
    TextInputLayout layout_vlera;
    FloatingActionButton save;
    double shuma;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_itemintokursimet);
        Intent i = this.getIntent();
        id = Integer.parseInt(i.getStringExtra("KursimetItemID"));

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.addKursime);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseSource = new DataBaseSource(getApplicationContext());
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        title = (TextView)findViewById(R.id.kursime_title);
        date = (TextView)findViewById(R.id.kursime_date);
        tkursyne = (TextView)findViewById(R.id.tkursyne);
        krejt = (TextView)findViewById(R.id.gjithsej);
        euroja = (TextView)findViewById(R.id.euroja);

        value = (EditText)findViewById(R.id.vlera);
        layout_vlera = (TextInputLayout) findViewById(R.id.layout_vlera);
        save = (FloatingActionButton) findViewById(R.id.ki_add);

        final Savings savings = dataBaseSource.getSavingsById(id);
        try {
            shuma = dataBaseSource.getSumOfSavingsById(savings.getIdSavings());
            if (savings.getSavingsValue() <= shuma) {
                layout_vlera.setVisibility(View.GONE);
                euroja.setVisibility(View.GONE);
                save.setVisibility(View.GONE);
            }
        } catch (Exception e){
            shuma = 0.0;
            e.printStackTrace();
        }
        title.setText(savings.getSavingsTitle());
        date.setText(savings.getSavingsDate());
        krejt.setText(String.valueOf(savings.getSavingsValue())+" €");
        tkursyne.setText(String.valueOf(shuma) + " €");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!value.getText().toString().isEmpty()){
                    double vlera = Double.valueOf(value.getText().toString());

                    if(vlera <= (savings.getSavingsValue()-shuma)) {
                        SavingsItem s = new SavingsItem(vlera, id, dateFormat.format(new Date()));
                        dataBaseSource.insertIntoSavings(s);
                        finish();
                    } else {
                        layout_vlera.setError("Vlera juaj tejkalon kursimin!");
                    }
                } else {
                    layout_vlera.setError("Cakto vlerën për kursim!");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kursimetmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.fshije:
                dataBaseSource.deleteSavings(id);
                dataBaseSource.deleteItemSavings(id);
                finish();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
