package se306p2.domain.usecase;

import java.util.List;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.usecase.IGetBenefitsUseCase;

public class GetBenefitsUseCase implements IGetBenefitsUseCase {
    public List<IBenefit> getBenefits(String productId) {
        return RepositoryRouter.getProductRepository().getBenefits(productId);
    }
}
