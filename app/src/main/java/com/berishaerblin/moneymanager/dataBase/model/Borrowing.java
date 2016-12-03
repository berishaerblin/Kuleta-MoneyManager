package com.berishaerblin.moneymanager.dataBase.model;

import java.util.Date;

/**
 * Created by berishaerblin on 12/3/16.
 */

public class Borrowing {

    private int idBorrowing;
    private String borrowingTitle;
    private String borrowingType;
    private Date borrowingDate;
    private double borrowingValue;
    private double borrowingInteres;
    private int fBBalance;

    public Borrowing(int fBBalance,
                     int idBorrowing,
                     String borrowingTitle,
                     String borrowingType,
                     Date borrowingDate,
                     double borrowingValue,
                     double borrowingInteres) {
        this.fBBalance = fBBalance;
        this.idBorrowing = idBorrowing;
        this.borrowingTitle = borrowingTitle;
        this.borrowingType = borrowingType;
        this.borrowingDate = borrowingDate;
        this.borrowingValue = borrowingValue;
        this.borrowingInteres = borrowingInteres;
    }

    public int getIdBorrowing() {
        return idBorrowing;
    }

    public void setIdBorrowing(int idBorrowing) {
        this.idBorrowing = idBorrowing;
    }

    public String getBorrowingTitle() {
        return borrowingTitle;
    }

    public void setBorrowingTitle(String borrowingTitle) {
        this.borrowingTitle = borrowingTitle;
    }

    public String getBorrowingType() {
        return borrowingType;
    }

    public void setBorrowingType(String borrowingType) {
        this.borrowingType = borrowingType;
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public double getBorrowingValue() {
        return borrowingValue;
    }

    public void setBorrowingValue(double borrowingValue) {
        this.borrowingValue = borrowingValue;
    }

    public double getBorrowingInteres() {
        return borrowingInteres;
    }

    public void setBorrowingInteres(double borrowingInteres) {
        this.borrowingInteres = borrowingInteres;
    }

    public double getfBBalance() {
        return fBBalance;
    }

    public void setfBBalance(int fBBalance) {
        this.fBBalance = fBBalance;
    }
}
