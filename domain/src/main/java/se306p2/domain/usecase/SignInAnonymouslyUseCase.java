package se306p2.domain.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.ISignInAnonymouslyUseCase;

public class SignInAnonymouslyUseCase implements ISignInAnonymouslyUseCase {
    public Single<String> signInAnonymously() {
        return Single.create(emitter -> {
            try {
                String id = RepositoryRouter.getUserRepository().signInAnonymously();
                if (id == null) {
                    emitter.onError(new NullPointerException());
                    return;
                }
                emitter.onSuccess(id);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
