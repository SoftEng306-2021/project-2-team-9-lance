package se306p2.model.transformers;

import java.util.HashMap;
import java.util.Map;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.IRating;
import se306p2.model.entities.Benefit;
import se306p2.model.entities.Rating;

public class RatingTransformer {

    private RatingTransformer() {

    }

    public static Map<String, Object> pack(IRating rating) {

        Map<String, Object> map = new HashMap<>();

        map.put("numericRating", rating.getRating());
        map.put("numReviews", rating.getNum());

        return map;
    }

    public static IRating unpack(String Id, Map<String, Object> map) {

        Double numericRating = map.containsKey("numericRating") ? Double.valueOf( map.get("numericRating").toString()) : 0.0;
        int numReviews = map.containsKey("numReviews") ? ((Long) map.get("numReviews")).intValue()  : 0;

        Rating rating = new Rating(Id,numericRating,numReviews );
        return rating;
    }
}
