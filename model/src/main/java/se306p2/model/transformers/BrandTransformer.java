package se306p2.model.transformers;

import java.util.HashMap;
import java.util.Map;

import se306p2.domain.interfaces.entity.IBrand;
import se306p2.model.entities.Brand;

public class BrandTransformer {

    private BrandTransformer() {

    }

    public static Map<String, Object> pack(IBrand brand) {

        Map<String, Object> map = new HashMap<>();

        map.put("name", brand.getName());
        map.put("imageURI", brand.getImageURI());

        return map;
    }

    public static IBrand unpack(String Id, Map<String, Object> map) {

        String name = map.containsKey("name") ? map.get("name").toString() : "";
        String imageURI = map.containsKey("imageURI") ? map.get("imageURI").toString(): "";

        Brand brand = new Brand(Id,name,imageURI );
        return brand;
    }
}
