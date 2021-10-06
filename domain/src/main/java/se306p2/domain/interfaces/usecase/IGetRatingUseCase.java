package se306p2.domain.interfaces.usecase;

import se306p2.domain.interfaces.entity.IRating;

public interface IGetRatingUseCase {
    IRating getRating(String productId);
}
