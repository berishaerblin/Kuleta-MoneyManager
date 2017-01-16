package com.berishaerblin.moneymanager.dataBase.model;

import java.util.Date;

/**
 * Created by berishaerblin on 12/3/16.
 */

public class Expense {


    private int idExpense;
    private double expenseValue;
    private String expenseDate;
    private int fECategoryType;

    public Expense(){}

    public Expense(int idExpense, double expenseValue, String expenseDate, int fECategoryType) {
        this.idExpense = idExpense;
        this.expenseValue = expenseValue;
        this.expenseDate = expenseDate;
        this.fECategoryType = fECategoryType;
    }

    public Expense(double expenseValue, String expenseDate, int fECategoryType) {
        this.expenseValue = expenseValue;
        this.expenseDate = expenseDate;
        this.fECategoryType = fECategoryType;
    }

    public int getIdExpense() {
        return idExpense;
    }

    public void setIdExpense(int idExpense) {
        this.idExpense = idExpense;
    }

    public double getExpenseValue() {
        return expenseValue;
    }

    public void setExpenseValue(double expenseValue) {
        this.expenseValue = expenseValue;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public int getfECategoryType() {
        return fECategoryType;
    }

    public void setfECategoryType(int fECategoryType) {
        this.fECategoryType = fECategoryType;
    }

    @Override
    public String toString() {
        return idExpense+"/"+expenseValue+"/"+fECategoryType;
    }
}
