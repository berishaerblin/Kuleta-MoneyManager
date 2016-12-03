package com.berishaerblin.moneymanager.dataBase.model;

import java.util.Date;

/**
 * Created by berishaerblin on 12/3/16.
 */

public class Savings {

    private int idSavings;
    private String savingsTitle;
    private double savingsValue;
    private Date savingsDate;
    private int fSBalance;

    public Savings(int idSavings, String savingsTitle, double savingsValue, Date savingsDate, int fSBalance) {
        this.idSavings = idSavings;
        this.savingsTitle = savingsTitle;
        this.savingsValue = savingsValue;
        this.savingsDate = savingsDate;
        this.fSBalance = fSBalance;
    }

    public int getIdSavings() {
        return idSavings;
    }

    public void setIdSavings(int idSavings) {
        this.idSavings = idSavings;
    }

    public String getSavingsTitle() {
        return savingsTitle;
    }

    public void setSavingsTitle(String savingsTitle) {
        this.savingsTitle = savingsTitle;
    }

    public double getSavingsValue() {
        return savingsValue;
    }

    public void setSavingsValue(double savingsValue) {
        this.savingsValue = savingsValue;
    }

    public Date getSavingsDate() {
        return savingsDate;
    }

    public void setSavingsDate(Date savingsDate) {
        this.savingsDate = savingsDate;
    }

    public int getfSBalance() {
        return fSBalance;
    }

    public void setfSBalance(int fSBalance) {
        this.fSBalance = fSBalance;
    }
}
