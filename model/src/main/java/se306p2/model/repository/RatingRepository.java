package se306p2.model.repository;

import com.google.firebase.firestore.FirebaseFirestore;

import se306p2.domain.interfaces.entity.IRating;
import se306p2.model.interfaces.IRatingRepository;

public class RatingRepository implements IRatingRepository {

    private FirebaseFirestore db;
    private RatingRepository instance;

    @Override
    public IRatingRepository getInstance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public IRating getRating(String productID) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IRating addRating(String productID, String userID) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IRating removeRating(String productID) {
        throw new UnsupportedOperationException();
    }
}
