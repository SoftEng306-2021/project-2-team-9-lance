package se306p2.model.entities;

import se306p2.domain.interfaces.entity.IBrand;

public class Brand implements IBrand {
    private String brandID;
    private String name;
    private String imageURI;

    public Brand(String brandID,String name, String imageURI) {
        this.brandID = brandID;
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
