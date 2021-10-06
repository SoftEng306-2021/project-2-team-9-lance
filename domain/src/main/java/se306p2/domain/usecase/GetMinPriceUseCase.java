package se306p2.domain.usecase;

import java.math.BigDecimal;

import se306p2.domain.RepositoryRouter;

public class GetMinPriceUseCase {
    public BigDecimal getMinPrice(String categoryId) {
        return RepositoryRouter.getCategoryRepository().getMinPrice(categoryId);
    }
}
