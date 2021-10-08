package se306p2.model.transformers;

import com.google.firebase.firestore.DocumentReference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import se306p2.domain.interfaces.entity.IProduct;
import se306p2.model.entities.Product;

public class ProductTransformer {

    private ProductTransformer() {

    }

    public static Map<String, Object> pack(IProduct product) {

        Map<String, Object> map = new HashMap<>();

        map.put("categoryId", product.getCategoryId());
        map.put("name", product.getName());
        map.put("brandId", product.getBrandId());
        map.put("brandName", product.getBrandName());
        map.put("slogan", product.getSlogan());
        map.put("details", product.getDetails());
        map.put("usage", product.getUsage());
        map.put("link", product.getLink());
        map.put("ingredients", product.getIngredients());
        map.put("form", product.getForm());
        map.put("price", product.getPrice());
        map.put("numericRating", product.getNumericRating());
        map.put("numReviews", product.getNumReviews());
        map.put("defaultImageURI", product.getDefaultImageURI());

        return map;
    }

    public static IProduct unpack(String productId, String brandName, Map<String, Object> map) {

        String categoryId, brandId = categoryId = "";
        DocumentReference categoryRef = (DocumentReference) map.get("category");
        if (categoryRef != null) {
            categoryId = categoryRef.getId();
        }

        DocumentReference brandRef = (DocumentReference) map.get("brand");
        if (brandRef != null) {
            brandId = brandRef.getId();
        }

        String name = map.containsKey("name") ? map.get("name").toString() : "";
        String slogan = map.containsKey("slogan") ? map.get("slogan").toString() : "";
        String details = map.containsKey("details") ? map.get("details").toString() : "";
        String usage = map.containsKey("usage") ? map.get("usage").toString() : "";
        String link = map.containsKey("link") ? map.get("link").toString() : "";

        List<Object> ingredientsObj = Arrays.asList(map.get("ingredients"));
        List<String> ingredients = new ArrayList<String>();
        ingredientsObj.forEach((i) -> ingredients.add(String.valueOf(i)));

        IProduct.Form form = IProduct.Form.valueOf(map.get("form").toString());
        BigDecimal price = new BigDecimal(map.containsKey("price") ? (double) map.get("price") : 0.0);
        Double numericRating = map.containsKey("numericRating") ? (double) map.get("numericRating") : 0.0;
        int numReviews = map.containsKey("numReviews") ? ((Long) map.get("numReviews")).intValue()  : 0;
        String defaultImageURI = map.containsKey("defaultImageURI") ? map.get("defaultImageURI").toString() : "";

        return new Product(productId, categoryId, name, brandId, brandName, slogan, details, usage, link,
                ingredients, form, price, numericRating, numReviews, defaultImageURI);
    }
}
