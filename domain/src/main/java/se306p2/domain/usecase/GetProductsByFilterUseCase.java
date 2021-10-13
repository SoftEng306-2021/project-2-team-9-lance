package se306p2.domain.usecase;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetProductsByFilterUseCase;

public class GetProductsByFilterUseCase implements IGetProductsByFilterUseCase {
    public Single<List<IProduct>> getProductsByFilter(String categoryId, String brandId, BigDecimal min, BigDecimal max) {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(RepositoryRouter.getProductRepository().getProductsByFilter(categoryId, brandId, min, max));
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
