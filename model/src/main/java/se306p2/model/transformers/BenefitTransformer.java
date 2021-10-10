package se306p2.model.transformers;

import java.util.HashMap;
import java.util.Map;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.model.entities.Benefit;
import se306p2.model.entities.Category;

public class BenefitTransformer {

    private BenefitTransformer() {

    }

    public static Map<String, Object> pack(IBenefit benefit) {

        Map<String, Object> map = new HashMap<>();

        map.put("name", benefit.getName());
        map.put("imageURI", benefit.getImageURI());

        return map;
    }

    public static IBenefit unpack(String Id, Map<String, Object> map) {

        String name = map.containsKey("name") ? map.get("name").toString() : "";
        String imageURI = map.containsKey("imageURI") ? map.get("imageURI").toString(): "";

        Benefit benefit = new Benefit(Id,name,imageURI );
        return benefit;
    }
}
