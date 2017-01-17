package com.berishaerblin.moneymanager.dataBase.model;

/**
 * Created by mergimkrasniqi on 1/17/17.
 */

public class SavingsItem {
    int id;
    int idSavings;
    double value;
    String date;

    public SavingsItem() {
    }

    public SavingsItem(int id,double value,int idSavings,String date) {
        this.date = date;
        this.id = id;
        this.idSavings = idSavings;
        this.value = value;
    }

    public SavingsItem(double value,int idSavings,String date) {
        this.date = date;
        this.idSavings = idSavings;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSavings() {
        return idSavings;
    }

    public void setIdSavings(int idSavings) {
        this.idSavings = idSavings;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
