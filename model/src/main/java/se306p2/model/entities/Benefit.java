package se306p2.model.entities;

import se306p2.domain.interfaces.entity.IBenefit;

public class Benefit implements IBenefit {

    private String benefitID;
    private String name;
    private String imageURI;

    public Benefit(String benefitID,String name, String imageURI) {
        this.benefitID = benefitID;
        this.name = name;
        this.imageURI = imageURI;
    }

    public String getName() {
        return this.name;
    }


    public String getImageURI() {
        return this.imageURI;
    }
}
