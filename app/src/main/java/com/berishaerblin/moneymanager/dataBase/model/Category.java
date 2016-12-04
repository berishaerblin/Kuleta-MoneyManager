package com.berishaerblin.moneymanager.dataBase.model;

/**
 * Created by berishaerblin on 12/3/16.
 */

public class Category {

    private int idCategory;
    private String categoryName;
    private String categoryType;
    private int categoryImage;

    public Category(int idCategory, String categoryName, String categoryType, int categoryImage) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.categoryImage = categoryImage;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }
}
