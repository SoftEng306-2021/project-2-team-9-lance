package se306p2.domain.usecase;

import android.util.Pair;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IBrand;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.ISearchProductsUseCase;

/**
 * This class is used to search for products.
 */
public class SearchProductsUseCase implements ISearchProductsUseCase {
    public Single<Pair<List<IProduct>, List<IBrand>>> searchProducts(String term) {
        return Single.create(emitter -> {
            try {
                Pair<List<IProduct>, List<IBrand>> products = RepositoryRouter.getProductRepository().getProductsBySearch(term);
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
