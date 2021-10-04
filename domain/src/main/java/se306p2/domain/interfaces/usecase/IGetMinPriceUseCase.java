package se306p2.domain.interfaces.usecase;

import java.math.BigDecimal;

public interface IGetMinPriceUseCase {
    BigDecimal getMinPrice(String categoryID);
}
