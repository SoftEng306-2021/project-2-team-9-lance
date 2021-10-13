package se306p2.domain.usecase;

import java.util.List;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.ISearchProductsUseCase;

public class SearchProductsUseCase implements ISearchProductsUseCase {
    public List<IProduct> searchProducts(String term) {
        return RepositoryRouter.getProductRepository().getProductsBySearch(term);
    }
}
