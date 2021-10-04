package se306p2.model.transformers;

import com.google.firebase.firestore.DocumentReference;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se306p2.domain.interfaces.entity.ICategory;
import se306p2.model.entities.Category;

public class CategoryTransformer {

    private CategoryTransformer() {

    }

    public static Map<String, Object> pack(ICategory category) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", category.getCategoryName());
        map.put("imageURI", category.getImageURI());

        return map;
    }

    public static ICategory unpack(String categoryID, Map<String, Object> map) {
        String name = map.get("name").toString();
        String imageURI = map.get("imageURI").toString();

        Category category = new Category(categoryID, name, imageURI);
        return category;
    }
}
