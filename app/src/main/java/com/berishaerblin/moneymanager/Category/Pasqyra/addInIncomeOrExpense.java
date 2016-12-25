package com.berishaerblin.moneymanager.Category.Pasqyra;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.model.Category;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.server.converter.StringToIntConverter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by mKrasniqi on 12/3/16.
 * Edited by berishaerblin on 12/20/16.
 */

public class addInIncomeOrExpense extends AppCompatActivity {

    Toolbar toolbar;
    RadioGroup radioGroup;
    EditText editText;
    TextInputLayout textInputLayout;
    ArrayList<Category> arrayListCategories;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_in_income_or_expense);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.addInIncomeOrExpense);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayListCategories = new ArrayList<Category>();
        floatingActionButton = (FloatingActionButton) findViewById(R.id.p_add);
        editText = (EditText) findViewById(R.id.input_value);
        textInputLayout = (TextInputLayout) findViewById(R.id.input_layout_value);

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

        expenseColor.setBackgroundColor(getResources().getColor(R.color.expenseColor));

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseColor.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                incomeColor.setBackgroundColor(getResources().getColor(R.color.incomeColor));
                isIncome = true;
                arrayListCategories.clear();
//                arrayListCategories.addAll();
            }
        });

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isIncome = false;
                incomeColor.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                expenseColor.setBackgroundColor(getResources().getColor(R.color.expenseColor));
                arrayListCategories.clear();
//                arrayListCategories.addAll();

            }
        });


        // Te dhena Testuese >>>>
        Category c1 = new Category(1, "Test 1", "Test1", R.drawable.ic_home);
        Category c2 = new Category(2, "Test 2", "Test2", R.drawable.ic_cilesimet);
        Category c3 = new Category(3, "Test 3", "Test3", R.drawable.ic_ftoni_miqte);
        Category c4 = new Category(4, "Test 4", "Test4", R.drawable.ic_histori);
        Category c5 = new Category(5, "Test 5", "Test5", R.drawable.ic_kategori);
        Category c6 = new Category(6, "Test 6", "Test6", R.drawable.ic_huazimet);
        Category c7 = new Category(7, "Test 7", "Test6", R.drawable.ic_huazimet);
        Category c8 = new Category(8, "Test 8", "Test6", R.drawable.ic_huazimet);
        Category c9 = new Category(9, "Test 9", "Test6", R.drawable.ic_huazimet);

        arrayListCategories.add(c1);
        arrayListCategories.add(c2);
        arrayListCategories.add(c3);
        arrayListCategories.add(c4);
        arrayListCategories.add(c5);
        arrayListCategories.add(c6);
        arrayListCategories.add(c7);
        arrayListCategories.add(c8);
        arrayListCategories.add(c9);
        //


//            arrayListCategories.addAll();
//      Masi te krijohet e gjitha databasa do te plotesohet funksioni me larte duke i marruar kategorit prej DB-s


        radioGroup = (RadioGroup) findViewById(R.id.p_radiogroup);
        createRadioButton(radioGroup);

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

    public void createRadioButton(RadioGroup rgp) {
        for (int i = 0; i <= arrayListCategories.size() - 1; i++) {

            Drawable img = getResources().getDrawable(arrayListCategories.get(i).getCategoryImage());
            String title = arrayListCategories.get(i).getCategoryName();
            int id = arrayListCategories.get(i).getIdCategory();

            RadioButton rbn = new RadioButton(getApplicationContext());
            rbn.setId(id);
            rbn.setText(title);
            rbn.setPadding(75, 75, 75, 75);
            rbn.setButtonDrawable(null);
            rbn.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
            rbn.setBackground(getResources().getDrawable(R.drawable.radiobutton_background));

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            rbn.setLayoutParams(params);
            rgp.addView(rbn);
        }
    }

}
