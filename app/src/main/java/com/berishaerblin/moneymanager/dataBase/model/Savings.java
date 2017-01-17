package com.berishaerblin.moneymanager.dataBase.model;

import java.util.Date;

/**
 * Created by berishaerblin on 12/3/16.
 * Edited by mkrasniqi on 01/17/17
 */

public class Savings {

    private int idSavings;
    private String savingsTitle;
    private double savingsValue;
    private String savingsDate;

    public Savings(){}

    public Savings(int idSavings, String savingsTitle, double savingsValue, String savingsDate) {
        this.idSavings = idSavings;
        this.savingsTitle = savingsTitle;
        this.savingsValue = savingsValue;
        this.savingsDate = savingsDate;
    }

    public Savings(String savingsTitle, double savingsValue, String savingsDate) {
        this.savingsTitle = savingsTitle;
        this.savingsValue = savingsValue;
        this.savingsDate = savingsDate;
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

    public String getSavingsDate() {
        return savingsDate;
    }

    public void setSavingsDate(String savingsDate) {
        this.savingsDate = savingsDate;
    }

    @Override
    public String toString() {
        return idSavings+"";
    }
}
