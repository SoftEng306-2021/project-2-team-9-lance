package se306p2.domain.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.usecase.IGetBenefitsUseCase;

public class GetBenefitsUseCase implements IGetBenefitsUseCase {
    public Single<List<IBenefit>> getBenefits(String productId) {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(RepositoryRouter.getProductRepository().getBenefits(productId));
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
