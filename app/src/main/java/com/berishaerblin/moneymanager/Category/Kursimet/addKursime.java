package com.berishaerblin.moneymanager.Category.Kursimet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Savings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class addKursime extends AppCompatActivity {
    Toolbar toolbar;
    EditText title,value;
    TextInputLayout titleLayout,valueLayout;
    FloatingActionButton saveButton;

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
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kursime);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.addKursime);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseSource = new DataBaseSource(getApplicationContext());
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        dateTextView = (TextView)findViewById(R.id.KdateTextViewID);
        title = (EditText) findViewById(R.id.input_title);
        value = (EditText) findViewById(R.id.input_value);
        titleLayout = (TextInputLayout) findViewById(R.id.input_layout_title);
        valueLayout = (TextInputLayout) findViewById(R.id.input_layout_value);
        saveButton = (FloatingActionButton) findViewById(R.id.k_save);

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
                if(!title.getText().toString().isEmpty()){
                    if(!value.getText().toString().isEmpty()){
                        Savings savings = new Savings();
                        savings.setSavingsTitle(title.getText().toString());
                        savings.setSavingsValue(Double.parseDouble(value.getText().toString()));
                        savings.setSavingsDate(dateFormat.format(new Date(myYear - 1900, myMonth, myDay)));
                        dataBaseSource.createSavings(savings);
                        finish();
                    } else {
                        valueLayout.setError("Cakto vlerën për kursim!");
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
