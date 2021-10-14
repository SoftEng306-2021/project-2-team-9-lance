package se306p2.domain.interfaces.usecase;

import java.util.Set;

import io.reactivex.rxjava3.core.Single;

public interface IGetFavouritesUseCase {
    Single<Set<String>> getFavourites();
}
