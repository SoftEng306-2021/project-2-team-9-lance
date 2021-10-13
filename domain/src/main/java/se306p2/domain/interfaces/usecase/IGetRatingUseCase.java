package se306p2.domain.interfaces.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.entity.IRating;

public interface IGetRatingUseCase {
    Single<IRating> getRating(String productId);
}
