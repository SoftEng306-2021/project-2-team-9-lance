package se306p2.domain.usecase;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.IGetCurrentUserIdUseCase;

public class GetCurrentUserIdUseCase implements IGetCurrentUserIdUseCase {
    public String getCurrentUserId() {
        return RepositoryRouter.getUserRepository().getCurrentUserId();
    }
}
