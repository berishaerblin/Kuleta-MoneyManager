package com.berishaerblin.moneymanager.Category.Pasqyra;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Category;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by mKrasniqi on 12/3/16.
 * Edited by berishaerblin on 12/20/16.
 */

public class addInIncomeOrExpense extends AppCompatActivity {

    Toolbar toolbar;
    ListView categoriesList;
    EditText editText;
    TextInputLayout textInputLayout;
    FloatingActionButton floatingActionButton;

    LinearLayout expense, income;
    ImageView expenseColor, incomeColor;
    TextView dateTextView;
    boolean isIncome = false;
    int myYear, myMonth, myDay;
    static final int dialogID = 0;
    Calendar calendar = Calendar.getInstance();
    private String currentDateString;
    private static final String[] ALBMONTHS = {"Jan", "Shk", "Mar", "Pri", "Maj", "Qer", "Korr", "Gush", "Shta", "Tet", "Nen", "Dhj"};
    //public static final String[] ALBDAYS = {"", "Die", "Hen", "Mar", "Mer", "Enj", "Pre", "Sht"};
    private String myAlbDay;
    private String myAlbMonth;
    String dateTexttoSet;

    DataBaseSource dataBaseSource;
    CustomAdapterCategory customAdapterCategory;
    int categorySelected;
    List<Category> arrayListCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_in_income_or_expense);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.addInIncomeOrExpense);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.p_add);
        editText = (EditText) findViewById(R.id.input_value);
        textInputLayout = (TextInputLayout) findViewById(R.id.input_layout_value);
        dataBaseSource = new DataBaseSource(getApplicationContext());
        categoriesList = (ListView) findViewById(R.id.categoriesList);

        arrayListCategories = new ArrayList<Category>();
        customAdapterCategory = new CustomAdapterCategory(getApplicationContext(),arrayListCategories);
        categoriesList.setAdapter(customAdapterCategory);

        expense = (LinearLayout) findViewById(R.id.expenseID);
        income = (LinearLayout) findViewById(R.id.incomeID);
        expenseColor = (ImageView) findViewById(R.id.colorOfExpense);
        incomeColor = (ImageView) findViewById(R.id.colorOfIncome);
        dateTextView = (TextView) findViewById(R.id.dateTextViewID);

        myYear = calendar.get(Calendar.YEAR);
        myMonth = calendar.get(Calendar.MONTH);
        myDay = calendar.get(Calendar.DAY_OF_MONTH);
        showCalendarDialogOnTextViewClicked();

        currentDateString = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
        dateTexttoSet = dateConverter(currentDateString)+", " + myDay +" "+ ALBMONTHS[myMonth] + ", " + myYear;

        dateTextView.setText(dateTexttoSet);
//        calendar.setTimeInMillis(System.currentTimeMillis());

//                + ", "
//                + calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
//                + " "
//                + calendar.get(Calendar.DATE)
//                + ", "
//                + calendar.get(Calendar.YEAR);
//
//        dateTextView.setText(dateString);

        arrayListCategories.addAll(dataBaseSource.getCategoriesByType("EXPENSE"));
        expenseColor.setBackgroundColor(getResources().getColor(R.color.expenseColor));


        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseColor.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                incomeColor.setBackgroundColor(getResources().getColor(R.color.incomeColor));
                isIncome = true;
                arrayListCategories.clear();
                arrayListCategories.addAll(dataBaseSource.getCategoriesByType("INCOME"));
                customAdapterCategory.notifyDataSetChanged();
            }
        });

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isIncome = false;
                incomeColor.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                expenseColor.setBackgroundColor(getResources().getColor(R.color.expenseColor));
                arrayListCategories.clear();
                arrayListCategories.addAll(dataBaseSource.getCategoriesByType("EXPENSE"));
                customAdapterCategory.notifyDataSetChanged();

            }
        });

        categoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               categorySelected = arrayListCategories.get(i).getIdCategory();
                Toast.makeText(addInIncomeOrExpense.this, "Itemi i selektum >>" + arrayListCategories.get(i).getCategoryName(), Toast.LENGTH_SHORT).show();
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isIncome) {
                    if (!editText.getText().toString().isEmpty()) {
                        //Shtimi i te dhenave ne Databaze
                        //db.insertInExpense();
                    } else {
                        textInputLayout.setError(getString(R.string.empty));
                    }
                } else {
                    if (!editText.getText().toString().isEmpty()) {
                        //Shtimi i te dhenave ne Databaze
                        //db.insertInIncome();
                    } else {
                        textInputLayout.setError(getString(R.string.empty));
                    }
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
        if (id == dialogID) {
            return new DatePickerDialog(this, datePicker, myYear, myMonth, myDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;

            Date daySelected = new Date(myYear, myMonth, myDay - 1);
            SimpleDateFormat myFormat = new SimpleDateFormat("EEE");
            myAlbDay = myFormat.format(daySelected);
            myAlbMonth = ALBMONTHS[myMonth];

            dateTexttoSet = dateConverter(myAlbDay)+", " + myDay +" "+ myAlbMonth + ", " + myYear;
            dateTextView.setText(dateTexttoSet);
        }
    };

    public String dateConverter(String theKey){
        Map<String,String> valuesNeded = new HashMap<String,String>()
        {{  put("Mon", "Hen");
            put("Tue", "Mar");
            put("Wed", "Mer");
            put("Thu", "Enj");
            put("Fri", "Pre");
            put("Sat", "Sht");
            put("Sun", "Die");
        }};

        return (String) valuesNeded.get(theKey);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
