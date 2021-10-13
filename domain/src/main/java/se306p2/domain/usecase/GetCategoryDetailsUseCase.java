package se306p2.domain.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.usecase.IGetCategoryDetailsUseCase;

public class GetCategoryDetailsUseCase implements IGetCategoryDetailsUseCase {
    public Single<List<ICategory>> getCategoryDetails() {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(RepositoryRouter.getCategoryRepository().getCategories());
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
