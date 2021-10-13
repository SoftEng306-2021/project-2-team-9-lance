package se306p2.domain.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.IGetCurrentUserIdUseCase;

public class GetCurrentUserIdUseCase implements IGetCurrentUserIdUseCase {
    public Single<String> getCurrentUserId() {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(RepositoryRouter.getUserRepository().getCurrentUserId());
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
