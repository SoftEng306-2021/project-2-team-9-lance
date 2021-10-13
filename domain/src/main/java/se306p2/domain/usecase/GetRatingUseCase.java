package se306p2.domain.usecase;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IRating;
import se306p2.domain.interfaces.usecase.IGetRatingUseCase;

public class GetRatingUseCase implements IGetRatingUseCase {
    public IRating getRating(String productId) {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(RepositoryRouter.getRatingRepository().getRating(productId));
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
