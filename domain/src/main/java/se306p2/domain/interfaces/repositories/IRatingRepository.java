package se306p2.domain.interfaces.repositories;

import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.IRating;

public interface IRatingRepository {

    IRatingRepository getInstance();
    IRating getRating(String productId);
    IRating addRating(String productId, String userId, Integer addedRating);
    IRating removeRating(String productId, String userId);
}
