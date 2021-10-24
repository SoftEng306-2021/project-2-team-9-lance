package se306p2.domain.interfaces.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.entity.IProduct;

/**
 * This interface defines the methods that a GetFeaturedProductsUseCase must implement.
 */
public interface IGetFeaturedProductsUseCase {
    Single<List<IProduct>> getFeaturedProducts();
}
