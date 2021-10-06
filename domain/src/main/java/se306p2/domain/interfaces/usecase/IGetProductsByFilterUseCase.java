package se306p2.domain.interfaces.usecase;

import java.math.BigDecimal;
import java.util.List;

import se306p2.domain.interfaces.entity.IProduct;

public interface IGetProductsByFilterUseCase {
    List<IProduct> getProductsByFilter(String categoryId, String brandId, BigDecimal min, BigDecimal max);
}
