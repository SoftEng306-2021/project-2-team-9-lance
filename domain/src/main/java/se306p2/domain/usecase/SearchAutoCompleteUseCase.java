package se306p2.domain.usecase;

import java.util.List;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.ISearchAutoCompleteUseCase;

public class SearchAutoCompleteUseCase implements ISearchAutoCompleteUseCase {
    public List<String> searchAutoComplete(String searchTerm) {
        return RepositoryRouter.getProductRepository().searchAutoComplete(searchTerm);
    }
}
