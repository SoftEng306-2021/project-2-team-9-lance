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
                List<IBenefit> benefits = RepositoryRouter.getProductRepository().getBenefits(productId);
                if (benefits == null) {
                    emitter.onError(new NullPointerException());
                    return;
                }
                emitter.onSuccess(benefits);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
