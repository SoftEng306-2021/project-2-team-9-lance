package se306p2.domain.usecase;

import java.math.BigDecimal;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.IGetMaxPriceUseCase;

public class GetMaxPriceUseCase implements IGetMaxPriceUseCase {
    public BigDecimal getMaxPrice(String categoryId) {
        return RepositoryRouter.getCategoryRepository().getMaxPrice(categoryId);
    }
}
