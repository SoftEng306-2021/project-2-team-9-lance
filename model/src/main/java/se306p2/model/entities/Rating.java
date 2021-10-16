package se306p2.model.entities;

import se306p2.domain.interfaces.entity.IRating;

public class Rating implements IRating {
    private String productId;
    private double numericRating;
    private int numReviews;

    public Rating(String productId, double rating, int num) {
        this.productId = productId;
        this.numericRating = rating;
        this.numReviews = num;
    }

    public String getId() { return this.productId; }

    public double getRating() {
        return this.numericRating;
    }

    public int getNum() {
        return this.numReviews;
    }

}
