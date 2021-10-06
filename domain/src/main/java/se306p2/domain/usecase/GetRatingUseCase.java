package se306p2.domain.usecase;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IRating;

public class GetRatingUseCase {
    public IRating getRating(String productId) {
        return RepositoryRouter.getRatingRepository().getRating(productId);
    }
}
