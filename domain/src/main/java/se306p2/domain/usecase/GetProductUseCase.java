package se306p2.domain.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetProductUseCase;

/**
 * This class is used to get a product from the repository.
 */
public class GetProductUseCase implements IGetProductUseCase {
    public Single<IProduct> getProduct(String productId) {
        return Single.create(emitter -> {
            try {
                IProduct product = RepositoryRouter.getProductRepository().getProductById(productId);
                if (product == null) {
                    emitter.onError(new NullPointerException());
                    return;
                }
                emitter.onSuccess(product);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
