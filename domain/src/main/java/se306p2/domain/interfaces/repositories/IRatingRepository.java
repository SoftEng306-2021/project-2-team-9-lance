package se306p2.domain.interfaces.repositories;

import se306p2.domain.interfaces.entity.IRating;

/**
 * Interface for the Rating Repository
 */
public interface IRatingRepository {

    /**
     * Get ratings by product id
     * @param productId
     * @return product rating
     */
    IRating getRating(String productId);
    
    /**
     * Rate a product as a user
     * @param productId
     * @param userId
     * @param addedRating
     * @return product rating
     */
    IRating addRating(String productId, String userId, Integer addedRating);

    /**
     * Remove rating from a product as a user
     * @param productId
     * @param userId
     * @return product rating
     */
    IRating removeRating(String productId, String userId);

    /**
     * Check if product has already been reated by user
     * @param productId
     * @param userId
     * @return rated by user
     */
    Boolean rated(String productId, String userId);
}
