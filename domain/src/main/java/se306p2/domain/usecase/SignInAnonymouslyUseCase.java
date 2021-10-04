package se306p2.domain.usecase;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.ISignInAnonymouslyUseCase;

public class SignInAnonymouslyUseCase implements ISignInAnonymouslyUseCase {
    public String signInAnonymously() {
        return RepositoryRouter.getUserRepository().signInAnonymously();
    }
}
