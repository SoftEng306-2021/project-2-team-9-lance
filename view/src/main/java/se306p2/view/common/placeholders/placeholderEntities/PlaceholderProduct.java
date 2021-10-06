package se306p2.view.common.placeholders.placeholderEntities;

import java.math.BigDecimal;
import java.util.List;

import se306p2.domain.interfaces.entity.IProduct;

public class PlaceholderProduct implements IProduct {

    private String productID;
    private String categoryID;
    private String name;
    private String brandID;
    private String brandName;
    private String slogan;
    private String details;
    private String usage;
    private String link;
    private List<String> ingredients;
    private Form form;
    private BigDecimal defaultPrice;
    private Double numericRating;
    private int numReviews;
    private String defaultImageURI;

    public PlaceholderProduct(String productID, String categoryID, String name, String brandID,
                   String brandName, String slogan, String details, String usage, String link,
                   List<String> ingredients, Form form, BigDecimal defaultPrice,
                   Double numericRating,int numReviews, String defaultImageURI) {

        this.productID = productID;
        this.categoryID = categoryID;
        this.name = name;
        this.brandID = brandID;
        this.brandName = brandName;
        this.slogan = slogan;
        this.details = details;
        this.usage = usage;
        this.link = link;
        this.ingredients = ingredients;
        this.form = form;
        this.defaultPrice = defaultPrice;
        this.numericRating = numericRating;
        this.numReviews = numReviews;
        this.defaultImageURI = defaultImageURI;

    }

    public String getProductID() {
        return this.productID;
    }

    public String getName() {
        return this.name;
    }

    public String getSlogan() {
        return this.slogan;
    }

    public String getDetails() {
        return this.details;
    }

    public String getUsage() {
        return this.usage;
    }

    public List<String> getIngredients() {
        return this.ingredients;
    }

    public String getLink() {
        return this.link;
    }

    public Form getForm() {
        return this.form;
    }

    public String getCategoryID() { return this.categoryID; }

    public String getBrandID() { return this.brandID; }

    public String getBrandName() { return this.brandName; }

    public BigDecimal getPrice() { return this.defaultPrice; }

    public Double getNumericRating() { return this.numericRating; }

    public Number getNumReviews() { return this.numReviews; }

    public String getDefaultImageURI() {return this.defaultImageURI; }
}
