package com.berishaerblin.moneymanager.Category.Huazimet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Borrowing;
import com.berishaerblin.moneymanager.dataBase.model.Savings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by berishaerblin on 1/17/17.
 */

public class addHuazimet extends AppCompatActivity {
    Toolbar toolbar;
    EditText titleHuzaimi,valueHuazimi,interesValue;
    TextInputLayout titleLayout,valueLayout,intersValueLayout;
    FloatingActionButton saveButton;
    CheckBox checkBoxInteres;

    DataBaseSource dataBaseSource;

    //Selektimi i dates
    int myYear, myMonth, myDay;
    static final int dialogID = 0;
    Calendar calendar = Calendar.getInstance();
    private String currentDateString;
    private static final String[] ALBMONTHS = {"Jan", "Shk", "Mar", "Pri", "Maj", "Qer", "Korr", "Gush", "Shta", "Tet", "Nen", "Dhj"};
    private String myAlbDay;
    private String myAlbMonth;
    String dateTexttoSet;
    Date daySelected;
    TextView dateTextView;
    double interesi = 0.0;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_huazimet);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.addHuazim);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseSource = new DataBaseSource(getApplicationContext());
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        dateTextView = (TextView)findViewById(R.id.HdateTextViewID);
        titleHuzaimi = (EditText) findViewById(R.id.input_title);
        valueHuazimi = (EditText) findViewById(R.id.input_value);
        interesValue = (EditText) findViewById(R.id.interesValue);
        titleLayout = (TextInputLayout) findViewById(R.id.input_layout_title);
        valueLayout = (TextInputLayout) findViewById(R.id.input_layout_value);
        saveButton = (FloatingActionButton) findViewById(R.id.saveHuazim);
        checkBoxInteres = (CheckBox)findViewById(R.id.checkBoxInteres);
        intersValueLayout = (TextInputLayout) findViewById(R.id.intersValueLayout);


        checkBoxInteres.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    intersValueLayout.setVisibility(View.VISIBLE);

                } else {
                    intersValueLayout.setVisibility(View.INVISIBLE);
                    interesi = 0.0;
                }
            }
        });


        myYear = calendar.get(Calendar.YEAR);
        myMonth = calendar.get(Calendar.MONTH);
        myDay = calendar.get(Calendar.DAY_OF_MONTH);
        showCalendarDialogOnTextViewClicked();
        currentDateString = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
        dateTexttoSet = dateConverter(currentDateString)+", " + myDay +" "+ ALBMONTHS[myMonth] + ", " + myYear;
        dateTextView.setText(dateTexttoSet);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!interesValue.getText().toString().isEmpty()){
                    interesi = Double.valueOf(interesValue.getText().toString());
                } else{
                    intersValueLayout.setError("Cakto vleren e interesit!");
                }
                if(!titleHuzaimi.getText().toString().isEmpty()){
                    if(!valueHuazimi.getText().toString().isEmpty()){
                        Borrowing borrow = new Borrowing();
                        borrow.setBorrowingTitle(titleHuzaimi.getText().toString());
                        borrow.setBorrowingValue(Double.parseDouble(valueHuazimi.getText().toString()));
                        borrow.setBorrowingDate(dateFormat.format(new Date(myYear - 1900, myMonth, myDay)));
                        borrow.setBorrowingInteres(interesi);
                        dataBaseSource.createBorrwings(borrow);
                        finish();
                    } else {
                        valueLayout.setError("Cakto vlerën e shumës se huazuar!");
                    }
                } else {
                    titleLayout.setError("Vendose një titull!");
                }
            }
        });
    }

    public void showCalendarDialogOnTextViewClicked() {
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(dialogID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cal.set(Calendar.DAY_OF_YEAR, 1);
        Date start = cal.getTime();

        if (id == dialogID) {
            DatePickerDialog d = new DatePickerDialog(this, datePicker, myYear, myMonth, myDay);
            d.getDatePicker().setMinDate(start.getTime());
            return d;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            daySelected = new Date(myYear-1900, myMonth, myDay);
            SimpleDateFormat myFormat = new SimpleDateFormat("EEE");
            myAlbDay = myFormat.format(daySelected);
            myAlbMonth = ALBMONTHS[myMonth];
            dateTexttoSet = dateConverter(myAlbDay)+", " + myDay +" "+ myAlbMonth + ", " + myYear;
            dateTextView.setText(dateTexttoSet);
        }
    };

    public String dateConverter(String theKey){
        Map<String,String> valuesNeded = new HashMap<String,String>()
        {{  put("Mon", "E Hënë");
            put("Tue", "E Martë");
            put("Wed", "E Mërkurë");
            put("Thu", "E Enjte");
            put("Fri", "E Premte");
            put("Sat", "E Shtunë");
            put("Sun", "E Diel");
        }};

        return (String) valuesNeded.get(theKey);
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
