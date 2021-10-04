package se306p2.domain.interfaces.usecase;

import java.math.BigDecimal;

public interface IGetMaxPriceUseCase {
    BigDecimal getMaxPrice(String categoryID);
}
