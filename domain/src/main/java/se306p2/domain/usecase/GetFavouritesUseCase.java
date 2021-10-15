package se306p2.domain.usecase;

import java.util.Set;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.IGetFavouritesUseCase;

public class GetFavouritesUseCase implements IGetFavouritesUseCase {
    public Single<Set<String>> getFavourites() {
        return Single.create(emitter -> {
            Thread thread = new Thread(() -> {
                try {
                    Set<String> favourites = RepositoryRouter.getUserRepository().favourites();
                    if (favourites == null) {
                        emitter.onError(new NullPointerException());
                        return;
                    }
                    emitter.onSuccess(favourites);
                } catch (Exception e) {
                    emitter.onError(e);
                }
            });
            thread.start();
        });
    }
}
