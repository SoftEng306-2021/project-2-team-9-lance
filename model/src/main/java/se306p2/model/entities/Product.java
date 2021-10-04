package se306p2.model.entities;

import java.math.BigDecimal;
import java.util.List;

import se306p2.domain.interfaces.entity.IProduct;

public class Product implements IProduct {

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
    private BigDecimal price;
    private double numericRating;
    private int numReviews;

    public Product(String productID, String categoryID, String name, String brandID,
                   String brandName, String slogan, String details, String usage, String link,
                   List<String> ingredients, Form form, BigDecimal price,
                   Double numericRating,int numReviews) {

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
        this.price = price;
        this.numericRating = numericRating;
        this.numReviews = numReviews;

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

    @Override
    public String getCategoryID() {
        return this.categoryID;
    }

    @Override
    public String getBrandID() {
        return this.brandID;
    }

    @Override
    public String getBrandName() {
        return this.brandName
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public double getNumericRating() {
        return this.numericRating;
    }

    @Override
    public int getnumReviews() {
        return this.numReviews;
    }
}
