package se306p2.domain.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IRating;
import se306p2.domain.interfaces.usecase.IGetRatingUseCase;

/**
 * This class is used to get a rating from the repository.
 */
public class GetRatingUseCase implements IGetRatingUseCase {
    public Single<IRating> getRating(String productId) {
        return Single.create(emitter -> {
            try {
                IRating rating = RepositoryRouter.getRatingRepository().getRating(productId);
                if (rating == null) {
                    emitter.onError(new NullPointerException());
                    return;
                }
                emitter.onSuccess(rating);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
