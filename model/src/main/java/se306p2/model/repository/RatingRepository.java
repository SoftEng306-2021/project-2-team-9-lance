package se306p2.model.repository;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.entity.IRating;
import se306p2.domain.interfaces.repositories.IRatingRepository;
import se306p2.model.transformers.ProductTransformer;
import se306p2.model.transformers.RatingTransformer;

public class RatingRepository implements IRatingRepository {

    private FirebaseFirestore db;
    private static RatingRepository instance;

    private RatingRepository(FirebaseFirestore firestore) {
        this.db = firestore;
    }

    public static IRatingRepository getInstance() {
        if (instance == null) {
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            instance = new RatingRepository(firestore);
        }
        return instance;
    }

    public IRating getRating(String productId) {
        try {
            DocumentSnapshot snapshot = Tasks.await(db.collection("product").document(productId).get());
            if (!snapshot.exists()) {
                return null;
            }

            return RatingTransformer.unpack(snapshot.getId(), snapshot.getData());
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public IRating addRating(String productId, String userId, Integer addedRating) {

        try {

            DocumentSnapshot userSnapshot = Tasks.await(db.collection("user").document(userId).get());
            DocumentSnapshot productSnapshot = Tasks.await(db.collection("product").document(productId).get());

            if (productSnapshot.getData() == null) {
                throw new UnsupportedOperationException("product does not exist");
            }

            IProduct product = ProductTransformer.unpack(productSnapshot.getId(), productSnapshot.getData().get("brand").toString(), productSnapshot.getData());

            Double currentRatingInProduct = product.getNumericRating();
            Number currentNumReviews = product.getNumReviews();

            Double cumulativeRating = currentRatingInProduct * currentNumReviews.doubleValue();

            Number newNumReviews;
            Double newRating;

            Map<String, Object> entry;
            Map<String, Integer> userRatings = new HashMap<String, Integer>();
            ;

            if (userSnapshot.get("ratings") != null) {
                Map<String, Number> userRatingsObject = (Map<String, Number>) userSnapshot.get("ratings");

                for (Map.Entry<String, Number> userInfo : userRatingsObject.entrySet()) {
                    userRatings.put(userInfo.getKey(), userInfo.getValue().intValue());
                }


            } else {

                if (userSnapshot.get("favourites") == null) {

                    entry = new HashMap<String, Object>() {{
                        put("favourites", new ArrayList<DocumentReference>() {{
                        }});
                    }};
                    Tasks.await(db.collection("user").document(userId).set(entry));

                }

            }

            if (userRatings.containsKey(productId)) {
                Integer initValue = userRatings.get(productId);
                userRatings.remove(productId);

                newNumReviews = currentNumReviews.intValue();
                newRating = (cumulativeRating - Double.valueOf(initValue) + Double.valueOf(addedRating)) / newNumReviews.doubleValue();
            } else {

                newNumReviews = currentNumReviews.intValue() + 1;
                newRating = (cumulativeRating + Double.valueOf(addedRating)) / newNumReviews.doubleValue();

            }

            userRatings.put(productId, addedRating);

            Tasks.await(db.collection("user")
                    .document(userId)
                    .update("ratings", userRatings)
            );

            Tasks.await(db.collection("product")
                    .document(productId)
                    .update("numReviews", newNumReviews)
            );

            Tasks.await(db.collection("product")
                    .document(productId)
                    .update("numericRating", newRating)
            );

            return getRating(productId);


        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }

    }


    public IRating removeRating(String productId, String userId) {
        try {

            DocumentSnapshot userSnapshot = Tasks.await(db.collection("user").document(userId).get());
            DocumentSnapshot productSnapshot = Tasks.await(db.collection("product").document(productId).get());

            if (productSnapshot.getData() == null) {
                throw new UnsupportedOperationException("product does not exist");
            }

            IProduct product = ProductTransformer.unpack(productSnapshot.getId(), productSnapshot.getData().get("brand").toString(), productSnapshot.getData());

            Double currentRatingInProduct = product.getNumericRating();
            Number currentNumReviews = product.getNumReviews();

            Double cumulativeRating = currentRatingInProduct * currentNumReviews.doubleValue();

            Number newNumReviews;
            Double newRating;

            Map<String, Integer> userRatings = new HashMap<String, Integer>();
            ;

            if (userSnapshot.get("ratings") != null) {
                Map<String, Number> userRatingsObject = (Map<String, Number>) userSnapshot.get("ratings");

                for (Map.Entry<String, Number> userInfo : userRatingsObject.entrySet()) {
                    userRatings.put(userInfo.getKey(), userInfo.getValue().intValue());
                }
                if (!userRatings.containsKey(productId)) {
                    throw new UnsupportedOperationException("This product has not been rated by the user yet");
                }

                if (userRatings.containsKey(productId)) {
                    Integer initValue = userRatings.get(productId);
                    userRatings.remove(productId);

                    newNumReviews = currentNumReviews.intValue() - 1;
                    if (newNumReviews.intValue() == 0) {
                        newRating = 0.0;

                    } else {
                        newRating = (cumulativeRating - Double.valueOf(initValue)) / newNumReviews.doubleValue();
                    }

                } else {
                    throw new UnsupportedOperationException("This product has not been rated by the user yet");
                }

                Tasks.await(db.collection("user")
                        .document(userId)
                        .update("ratings", userRatings)
                );

                Tasks.await(db.collection("product")
                        .document(productId)
                        .update("numReviews", newNumReviews)
                );

                Tasks.await(db.collection("product")
                        .document(productId)
                        .update("numericRating", newRating)
                );

            } else {

                throw new UnsupportedOperationException("The user has not rated any products yet");
            }

            return getRating(productId);
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Boolean rated(String productId, String userId) {
        try {
            DocumentSnapshot userSnapshot = Tasks.await(db.collection("user").document(userId).get());

            if (userSnapshot.get("ratings") == null) {
                return false;
            }

            Map<String, Number> userRatingsObject = (Map<String, Number>) userSnapshot.get("ratings");
            return userRatingsObject.containsKey(productId);

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
