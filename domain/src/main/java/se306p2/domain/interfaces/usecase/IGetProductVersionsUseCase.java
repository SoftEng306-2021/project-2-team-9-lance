package se306p2.domain.interfaces.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.entity.IProductVersion;

/**
 * This interface defines the methods that a product version use case must implement.
 */
public interface IGetProductVersionsUseCase {
    Single<List<IProductVersion>> getProductVersions(String productId);
}
