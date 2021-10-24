package se306p2.domain.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IRating;
import se306p2.domain.interfaces.usecase.IGetRatedUseCase;

/**
 * This class is used to get the ratings of a user.
 */
public class GetRatedUseCase implements IGetRatedUseCase {
    public Single<Boolean> rated(String productId, String userId) {
        return Single.create(emitter -> {
            try {
                Boolean rated = RepositoryRouter.getRatingRepository().rated(productId, userId);
                if (rated == null) {
                    emitter.onError(new NullPointerException());
                    return;
                }
                emitter.onSuccess(rated);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
