package se306p2.domain.interfaces.usecase;

import java.util.Set;

import io.reactivex.rxjava3.core.Single;

/**
 * This interface defines the methods that a GetFavouritesUseCase must implement.
 */
public interface IGetFavouritesUseCase {
    Single<Set<String>> getFavourites();
}
