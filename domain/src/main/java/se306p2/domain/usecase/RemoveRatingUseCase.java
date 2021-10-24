package se306p2.domain.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IRating;
import se306p2.domain.interfaces.usecase.IRemoveRatingUseCase;

/**
 * RemoveRatingUseCase is a use case that removes a rating from the database.
 */
public class RemoveRatingUseCase implements IRemoveRatingUseCase {
    public Single<IRating> removeRating(String productId, String userId) {
        return Single.create(emitter -> {
            try {
                IRating rating = RepositoryRouter.getRatingRepository().removeRating(productId, userId);
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
