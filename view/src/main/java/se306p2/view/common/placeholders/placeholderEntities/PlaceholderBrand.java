package se306p2.view.common.placeholders.placeholderEntities;

import se306p2.domain.interfaces.entity.IBrand;

public class PlaceholderBrand implements IBrand {
    private String id;
    private String name;
    private String imageURI;

    public PlaceholderBrand(String id, String name, String imageURI) {
        this.id = id;
        this.name = name;
        this.imageURI = imageURI;
    }

    public String getId() { return this.id; }

    public String getName() { return this.name; }

    public String getImageURI() { return this.imageURI; }
}
