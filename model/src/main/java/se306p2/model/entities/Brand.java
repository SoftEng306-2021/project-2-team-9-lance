package se306p2.model.entities;

import se306p2.domain.interfaces.entity.IBrand;

/**
 * @see IBrand
 */
public class Brand implements IBrand {
    private String id;
    private String name;
    private String imageURI;

    public Brand(String id, String name, String imageURI) {
        this.id = id;
        this.name = name;
        this.imageURI = imageURI;
    }

    public String getId() { return this.id; }

    public String getName() { return this.name; }

    public String getImageURI() { return this.imageURI; }
}
