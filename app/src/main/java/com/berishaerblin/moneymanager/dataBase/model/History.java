package com.berishaerblin.moneymanager.dataBase.model;

public class History {

    private int idHistory;
    private String historyTitle;
    private double fIncomeHistory;
    private double fExpenseHistory;

    public History(){}

    public History(int idHistory, String historyTitle, double fIncomeHistory, double fExpenseHistory) {
        this.idHistory = idHistory;
        this.historyTitle = historyTitle;
        this.fIncomeHistory = fIncomeHistory;
        this.fExpenseHistory = fExpenseHistory;
    }

    public History(String historyTitle, double fIncomeHistory, double fExpenseHistory){
        this.historyTitle = historyTitle;
        this.fIncomeHistory = fIncomeHistory;
        this.fExpenseHistory = fExpenseHistory;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public String getHistoryTitle() {
        return historyTitle;
    }

    public void setHistoryTitle(String historyTitle) {
        this.historyTitle = historyTitle;
    }

    public double getfIncomeHistory() {
        return fIncomeHistory;
    }

    public void setfIncomeHistory(double fIncomeHistory) {
        this.fIncomeHistory = fIncomeHistory;
    }

    public double getfExpenseHistory() {
        return fExpenseHistory;
    }

    public void setfExpenseHistory(double fExpenseHistory) {
        this.fExpenseHistory = fExpenseHistory;
    }

    @Override
    public String toString() {
        return fIncomeHistory+"/"+fExpenseHistory;
    }
}
