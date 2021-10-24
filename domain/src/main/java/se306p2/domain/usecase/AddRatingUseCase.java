package se306p2.domain.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IRating;
import se306p2.domain.interfaces.usecase.IAddRatingUseCase;

/**
 * This class is used to add a rating to a user.
 */
public class AddRatingUseCase implements IAddRatingUseCase {
    public Single<IRating> addRating(String productId, String userId, Integer addedRating) {
        return Single.create(emitter -> {
            try {
                IRating rating = RepositoryRouter.getRatingRepository().addRating(productId, userId, addedRating);
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
