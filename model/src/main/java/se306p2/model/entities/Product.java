package se306p2.model.entities;

import java.math.BigDecimal;
import java.util.List;

import se306p2.domain.interfaces.entity.IProduct;

public class Product implements IProduct {

    private String productId;
    private String categoryId;
    private String name;
    private String brandId;
    private String brandName;
    private String slogan;
    private String details;
    private String usage;
    private String link;
    private List<String> ingredients;
    private Form form;
    private BigDecimal price;
    private Double numericRating;
    private int numReviews;
    private String defaultImageURI;




    public Product(String productId, String categoryId, String name, String brandId,
                   String brandName, String slogan, String details, String usage, String link,
                   List<String> ingredients, Form form, BigDecimal price,
                   Double numericRating,int numReviews, String defaultImageURI) {

        this.productId = productId;
        this.categoryId = categoryId;
        this.name = name;
        this.brandId = brandId;
        this.brandName = brandName;
        this.slogan = slogan;
        this.details = details;
        this.usage = usage;
        this.link = link;
        this.ingredients = ingredients;
        this.form = form;
        this.numericRating = numericRating;
        this.numReviews = numReviews;
        this.defaultImageURI = defaultImageURI;
        this.price = price;

    }

    public String getId() {
        return this.productId;
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

    public String getDefaultImageURI() {return this.defaultImageURI; }

    public String getCategoryId() {
        return this.categoryId;
    }

    public String getBrandId() {
        return this.brandId;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Double getNumericRating() {
        return this.numericRating;
    }


    public Number getNumReviews() {
        return this.numReviews;
    }
}
