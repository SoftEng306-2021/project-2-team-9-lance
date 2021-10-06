package se306p2.domain.usecase;

import java.util.List;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProductVersion;

public class GetProductVersionsUseCase {
    public List<IProductVersion> getProductVersions(String productId) {
        return RepositoryRouter.getProductRepository().getProductVersions(productId);
    }
}
