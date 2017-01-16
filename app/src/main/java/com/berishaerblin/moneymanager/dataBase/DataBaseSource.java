package com.berishaerblin.moneymanager.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.model.Balance;
import com.berishaerblin.moneymanager.dataBase.model.Category;
import com.berishaerblin.moneymanager.dataBase.model.Expense;
import com.berishaerblin.moneymanager.dataBase.model.Income;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by berishaerblin on 11/27/16.
 * Edited by mKrasniqi & berishaerblin on 12/27/16.
 */

public class DataBaseSource extends SQLiteOpenHelper {

    //Database
    public static final String moneyManagerDataBase = "moneyManagerDataBase.db";


    //Balance Table
    public static final String balanceTable = "balanceTable";
    public static final String idBalance = "idBalance";
    public static final String totalBalance = "totalBalance";
    /*public static final String fidIncome = "fidIncome";
    public static final String fidExpence = "fidExpense";*/

    //Category Table
    public static final String categoryTable = "categoryTable";
    public static final String idCategory = "idCategory";
    public static final String categoryName = "categoryName";
    public static final String categoryType = "categoryType";
    public static final String categoryImage = "categoryImage";

    //Income Table
    public static final String incomeTable = "incomeTable";
    public static final String idIncome = "idIncome";
    public static final String incomeValue = "incomeValue";
    public static final String incomeDate = "incomeDate";
    public static final String fICategoryType = "fICategoryType";
    public static final String fIBalance = "fIBalance";
    //public static final String fidBalance = "fidBalance";

    //Expense Table
    public static final String expenseTable = "expenseTable";
    public static final String idExpense = "idExpense";
    public static final String expenseValue = "expenseValue";
    public static final String expenseDate = "expenseDate";
    public static final String fECategoryType = "fidExpenseType";
    public static final String fEBalance = "fEbalance";


    //History Table
    public static final String historyTable = "historyTable";
    public static final String idHistory = "idHIstory";
    public static final String historyTitle = "historyTitle";
    public static final String fIncomeHistory = "fIncomeHistory";
    public static final String fExpenseHistory = "fExpenseHistory";

    //Savings Table
    public static final String savingsTable = "savingsTable";
    public static final String idSavings = "idSavings";
    public static final String savingsTitle = "savingsTitle";
    public static final String savingsValue = "savingsValue";
    public static final String savingsDate = "savingsDate";
    public static final String fSBalance = "fSBalance";

    //Borrowing Table
    public static final String borrowingTable = "borrowingTable";
    public static final String idBorrowing = "idBorrowing";
    public static final String borrowingTitle = "borrowingTitle";
    public static final String borrowingType = "borrowingType";
    public static final String borrowingDate = "borrowingDate";
    public static final String borrowingValue = "borrowingValue";
    public static final String borrowingInteres = "borrowingInteres";
    public static final String fBBalance = "fBBalance";


