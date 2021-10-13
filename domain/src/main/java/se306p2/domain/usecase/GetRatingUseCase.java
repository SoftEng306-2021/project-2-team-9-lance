package se306p2.domain.usecase;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IRating;
import se306p2.domain.interfaces.usecase.IGetRatingUseCase;

public class GetRatingUseCase implements IGetRatingUseCase {
    public IRating getRating(String productId) {
        return RepositoryRouter.getRatingRepository().getRating(productId);
    }
}
