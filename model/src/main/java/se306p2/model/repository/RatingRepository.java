package se306p2.model.repository;

import com.google.firebase.firestore.FirebaseFirestore;

import se306p2.domain.interfaces.entity.IRating;
import se306p2.domain.interfaces.repositories.IRatingRepository;

public class RatingRepository implements IRatingRepository {

    private FirebaseFirestore db;
    private static RatingRepository instance;

    public static IRatingRepository getInstance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public IRating getRating(String productId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IRating addRating(String productId, String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IRating removeRating(String productId) {
        throw new UnsupportedOperationException();
    }
}
