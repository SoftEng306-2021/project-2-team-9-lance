package se306p2.model.transformers;

import com.google.firebase.firestore.DocumentReference;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se306p2.domain.interfaces.entity.IProduct;
import se306p2.model.entities.Product;

public class ProductTransformer {

    private ProductTransformer() {

    }

    public static Map<String,Object> pack(IProduct product) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("categoryID", product.getCategoryID());
        map.put("name", product.getName());
        map.put("brandID", product.getBrandID());
        map.put("brandName", product.getBrandName());
        map.put("slogan", product.getSlogan());
        map.put("details", product.getDetails());
        map.put("usage",product.getUsage());
        map.put("link",product.getLink());
        map.put("ingredients",product.getIngredients());
        map.put("form",product.getForm());
        map.put("price", product.getPrice());
        map.put("numericRating",product.getNumericRating());
        map.put("numReviews", product.getNumReviews());
        map.put("defaultImageURI",product.getDefaultImageURI());

        return map;
    }

    public static IProduct unpack(String productID,String brandName, Map<String, Object> map) {

        DocumentReference categoryRef = (DocumentReference) map.get("category");
        String categoryID = categoryRef.getId();


        DocumentReference brandRef = (DocumentReference) map.get("brand");
        String brandID = brandRef.getId();

        String name = map.get("name").toString();
        String slogan = map.get("slogan").toString();
        String details = map.get("details").toString();
        String usage = map.get("usage").toString();
        String link = map.get("link").toString();
        List<String> ingredients = (List<String>) map.get("ingredients");
        IProduct.Form form = (IProduct.Form) map.get("form");
        BigDecimal price = (BigDecimal) map.get("default");
        double numericRating = (double) map.get("numericRaing");
        int numReviews = (int) map.get("numReviews");
        String defaultImageURI = map.get("defaultImageURI").toString();

        Product product = new Product(productID, categoryID, name, brandID, brandName, slogan, details, usage, link,
                ingredients, form, price, numericRating, numReviews, defaultImageURI);
        return product;

    }
}
