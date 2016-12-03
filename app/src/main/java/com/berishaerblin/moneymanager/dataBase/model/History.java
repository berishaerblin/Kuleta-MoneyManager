package com.berishaerblin.moneymanager.dataBase.model;

/**
 * Created by berishaerblin on 12/3/16.
 */

public class History {

    private int idHistory;
    private String historyTitle;
    private int fIncomeHistory;
    private int fExpenseHistory;

    public History(int idHistory, String historyTitle, int fIncomeHistory, int fExpenseHistory) {
        this.idHistory = idHistory;
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

    public int getfIncomeHistory() {
        return fIncomeHistory;
    }

    public void setfIncomeHistory(int fIncomeHistory) {
        this.fIncomeHistory = fIncomeHistory;
    }

    public int getfExpenseHistory() {
        return fExpenseHistory;
    }

    public void setfExpenseHistory(int fExpenseHistory) {
        this.fExpenseHistory = fExpenseHistory;
    }
}
