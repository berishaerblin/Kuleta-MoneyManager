package com.berishaerblin.moneymanager.dataBase.model;

import java.util.Date;

/**
 * Created by berishaerblin on 12/3/16.
 */

public class Expense {


    private int idExpense;
    private double expenseValue;
    private Date expenseDate;
    private String fECategoryType;
    private int fEBalance;

    public Expense(int idExpense, double expenseValue, Date expenseDate, String fECategoryType, int fEBalance) {
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

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getfECategoryType() {
        return fECategoryType;
    }

    public void setfECategoryType(String fECategoryType) {
        this.fECategoryType = fECategoryType;
    }

    public double getfEBalance() {
        return fEBalance;
    }

    public void setfEBalance(int fEBalance) {
        this.fEBalance = fEBalance;
    }
}
