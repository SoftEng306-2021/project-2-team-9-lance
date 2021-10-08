package se306p2.model.transformers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.model.entities.Benefit;
import se306p2.model.entities.ProductVersion;

public class ProductVersionTransformer {

    private ProductVersionTransformer() {

    }

    public static Map<String, Object> pack(IProductVersion productVersion) {

        Map<String, Object> map = new HashMap<>();

        map.put("versionId", productVersion.getId());
        map.put("name", productVersion.getName());
        map.put("hexColor", productVersion.getHexColor());
        map.put("imageURI", productVersion.getImageURI());
        map.put("order",productVersion.getOrder());

        return map;
    }

    public static IProductVersion unpack(String Id, Map<String, Object> map) {

        String name = map.containsKey("name") ? map.get("name").toString() : "";
        String hexColor = map.containsKey("hexColor") ? map.get("hexColor").toString() : "";

        List<Object> imageURIObjs  = new ArrayList<>((Collection<?>)map.get("imageURI") );
        List<String> imageURIs = new ArrayList<String>();
        imageURIObjs.forEach((i) -> imageURIs.add(String.valueOf(i)));

        int order = ((Long) map.get("order")).intValue();

        ProductVersion productVersion = new ProductVersion(Id,name,hexColor,imageURIs,order );
        return productVersion;
    }

}
