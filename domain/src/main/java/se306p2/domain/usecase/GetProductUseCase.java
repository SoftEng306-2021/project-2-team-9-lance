package se306p2.domain.usecase;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;

public class GetProductUseCase {
    public IProduct getProduct(String productID) {
        return RepositoryRouter.getProductRepository().getProductByID(productID);
    }
}
