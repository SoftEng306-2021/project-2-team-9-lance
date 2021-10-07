package se306p2.model.entities;

import se306p2.domain.interfaces.entity.IRating;

public class Rating implements IRating {
    private String productId;
    private double rating;
    private int num;

    public Rating(String productId, double rating, int num) {
        this.productId = productId;
        this.rating = rating;
        this.num = num;
    }

    public String getId() { return this.productId; }

    public double getRating() {
        return this.rating;
    }

    public int getNum() {
        return this.num;
    }

}
