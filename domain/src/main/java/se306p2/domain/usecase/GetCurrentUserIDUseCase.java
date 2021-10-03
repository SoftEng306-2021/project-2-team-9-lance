package se306p2.domain.usecase;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.IGetCurrentUserIDUseCase;

public class GetCurrentUserIDUseCase implements IGetCurrentUserIDUseCase {
    public String getCurrentUserID() {
        return RepositoryRouter.getUserRepository().getCurrentUserID();
    }
}
