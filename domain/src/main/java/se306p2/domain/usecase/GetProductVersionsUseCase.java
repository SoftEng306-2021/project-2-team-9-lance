package se306p2.domain.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.domain.interfaces.usecase.IGetProductVersionsUseCase;

public class GetProductVersionsUseCase implements IGetProductVersionsUseCase {
    public Single<List<IProductVersion>> getProductVersions(String productId) {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(RepositoryRouter.getProductRepository().getProductVersions(productId));
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
