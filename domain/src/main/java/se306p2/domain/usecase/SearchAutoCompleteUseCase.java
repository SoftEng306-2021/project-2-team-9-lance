package se306p2.domain.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.ISearchAutoCompleteUseCase;

public class SearchAutoCompleteUseCase implements ISearchAutoCompleteUseCase {
    public Single<List<String>> searchAutoComplete(String searchTerm) {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(RepositoryRouter.getProductRepository().searchAutoComplete(searchTerm));
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
