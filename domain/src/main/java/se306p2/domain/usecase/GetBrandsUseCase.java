package se306p2.domain.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IBrand;
import se306p2.domain.interfaces.usecase.IGetBrandsUseCase;

/**
 * This class is the implementation of the IGetBrandsUseCase interface.
 */
public class GetBrandsUseCase implements IGetBrandsUseCase {
    public Single<List<IBrand>> getBrands(String categoryId) {
        return Single.create(emitter -> {
            try {
                List<IBrand> brands = RepositoryRouter.getBrandRepository().getBrands(categoryId);
                if (brands == null) {
                    emitter.onError(new NullPointerException());
                    return;
                }
                emitter.onSuccess(brands);
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(e);
            }
        });
    }
}
