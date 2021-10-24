package se306p2.domain.interfaces.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.entity.IBrand;

/**
 * Interface for the GetBrandsUseCase.
 */
public interface IGetBrandsUseCase {
    Single<List<IBrand>> getBrands(String categoryId);
}
