package se306p2.domain.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.ISearchProductsUseCase;

public class SearchProductsUseCase implements ISearchProductsUseCase {
    public Single<List<IProduct>> searchProducts(String term) {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(RepositoryRouter.getProductRepository().getProductsBySearch(term));
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
