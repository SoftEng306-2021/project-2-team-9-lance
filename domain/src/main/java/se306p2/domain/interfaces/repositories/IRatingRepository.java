package se306p2.domain.interfaces.repositories;

import se306p2.domain.interfaces.entity.IRating;

public interface IRatingRepository {

    IRatingRepository getInstance();
    IRating getRating(String productId);
    IRating addRating(String productId,String userId);
    IRating removeRating(String productId);
}
