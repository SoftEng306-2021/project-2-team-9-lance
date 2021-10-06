package se306p2.view.common.placeholders.placeholderEntities;

import se306p2.domain.interfaces.entity.ICategory;

public class PlaceholderCategory implements ICategory {
    private String categoryId;
    private String categoryName;
    private String imageURI;

    public PlaceholderCategory(String categoryId, String categoryName, String imageURI) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.imageURI = imageURI;
    }

    public String getId() { return categoryId; }

    public String getName() {
        return this.categoryName;
    }

    public String getImageURI() {
        return this.imageURI;
    }
}