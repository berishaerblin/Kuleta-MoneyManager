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
    private int fEBalance;

    public Expense(){}
    public Expense(double expenseValue, String expenseDate, int fECategoryType, int fEBalance) {
        this.expenseValue = expenseValue;
        this.expenseDate = expenseDate;
        this.fECategoryType = fECategoryType;
        this.fEBalance = fEBalance;
    }

    public Expense(int idExpense, double expenseValue, String expenseDate, int fECategoryType, int fEBalance) {
        this.idExpense = idExpense;
        this.expenseValue = expenseValue;
        this.expenseDate = expenseDate;
        this.fECategoryType = fECategoryType;
        this.fEBalance = fEBalance;
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

    public double getfEBalance() {
        return fEBalance;
    }

    public void setfEBalance(int fEBalance) {
        this.fEBalance = fEBalance;
    }
}
