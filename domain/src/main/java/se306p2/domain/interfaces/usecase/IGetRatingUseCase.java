package se306p2.domain.interfaces.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.entity.IRating;

/**
 * Interface for the get rating use case.
 */
public interface IGetRatingUseCase {
    Single<IRating> getRating(String productId);
}
