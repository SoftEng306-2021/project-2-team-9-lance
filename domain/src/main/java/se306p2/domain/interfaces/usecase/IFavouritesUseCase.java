package se306p2.domain.interfaces.usecase;

import java.util.Set;

import io.reactivex.rxjava3.core.Single;

/**
 * This interface defines the behaviour of the favourites use case.
 */
public interface IFavouritesUseCase {
    Single<Set<String>> favourite(String productId);
}
