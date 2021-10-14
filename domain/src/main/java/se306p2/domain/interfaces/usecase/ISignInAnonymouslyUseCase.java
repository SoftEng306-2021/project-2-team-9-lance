package se306p2.domain.interfaces.usecase;

import io.reactivex.rxjava3.core.Single;

public interface ISignInAnonymouslyUseCase {
    Single<String> signInAnonymously();
}
