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

    public Income(){}

    public Income(double incomeValue, String incomeDate, int fICategoryType) {
        this.incomeValue = incomeValue;
        this.incomeDate = incomeDate;
        this.fICategoryType = fICategoryType;

    }

    public Income(int idIncome, double incomeValue, String incomeDate, int fICategoryType) {
        this.idIncome = idIncome;
        this.incomeValue = incomeValue;
        this.incomeDate = incomeDate;
        this.fICategoryType = fICategoryType;
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

    @Override
    public String toString() {
        return idIncome+"/"+incomeValue+"/"+fICategoryType;
    }
}
