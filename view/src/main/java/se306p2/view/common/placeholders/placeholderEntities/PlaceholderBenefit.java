package se306p2.view.common.placeholders.placeholderEntities;

import se306p2.domain.interfaces.entity.IBenefit;

public class PlaceholderBenefit implements IBenefit {

    private String id;
    private String benefitId;
    private String name;
    private String imageURI;

    public PlaceholderBenefit(String id, String benefitId,String name, String imageURI) {
        this.id = id;
        this.benefitId = benefitId;
        this.name = name;
        this.imageURI = imageURI;
    }

    public String getId() { return this.id; }

    public String getName() { return this.name; }

    public String getImageURI() { return this.imageURI; }
}
