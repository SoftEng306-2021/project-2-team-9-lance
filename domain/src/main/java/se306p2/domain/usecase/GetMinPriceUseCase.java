package se306p2.domain.usecase;

import java.math.BigDecimal;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.IGetMinPriceUseCase;

public class GetMinPriceUseCase implements IGetMinPriceUseCase {
    public Single<BigDecimal> getMinPrice(String categoryId) {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(RepositoryRouter.getCategoryRepository().getMinPrice(categoryId));
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
