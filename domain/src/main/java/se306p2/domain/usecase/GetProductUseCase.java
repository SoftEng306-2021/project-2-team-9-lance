package se306p2.domain.usecase;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetProductUseCase;

public class GetProductUseCase implements IGetProductUseCase {
    public IProduct getProduct(String productId) {
        return RepositoryRouter.getProductRepository().getProductById(productId);
    }
}
