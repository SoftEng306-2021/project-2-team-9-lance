package se306p2.domain.interfaces.usecase;

import io.reactivex.rxjava3.core.Single;

/**
 * This interface defines the contract for the sign in anonymously use case.
 */
public interface ISignInAnonymouslyUseCase {
    Single<String> signInAnonymously();
}
