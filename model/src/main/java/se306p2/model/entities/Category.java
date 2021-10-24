package se306p2.model.entities;

import se306p2.domain.interfaces.entity.ICategory;

/**
 * @see ICategory
 */
public class Category implements ICategory {
    private String categoryId;
    private String categoryName;
    private String imageURI;

    public Category(String categoryId, String categoryName, String imageURI) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.imageURI = imageURI;
    }

    public String getId() { return this.categoryId; }

    public String getName() {
        return this.categoryName;
    }

    public String getImageURI() {
        return this.imageURI;
    }
}
