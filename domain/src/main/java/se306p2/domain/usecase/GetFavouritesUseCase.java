package se306p2.domain.usecase;

import java.util.Set;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.IGetFavouritesUseCase;

public class GetFavouritesUseCase implements IGetFavouritesUseCase {
    public Set<String> getFavourites() {
        return RepositoryRouter.getUserRepository().favourites();
    }
}
