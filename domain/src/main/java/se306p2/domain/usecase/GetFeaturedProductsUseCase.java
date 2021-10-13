package se306p2.domain.usecase;

import java.util.List;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetFeaturedProductsUseCase;

public class GetFeaturedProductsUseCase implements IGetFeaturedProductsUseCase {
    public List<IProduct> getFeaturedProducts() {
        return RepositoryRouter.getProductRepository().getFeaturedProducts();
    }
}
