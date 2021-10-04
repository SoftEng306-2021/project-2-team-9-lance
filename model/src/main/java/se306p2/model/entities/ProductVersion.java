package se306p2.model.entities;

import java.util.List;

import se306p2.domain.interfaces.entity.IProductVersion;

public class ProductVersion implements IProductVersion {

    private String productID;
    private String name;
    private String hexColor;
    private List<String> imageURI;
    private int order;

    public ProductVersion(String productID, String name, String hexColor, List<String> imageURI,
                          int order) {

        this.productID = productID;
        this.name = name;
        this.hexColor = hexColor;
        this.imageURI = imageURI;
        this.order = order;

    }

    public String getName() {
        return this.name;
    }

    public String getHexColor() {
        return this.hexColor;
    }

    public List<String> getImageURI() {
        return this.imageURI;
    }

    public int getOrder() {
        return this.order;
    }
}
