package se306p2.model.entities;

import se306p2.domain.interfaces.entity.IRating;

public class Rating implements IRating {

    private String productID;
    private double rating;
    private int num;

    public Rating(String productID, double rating, int num) {
        this.productID = productID;
        this.rating = rating;
        this.num = num;
    }

    public double getRating() {
        return this.rating;
    }

    public int getNum() {
        return this.num;
    }
}
