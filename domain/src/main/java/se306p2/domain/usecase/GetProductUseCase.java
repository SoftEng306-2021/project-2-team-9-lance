package se306p2.domain.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetProductUseCase;

public class GetProductUseCase implements IGetProductUseCase {
    public Single<IProduct> getProduct(String productId) {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(RepositoryRouter.getProductRepository().getProductById(productId));
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
