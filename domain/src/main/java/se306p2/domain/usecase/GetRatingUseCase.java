package se306p2.domain.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IRating;
import se306p2.domain.interfaces.usecase.IGetRatingUseCase;

public class GetRatingUseCase implements IGetRatingUseCase {
    public Single<IRating> getRating(String productId) {
        return Single.create(emitter -> {
            Thread thread = new Thread(() -> {
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
            thread.start();
        });
    }
}
