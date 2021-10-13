package se306p2.domain.usecase;

import java.math.BigDecimal;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.usecase.IGetMinPriceUseCase;

public class GetMinPriceUseCase implements IGetMinPriceUseCase {
    public BigDecimal getMinPrice(String categoryId) {
        return RepositoryRouter.getCategoryRepository().getMinPrice(categoryId);
    }
}
