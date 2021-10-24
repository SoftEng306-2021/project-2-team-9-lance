package se306p2.domain.usecase;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.ISearchAndFilterProductsUseCase;

/**
 * This class is used to search and filter products.
 */
public class SearchAndFilterProductsUseCase implements ISearchAndFilterProductsUseCase {
    public Single<List<IProduct>> searchAndFilterProducts(String term, String brandId, BigDecimal min, BigDecimal max) {
        return Single.create(emitter -> {
            try {
                List<IProduct> products = RepositoryRouter.getProductRepository().getProductsBySearchAndFilter(term, brandId, min, max);
                if (products == null) {
                    emitter.onError(new NullPointerException());
                    return;
                }
                emitter.onSuccess(products);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
