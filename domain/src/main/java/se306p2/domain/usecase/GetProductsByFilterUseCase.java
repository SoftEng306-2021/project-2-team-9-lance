package se306p2.domain.usecase;

import java.math.BigDecimal;
import java.util.List;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;

public class GetProductsByFilterUseCase {
    public List<IProduct> getProductsByFilter(String categoryID, String brandID, BigDecimal min, BigDecimal max) {

        return RepositoryRouter.getProductRepository().getProductsByFilter(categoryID,brandID,min ,max);

    }
}
