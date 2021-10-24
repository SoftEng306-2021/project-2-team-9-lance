package se306p2.domain.interfaces.entity;

import java.util.List;

/**
 * Interface for ProductVersion
 */
public interface IProductVersion {
    String getId();
    String getName();
    String getHexColor();
    List<String> getImageURI();
    int getOrder();
    
}
