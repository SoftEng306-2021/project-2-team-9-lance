package se306p2.domain.usecase;

import java.math.BigDecimal;

import se306p2.domain.RepositoryRouter;

public class GetMaxPriceUseCase {
    public BigDecimal getMaxPrice(String categoryID) {
        return RepositoryRouter.getCategoryRepository().getMaxPrice(categoryID);
    }
}
