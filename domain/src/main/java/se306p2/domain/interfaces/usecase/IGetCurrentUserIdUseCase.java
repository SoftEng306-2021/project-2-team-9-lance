package se306p2.domain.interfaces.usecase;

import io.reactivex.rxjava3.core.Single;

/**
 * This interface is used to get the current user id.
 */
public interface IGetCurrentUserIdUseCase {
    Single<String> getCurrentUserId();
}
