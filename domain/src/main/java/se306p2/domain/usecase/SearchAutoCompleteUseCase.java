package se306p2.domain.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.ISearchAutoCompleteUseCase;

/**
 * This class is used to search for auto complete suggestions.
 */
public class SearchAutoCompleteUseCase implements ISearchAutoCompleteUseCase {
    public Single<List<String>> searchAutoComplete(String searchTerm) {
        return Single.create(emitter -> {
            try {
                List<String> search = RepositoryRouter.getProductRepository().searchAutoComplete(searchTerm);
                if (search == null) {
                    emitter.onError(new NullPointerException());
                    return;
                }
                emitter.onSuccess(search);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
