package org.example.models;

import java.sql.Timestamp;

public class Category {

    private int id;
    private String CategoryName;

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getCategoryName ( ) {
        return CategoryName;
    }

    public void setCategoryName (String categoryName) {
        CategoryName = categoryName;
    }
}
