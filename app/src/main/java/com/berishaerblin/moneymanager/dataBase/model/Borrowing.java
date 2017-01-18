package com.berishaerblin.moneymanager.dataBase.model;

import java.util.Date;

/**
 * Created by berishaerblin on 12/3/16.
 */

public class Borrowing {

    private int idBorrowing;
    private String borrowingTitle;
    private String borrowingType;
    private String borrowingDate;
    private double borrowingValue;
    private double borrowingInteres;


    public Borrowing(){};

    public Borrowing(String borrowingTitle,
                     String borrowingDate,
                     double borrowingValue,
                     double borrowingInteres) {

        this.borrowingTitle = borrowingTitle;
        this.borrowingDate = borrowingDate;
        this.borrowingValue = borrowingValue;
        this.borrowingInteres = borrowingInteres;
    }

    public Borrowing(int idBorrowing,
                     String borrowingTitle,
                     String borrowingDate,
                     double borrowingValue,
                     double borrowingInteres) {

        this.idBorrowing = idBorrowing;
        this.borrowingTitle = borrowingTitle;
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

//    public String getBorrowingType() {
//        return borrowingType;
//    }

//    public void setBorrowingType(String borrowingType) {
//        this.borrowingType = borrowingType;
//    }

    public String getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(String borrowingDate) {
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

    @Override
    public String toString() {
        return idBorrowing+"";
    }
}
