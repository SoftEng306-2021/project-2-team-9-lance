package se306p2.domain.usecase;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetProductUseCase;

public class GetProductUseCase implements IGetProductUseCase {
    public Single<IProduct> getProduct(String productId) {
        return Single.create(emitter -> {
            Thread thread = new Thread(() -> {
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
            thread.start();
        });
    }
}
