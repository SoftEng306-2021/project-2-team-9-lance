package se306p2.domain.usecase;

import java.util.List;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.usecase.IGetCategoryDetailsUseCase;

public class GetCategoryDetailsUseCase implements IGetCategoryDetailsUseCase {
    public List<ICategory> getCategoryDetails() {
        return RepositoryRouter.getCategoryRepository().getCategories();
    }
}