    public DataBaseSource(Context context) {
        super(context, moneyManagerDataBase, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+balanceTable+"("
                +idBalance+" INTEGER PRIMARY KEY, "
                +totalBalance+" DOUBLE);");

        db.execSQL("CREATE TABLE " + categoryTable + "("
                + idCategory + " INTEGER PRIMARY KEY, "
                + categoryName + " TEXT NOT NULL, "
                + categoryType + " TEXT NOT NULL, "
                + categoryImage + " TEXT NOT NULL );");

        db.execSQL("CREATE TABLE "+incomeTable+"("
                +idIncome+" INTEGER PRIMARY KEY, "
                +incomeValue+" DOUBLE NOT NULL, "
                +incomeDate+" TEXT, "
                +fICategoryType+" INTEGER, "
                +"FOREIGN KEY ("+fICategoryType+") REFERENCES "+categoryTable+" ("+idCategory+") );");

        db.execSQL("CREATE TABLE "+expenseTable+"("
                +idExpense+" INTEGER PRIMARY KEY, "
                +expenseValue+" DOUBLE NOT NULL, "
                +expenseDate+" TEXT, "
                +fECategoryType+" INTEGER, "
                +"FOREIGN KEY ("+fECategoryType+") REFERENCES "+categoryTable+" ("+idCategory+") );");

        db.execSQL("CREATE TABLE "+historyTable+"("
                +idHistory+" INTEGER PRIMARY KEY, "
                +historyTitle+" TEXT NOT NULL, "
                +fIncomeHistory+" INTEGER, "
                +fExpenseHistory+" INTEGER, "
                +"FOREIGN KEY ("+fIncomeHistory+") REFERENCES "+incomeTable+" ("+idIncome+"), "
                +"FOREIGN KEY ("+fExpenseHistory+") REFERENCES "+expenseTable+" ("+idExpense+") );");

        db.execSQL("CREATE TABLE "+savingsTable+"("
                +idSavings+" INTEGER PRIMARY KEY, "
                +savingsTitle+" TEXT, "
                +savingsValue+" DOUBLE NOT NULL, "
                +savingsDate+" TEXT, "
                +fSBalance+" INTEGER);");
//                +"FOREIGN KEY ("+fSBalance+") REFERENCES "+balanceTable+" ("+idBalance+") ON DELETE CASCADE "+"ON UPDATE CASCADE);");

        db.execSQL("CREATE TABLE "+borrowingTable+"("
                +idBorrowing+" INTEGER PRIMARY KEY, "
                +borrowingTitle+" TEXT, "
                +borrowingType+" TEXT, "
                +borrowingDate+" TEXT, "
                +borrowingValue+" DOUBLE, "
                +borrowingInteres+" DOUBLE, "
                +fBBalance+" INTEGER);");
//                +"FOREIGN KEY ("+fBBalance+") REFERENCES "+balanceTable+" ("+idBalance+") ON DELETE CASCADE "+"ON UPDATE CASCADE);");

        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Rrogë','INCOME','R.drawable.salary' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Shpërblim','INCOME','R.drawable.bonus' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Pune e pavarur','INCOME','R.drawable.freelance' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Dhuratë','INCOME','R.drawable.gift' );" );

        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'I përgjithshëm','EXPENSE','R.drawable.general' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Fëmijë','EXPENSE','R.drawable.kids' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Shtëpi','EXPENSE','R.drawable.house' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Veshje','EXPENSE','R.drawable.clothes' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Shitblerje','EXPENSE','R.drawable.shop' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Dhuratë','EXPENSE','R.drawable.gift' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Ushqim','EXPENSE','R.drawable.food' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Pagesa','EXPENSE','R.drawable.payments' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Transport','EXPENSE','R.drawable.transport' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Shërbime Komunale','EXPENSE','R.drawable.utilities' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Personale','EXPENSE','R.drawable.personal' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Veturë','EXPENSE','R.drawable.car' );" );
        db.execSQL("INSERT INTO " + categoryTable + " ("+categoryName+", "+categoryType+", "+categoryImage+") VALUES ( 'Udhëtime','EXPENSE','R.drawable.vacation' );" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+balanceTable);
        db.execSQL("DROP TABLE IF EXISTS "+categoryTable);
        db.execSQL("DROP TABLE IF EXISTS "+incomeTable);
        db.execSQL("DROP TABLE IF EXISTS "+expenseTable);
        db.execSQL("DROP TABLE IF EXISTS "+historyTable);
        db.execSQL("DROP TABLE IF EXISTS "+savingsTable);
        db.execSQL("DROP TABLE IF EXISTS "+borrowingTable);

        onCreate(db);

    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
       if (!db.isReadOnly()) {
            // Enable foreign key constraints
        db.execSQL("PRAGMA foreign_keys=ON;");
       }
    }

    public void insertIntoIncome(Income income){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(incomeValue, income.getIncomeValue());
        contentValues.put(incomeDate, income.getIncomeDate());
        contentValues.put(fICategoryType, income.getfICategoryType());
        db.insert(incomeTable, null, contentValues);
        db.close();
    }

//    public void createBalance(Balance balance){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(totalBalance, balance.getTotalBalance());
//
//        db.insert(balanceTable, null, contentValues);
//        db.close();
//    }

