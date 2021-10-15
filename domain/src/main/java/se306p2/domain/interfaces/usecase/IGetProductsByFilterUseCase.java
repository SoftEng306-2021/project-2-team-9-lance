package se306p2.domain.interfaces.usecase;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.entity.IProduct;

public interface IGetProductsByFilterUseCase {
    Single<List<IProduct>> getProductsByFilter(String categoryId, String brandId, BigDecimal min, BigDecimal max);
}
