package se306p2.domain.interfaces.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.entity.IRating;

/**
 * Interface for getting a rating.
 */
public interface IGetRatedUseCase {
    Single<Boolean> rated(String productId, String userId);
}
