package se306p2.model.transformers;

import com.google.firebase.firestore.DocumentReference;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import se306p2.domain.interfaces.entity.ICategory;
import se306p2.model.entities.Category;

public class CategoryTransformer {

    private CategoryTransformer() {

    }

    public static Map<String,Object> pack(ICategory category) {

        Map<String, Object> map = new HashMap<String, Object>()

        map.put("name", category.getName());

        return null;
    }

    public static ICategory unpack(String categoryID, Map<String, Object> map) {
        DocumentReference categoryRef = (DocumentReference) map.get("category");
        // String categoryID;
        String categoryID = map.get("categoryID").toString();
        String name = map.get("name").toString();
        String brandID = map.get("brandID").toString();
        String brandName = map.get("brandName").toString();
        String slogan = map.get("slogan").toString();
        String details = map.get("details").toString();
        String usage = map.get("usage").toString();
        String link = map.get("link").toString();
        List<String> ingredients = (List<String>) map.get("ingredients");
        ICategory.Form form = (ICategory.Form) map.get("form");
        BigDecimal defaultPrice = (BigDecimal) map.get("default");
        double numericRating = (double) map.get("numericRaing");
        int numReviews = (int) map.get("numReviews");

        Category category = new Category(categoryID);
        return category;

    }
}
