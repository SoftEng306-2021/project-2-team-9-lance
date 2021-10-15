package se306p2.domain.interfaces.usecase;

import java.math.BigDecimal;

import io.reactivex.rxjava3.core.Single;

public interface IGetMinPriceUseCase {
    Single<BigDecimal> getMinPrice(String categoryId);
}
