package se306p2.domain.usecase;

import java.util.List;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IBrand;
import se306p2.domain.interfaces.usecase.IGetBrandsUseCase;

public class GetBrandsUseCase implements IGetBrandsUseCase {
    public List<IBrand> getBrands(String categoryID) {
        return RepositoryRouter.getBrandRepository().getBrands(categoryID);
    }
}
