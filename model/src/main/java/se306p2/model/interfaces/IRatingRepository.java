package se306p2.model.interfaces;

import se306p2.domain.interfaces.entity.IRating;

public interface IRatingRepository {

    IRatingRepository getInstance();
    IRating getRating(String productID);
    IRating addRating(String productID,String userID);
    IRating removeRating(String productID);
}
