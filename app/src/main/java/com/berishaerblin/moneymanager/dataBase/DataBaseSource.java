package com.berishaerblin.moneymanager.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.berishaerblin.moneymanager.dataBase.model.Balance;

/**
 * Created by berishaerblin on 11/27/16.
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
                +idBalance+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                +totalBalance+" DOUBLE);");

        db.execSQL("CREATE TABLE "+categoryTable+"("
                +idCategory+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                +categoryName+" TEXT NOT NULL, "
                +categoryType+" TEXT NOT NULL);");

        db.execSQL("CREATE TABLE "+incomeTable+"("
                +idIncome+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                +incomeValue+" DOUBLE NOT NULL, "
                +incomeDate+" TEXT, "
                +fICategoryType+" INTEGER, "
                +fIBalance+" INTEGER, "
                +"FOREIGN KEY ("+fICategoryType+") REFERENCES "+categoryTable+" ("+idCategory+") ON DELETE CASCADE "+"ON UPDATE CASCADE "
                +"FOREIGN KEY ("+fIBalance+") REFERENCES "+balanceTable+" ("+idBalance+") ON DELETE CASCADE "+"ON UPDATE CASCADE);");

        db.execSQL("CREATE TABLE "+expenseTable+"("
                +idExpense+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                +expenseValue+" DOUBLE NOT NULL, "
                +expenseDate+" TEXT, "
                +fECategoryType+" INTEGER, "
                +fEBalance+" INTEGER, "
                +"FOREIGN KEY ("+fECategoryType+") REFERENCES "+categoryTable+" ("+idCategory+") ON DELETE CASCADE "+"ON UPDATE CASCADE "
                +"FOREIGN KEY ("+fEBalance+") REFERENCES "+balanceTable+" ("+idBalance+") ON DELETE CASCADE "+"ON UPDATE CASCADE);");

        db.execSQL("CREATE TABLE "+historyTable+"("
                +idHistory+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                +historyTitle+" TEXT NOT NULL, "
                +fIncomeHistory+" INTEGER, "
                +fExpenseHistory+" INTEGER, "
                +"FOREIGN KEY ("+fIncomeHistory+") REFERENCES "+incomeTable+" ("+idIncome+") ON DELETE CASCADE "+"ON UPDATE CASCADE "
                +"FOREIGN KEY ("+fExpenseHistory+") REFERENCES "+expenseTable+" ("+idExpense+") ON DELETE CASCADE "+"ON UPDATE CASCADE);");

        db.execSQL("CREATE TABLE "+savingsTable+"("
                +idSavings+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                +savingsTitle+" TEXT, "
                +savingsValue+" DOUBLE NOT NULL, "
                +savingsDate+" TEXT, "
                +fSBalance+" INTEGER, "
                +"FOREIGN KEY ("+fSBalance+") REFERENCES "+balanceTable+" ("+idBalance+") ON DELETE CASCADE "+"ON UPDATE CASCADE);");

        db.execSQL("CREATE TABLE "+borrowingTable+"("
                +idBorrowing+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                +borrowingTitle+" TEXT, "
                +borrowingType+" TEXT, "
                +borrowingDate+" TEXT, "
                +borrowingValue+" DOUBLE, "
                +borrowingInteres+" DOUBLE, "
                +fBBalance+" INTEGER, "
                +"FOREIGN KEY ("+fBBalance+") REFERENCES "+balanceTable+" ("+idBalance+") ON DELETE CASCADE "+"ON UPDATE CASCADE);");

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

    public void insertIntoBalance(Balance balance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.totalBalance,balance.getTotalBalance());

        db.insert(this.balanceTable,null,contentValues);
        db.close();

    }



}