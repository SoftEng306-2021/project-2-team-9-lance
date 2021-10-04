package se306p2.domain.usecase;

import java.util.List;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;

public class SearchProductsUseCase {
    public List<IProduct> searchProducts(String term) {
        return RepositoryRouter.getProductRepository().getProductsBySearch(term);
    }
}
