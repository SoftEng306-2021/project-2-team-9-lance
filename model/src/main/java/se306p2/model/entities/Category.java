package se306p2.model.entities;

import se306p2.domain.interfaces.entity.ICategory;

public class Category implements ICategory {
    private String categoryID;
    private String categoryName;
    private String imageURI;

    public Category(String categoryID, String categoryName, String imageURI) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.imageURI = imageURI;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getImageURI() {
        return this.imageURI;
    }
}
