package se306p2.domain.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IBrand;
import se306p2.domain.interfaces.usecase.IGetBrandsUseCase;

public class GetBrandsUseCase implements IGetBrandsUseCase {
    public Single<List<IBrand>> getBrands(String categoryId) {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(RepositoryRouter.getBrandRepository().getBrands(categoryId));
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
