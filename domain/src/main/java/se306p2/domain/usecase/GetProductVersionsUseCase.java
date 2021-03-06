package se306p2.domain.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.domain.interfaces.usecase.IGetProductVersionsUseCase;

/**
 * This class is used to get the product versions of a single product.
 */
public class GetProductVersionsUseCase implements IGetProductVersionsUseCase {
    public Single<List<IProductVersion>> getProductVersions(String productId) {
        return Single.create(emitter -> {
            try {
                List<IProductVersion> productVersions = RepositoryRouter.getProductRepository().getProductVersions(productId);
                if (productVersions == null) {
                    emitter.onError(new NullPointerException());
                    return;
                }
                emitter.onSuccess(productVersions);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
