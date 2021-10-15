package se306p2.domain.usecase;

import java.math.BigDecimal;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.IGetMaxPriceUseCase;

public class GetMaxPriceUseCase implements IGetMaxPriceUseCase {
    public Single<BigDecimal> getMaxPrice(String categoryId) {
        return Single.create(emitter -> {
            Thread thread = new Thread(() -> {
                try {
                    BigDecimal price = RepositoryRouter.getCategoryRepository().getMaxPrice(categoryId);
                    if (price == null) {
                        emitter.onError(new NullPointerException());
                        return;
                    }
                    emitter.onSuccess(price);
                } catch (Exception e) {
                    emitter.onError(e);
                }
            });
            thread.start();
        });
    }
}
