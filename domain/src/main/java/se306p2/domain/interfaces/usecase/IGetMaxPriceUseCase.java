package se306p2.domain.interfaces.usecase;

import java.math.BigDecimal;

import io.reactivex.rxjava3.core.Single;

/**
 * This interface is used to get the maximum price of a product.
 */
public interface IGetMaxPriceUseCase {
    Single<BigDecimal> getMaxPrice(String categoryId);
}
