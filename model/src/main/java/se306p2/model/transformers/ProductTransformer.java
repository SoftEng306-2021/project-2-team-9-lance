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

        List<Object> ingredientsObj = Arrays.asList(map.get("ingredients"));
        List<String> ingredients = new ArrayList<String>();
        ingredientsObj.forEach((x) -> ingredients.add(String.valueOf(x)));

        IProduct.Form form = (IProduct.Form) map.get("form");
        BigDecimal price = (BigDecimal) map.get("default");
        Double numericRating = Double.parseDouble(map.get("numericRaing").toString());
        int numReviews = Integer.parseInt(map.get("numReviews").toString());
        String defaultImageURI = map.get("defaultImageURI").toString();

        Product product = new Product(productID, categoryID, name, brandID, brandName, slogan, details, usage, link,
                ingredients, form, price, numericRating, numReviews, defaultImageURI);
        return product;

    }
}
