package se306p2.domain.interfaces.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.entity.IRating;

/**
 * Interface for the add rating use case.
 */
public interface IAddRatingUseCase {
    Single<IRating> addRating(String productId, String userId, Integer addedRating);
}
