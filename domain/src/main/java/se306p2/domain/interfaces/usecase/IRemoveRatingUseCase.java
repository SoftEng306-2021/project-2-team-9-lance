package se306p2.domain.interfaces.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.entity.IRating;

/**
 * Interface for the remove rating use case.
 */
public interface IRemoveRatingUseCase {
    Single<IRating> removeRating(String productId, String userId);
}
