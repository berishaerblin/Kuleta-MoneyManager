package com.berishaerblin.moneymanager.dataBase.model;

/**
 * Created by berishaerblin on 12/3/16.
 */

public class Balance {

    private int idBalance;
    private double totalBalance;

    public Balance(){}
    public Balance(int idBalance, double totalBalance){
        this.idBalance = idBalance;
        this.totalBalance = totalBalance;
    }

    public Balance(double totalBalance){
        this.totalBalance = totalBalance;
    }

    public int getIdBalance() {
        return idBalance;
    }

    public void setIdBalance(int idBalance) {
        this.idBalance = idBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }
}
