package se306p2.view.common.placeholders.placeholderEntities;


import java.util.List;

import se306p2.domain.interfaces.entity.IProductVersion;

public class PlaceholderProductVersion implements IProductVersion {
    private String versionId;
    private String name;
    private String hexColor;
    private List<String> imageURI;
    private int order;

    public PlaceholderProductVersion(String versionId, String name, String hexColor, List<String> imageURI,
                          int order) {
        this.versionId = versionId;
        this.name = name;
        this.hexColor = hexColor;
        this.imageURI = imageURI;
        this.order = order;
    }

    public String getId() { return this.versionId; }

    public String getName() {
        return this.name;
    }

    public String getHexColor() {
        return this.hexColor;
    }

    public List<String> getImageURI() {
        return this.imageURI;
    }

    public int getOrder() {
        return this.order;
    }
}
