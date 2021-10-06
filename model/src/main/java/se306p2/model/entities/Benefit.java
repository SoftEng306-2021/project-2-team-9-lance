package se306p2.model.entities;

import se306p2.domain.interfaces.entity.IBenefit;

public class Benefit implements IBenefit {
    private String id;
    private String benefitId;
    private String name;
    private String imageURI;

    public Benefit(String id, String benefitId,String name, String imageURI) {
        this.id = id;
        this.benefitId = benefitId;
        this.name = name;
        this.imageURI = imageURI;
    }

    public String getId() { return this.id; }

    public String getName() { return this.name; }

    public String getImageURI() { return this.imageURI; }
}
