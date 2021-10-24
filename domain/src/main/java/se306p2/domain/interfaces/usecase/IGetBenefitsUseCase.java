package se306p2.domain.interfaces.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.entity.IBenefit;

/**
 * This interface is used to get the benefits of a user.
 */
public interface IGetBenefitsUseCase {
    Single<List<IBenefit>> getBenefits(String productId);
}
