package se306p2.domain.usecase;

import java.math.BigDecimal;
import java.util.List;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetProductsByFilterUseCase;

public class GetProductsByFilterUseCase implements IGetProductsByFilterUseCase {
    public List<IProduct> getProductsByFilter(String categoryId, String brandId, BigDecimal min, BigDecimal max) {

        return RepositoryRouter.getProductRepository().getProductsByFilter(categoryId,brandId,min ,max);

    }
}
