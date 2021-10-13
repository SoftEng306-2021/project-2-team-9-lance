package se306p2.domain.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetFeaturedProductsUseCase;

public class GetFeaturedProductsUseCase implements IGetFeaturedProductsUseCase {
    public Single<List<IProduct>> getFeaturedProducts() {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(RepositoryRouter.getProductRepository().getFeaturedProducts());
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
