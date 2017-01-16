package com.berishaerblin.moneymanager.Category.Pasqyra;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Balance;
import com.berishaerblin.moneymanager.dataBase.model.Category;
import com.berishaerblin.moneymanager.dataBase.model.Expense;
import com.berishaerblin.moneymanager.dataBase.model.Income;

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
    Date daySelected;
    boolean isDaySelected = false;
    DataBaseSource dataBaseSource;
    CustomAdapterCategory customAdapterCategory;
    int categorySelected;
    double value;
    List<Category> arrayListCategories;
    Balance balance;
    SimpleDateFormat sf;

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

        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        sf = new SimpleDateFormat("MM");
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
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isIncome) {
                    if (!editText.getText().toString().isEmpty()) {
                        value = Double.parseDouble(editText.getText().toString());
                        if(value < dataBaseSource.getBalance().getTotalBalance()) {
                            Date d;
                            if (isDaySelected) {
                                d = new Date(myYear - 1900, myMonth, myDay);
                            } else {
                                d = new Date();
                            }

                            Expense expense = new Expense(value, dateFormat.format(d), categorySelected);
                            dataBaseSource.insertIntoExpense(expense);
                            int idE = dataBaseSource.getAllExpenses().size();
                            dataBaseSource.insertIntoIncomeOrExpense(-1, idE, Integer.parseInt(sf.format(d)));
                            dataBaseSource.removeValueFromBalance(value);
                            finish();
                        } else {
                            Toast.makeText(addInIncomeOrExpense.this, "Nuk keni para të mjaftueshme!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        textInputLayout.setError(getString(R.string.empty));
                    }
                } else {
                    if (!editText.getText().toString().isEmpty()) {
                        Date d;
                        if(isDaySelected){
                            d = new Date(myYear-1900,myMonth,myDay);
                        } else {
                            d = new Date();
                        }
                        value = Double.parseDouble(editText.getText().toString());

                        Income income = new Income(value,dateFormat.format(d),categorySelected);
                        dataBaseSource.insertIntoIncome(income);
                        int id = dataBaseSource.getAllIncome().size();
                        dataBaseSource.insertIntoIncomeOrExpense(id,-1,Integer.parseInt(sf.format(d)));
                        dataBaseSource.addValueInBalance(value);
                        finish();
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

            Log.d("Viti/DataPickerDialog", myYear+" ");
            Log.d("Muji/DataPickerDialog", myMonth+" ");
            Log.d("Data/DataPickerDialog", myDay+" ");

            daySelected = new Date(myYear-1900, myMonth, myDay);

            Log.d("DaySelected:", daySelected+"");

            SimpleDateFormat myFormat = new SimpleDateFormat("EEE");
            myAlbDay = myFormat.format(daySelected);
            myAlbMonth = ALBMONTHS[myMonth];
            isDaySelected = true;
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
