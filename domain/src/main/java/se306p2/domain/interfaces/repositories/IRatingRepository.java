package se306p2.domain.interfaces.repositories;

import se306p2.domain.interfaces.entity.IRating;

public interface IRatingRepository {

    IRating getRating(String productId);
    IRating addRating(String productId, String userId, Integer addedRating);
    IRating removeRating(String productId, String userId);
    Boolean rated(String productId, String userId);
}
