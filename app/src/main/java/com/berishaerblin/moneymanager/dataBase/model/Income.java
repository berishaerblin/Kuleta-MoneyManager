package com.berishaerblin.moneymanager.dataBase.model;

import java.util.Date;

/**
 * Created by berishaerblin on 12/3/16.
 */

public class Income {

    private int idIncome;
    private double incomeValue;
    private String incomeDate;
    private int fICategoryType;
    private int fIBalance;

    public Income(){}

    public Income(double incomeValue, String incomeDate, int fICategoryType, int fIBalance) {
        this.incomeValue = incomeValue;
        this.incomeDate = incomeDate;
        this.fICategoryType = fICategoryType;

    }

    public Income(int idIncome, double incomeValue, String incomeDate, int fICategoryType, int fIBalance) {
        this.idIncome = idIncome;
        this.incomeValue = incomeValue;
        this.incomeDate = incomeDate;
        this.fICategoryType = fICategoryType;
        this.fIBalance = fIBalance;
    }

    public int getIdIncome() {
        return idIncome;
    }

    public void setIdIncome(int idIncome) {
        this.idIncome = idIncome;
    }

    public double getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(double incomeValue) {
        this.incomeValue = incomeValue;
    }

    public String getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(String incomeDate) {
        this.incomeDate = incomeDate;
    }

    public int getfICategoryType() {
        return fICategoryType;
    }

    public void setfICategoryType(int fICategoryType) {
        this.fICategoryType = fICategoryType;
    }

    public int getfIBalance() {
        return fIBalance;
    }

    public void setfIBalance(int fIBalance) {
        this.fIBalance = fIBalance;
    }
}
