package se306p2.domain.usecase;

import java.util.List;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.domain.interfaces.usecase.IGetProductVersionsUseCase;

public class GetProductVersionsUseCase implements IGetProductVersionsUseCase {
    public List<IProductVersion> getProductVersions(String productId) {
        return RepositoryRouter.getProductRepository().getProductVersions(productId);
    }
}
