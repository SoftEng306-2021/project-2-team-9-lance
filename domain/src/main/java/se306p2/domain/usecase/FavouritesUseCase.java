package se306p2.domain.usecase;

import java.util.Set;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.IFavouritesUseCase;

public class FavouritesUseCase implements IFavouritesUseCase {
    public Single<Set<String>> favourite(String productId) {
        return Single.create(emitter -> {
            try {
                Set<String> favourites = RepositoryRouter.getUserRepository().favourite(productId);
                if (favourites == null) {
                    emitter.onError(new NullPointerException());
                    return;
                }
                emitter.onSuccess(favourites);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
