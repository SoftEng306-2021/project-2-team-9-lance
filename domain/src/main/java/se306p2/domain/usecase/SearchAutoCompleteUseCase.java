package se306p2.domain.usecase;

import java.util.List;

import se306p2.domain.RepositoryRouter;

public class SearchAutoCompleteUseCase {
    public List<String> searchAutoComplete(String searchTerm) {
        return RepositoryRouter.getProductRepository().searchAutoComplete(searchTerm);
    }
}
