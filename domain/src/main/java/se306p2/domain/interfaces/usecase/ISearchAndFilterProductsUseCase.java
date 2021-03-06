package se306p2.domain.interfaces.usecase;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.entity.IProduct;

/**
 * Interface for the Search and Filter Products Use Case
 */
public interface ISearchAndFilterProductsUseCase {
    Single<List<IProduct>> searchAndFilterProducts(String term, String brandId, BigDecimal min, BigDecimal max);
}