//    public void insertIntoIncome2(Income income){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "INSERT INTO "+incomeTable+" ("+incomeValue+", "+incomeDate+", "+fICategoryType+", "+fIBalance+") " +
//                "VALUES('"+income.getIncomeValue()+"', '"+income.getIncomeDate()
//                +"', '"+income.getfICategoryType()+"', '"+income.getfIBalance()+"' );";
//        db.execSQL(query);
//    }
//
//    public void insertIntoExpense2(Expense expense){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "INSERT INTO "+expenseTable+" ("+expenseValue+", "+expenseDate+", "+fECategoryType+", "+fEBalance+") " +
//                "VALUES('"+expense.getExpenseValue()+"', '"+expense.getExpenseDate()
//                +"', '"+expense.getfECategoryType()+"', '"+expense.getfEBalance()+"' );";
//        db.execSQL(query);
//    }

    public void insertIntoExpense(Expense expense){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(expenseValue, expense.getExpenseValue());
        contentValues.put(expenseDate, expense.getExpenseDate());
        contentValues.put(fECategoryType, expense.getfECategoryType());

        Log.d("Databaza-Expense: ", expense.toString());
        db.insert(expenseTable, null, contentValues);

        db.close();
    }


    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT  * FROM " + categoryTable;

        Log.d("- DB - getAllCategories", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Category ctg = new Category();
                ctg.setIdCategory(c.getColumnIndex(idCategory));
                ctg.setCategoryName(c.getString(c.getColumnIndex(categoryName)));
                ctg.setCategoryType(c.getString(c.getColumnIndex(categoryType)));
                ctg.setCategoryImage(c.getString(c.getColumnIndex(categoryImage)));

                categories.add(ctg);
            } while (c.moveToNext());
        }
        return categories;
    }

    public List<Object> getAllIncomeAndExpenseOfMonth(String strDt) {
        List<Object> liste = new ArrayList<Object>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM "+incomeTable+" INNER JOIN "+expenseTable+" ON "+incomeTable+"."+incomeDate+"="+"'"+strDt+"'"+" AND "+expenseTable+"."+expenseDate+"="+"'"+strDt+"'";

        Log.d("getAllOfMonth > ", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Log.d("getColumnName(0)>>",c.getColumnName(0));

                if (c.getColumnName(0).equals(c.getString(0))) {
                    Expense e = new Expense();
                    e.setIdExpense(Integer.parseInt(c.getString(0)));
                    e.setExpenseValue(Double.parseDouble(c.getString(1)));
                    e.setExpenseDate(c.getString(2));
                    e.setfECategoryType(Integer.parseInt(c.getString(3)));
                    Log.d("getExpense: ", e.toString());
                    liste.add(e);
                } else {
                    Income i = new Income();
                    i.setIdIncome(Integer.parseInt(c.getString(0)));
                    i.setIncomeValue(Double.parseDouble(c.getString(1)));
                    i.setIncomeDate(c.getString(2));
                    i.setfICategoryType(Integer.parseInt(c.getString(3)));
                    Log.d("getIncome: ", i.toString());
                    liste.add(i);
                }
            } while (c.moveToNext());
        }
        return liste;
    }

    public List<Expense> getAllExpenses(){
        List<Expense> liste = new ArrayList<Expense>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM "+expenseTable;

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                    Expense e = new Expense();
                    e.setIdExpense(Integer.parseInt(c.getString(0)));
                    e.setExpenseValue(Double.parseDouble(c.getString(1)));
                    e.setExpenseDate(c.getString(2));
                    e.setfECategoryType(Integer.parseInt(c.getString(3)));
                    liste.add(e);
                    Log.d("getAllExpenses: ", e.toString());
            } while (c.moveToNext());
        }
        return liste;
    }

    public List<Category> getCategoriesByType(String category_type) {

        List<Category> categories = new ArrayList<Category>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + categoryTable + " WHERE " + categoryType + " = '" + category_type + "'";

        Log.d("- DB - getCateogry", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Category ctg = new Category();
                ctg.setIdCategory(c.getColumnIndex(idCategory));
                ctg.setCategoryName(c.getString(c.getColumnIndex(categoryName)));
                ctg.setCategoryType(c.getString(c.getColumnIndex(categoryType)));
                ctg.setCategoryImage(c.getString(c.getColumnIndex(categoryImage)));

                categories.add(ctg);
            } while (c.moveToNext());
        }
        return categories;
    }

//    public Category getCategory(long category_id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT * FROM " + categoryTable + " WHERE " + idCategory + " = " + category_id;
//
//        Log.d("- DB - getCateogry", selectQuery);
//
//        Cursor c = db.rawQuery(selectQuery, null);
//        if (c != null)
//            c.moveToFirst();
//
//        Category ctg = new Category();
//        ctg.setIdCategory(c.getColumnIndex(idCategory));
//        ctg.setCategoryName(c.getString(c.getColumnIndex(categoryName)));
//        ctg.setCategoryType(c.getString(c.getColumnIndex(categoryType)));
//        ctg.setCategoryImage(c.getString(c.getColumnIndex(categoryImage)));
//
//        return ctg;
//    }

    public Category getCategory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(categoryTable, new String[] { idCategory,
                        categoryName, categoryType, categoryImage }, idCategory + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Log.d("- DB - getCateogry", cursor.getString(2));
        Category contact = new Category(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return contact
        return contact;
    }
}