package com.berishaerblin.moneymanager.dataBase.model;

/**
 * Created by mergimkrasniqi on 1/16/17.
 */

public class IncomeExpense {
    private int id;
    private int idIncome;
    private int idExpense;
    private int month;

    public IncomeExpense(){}

    public IncomeExpense(int id, int idExpense, int idIncome, int month) {
        this.id = id;
        this.idExpense = idExpense;
        this.idIncome = idIncome;
        this.month = month;
    }

    public IncomeExpense(int idExpense, int idIncome, int month) {
        this.idExpense = idExpense;
        this.idIncome = idIncome;
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdExpense() {
        return idExpense;
    }

    public void setIdExpense(int idExpense) {
        this.idExpense = idExpense;
    }

    public int getIdIncome() {
        return idIncome;
    }

    public void setIdIncome(int idIncome) {
        this.idIncome = idIncome;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
