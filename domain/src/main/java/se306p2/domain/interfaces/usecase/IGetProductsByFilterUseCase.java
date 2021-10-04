package se306p2.domain.interfaces.usecase;

import java.math.BigDecimal;
import java.util.List;

import se306p2.domain.interfaces.entity.IProduct;

public interface IGetProductsByFilterUseCase {
    List<IProduct> getProductsByFilter(String categoryID, String brandID, BigDecimal min, BigDecimal max);
}
