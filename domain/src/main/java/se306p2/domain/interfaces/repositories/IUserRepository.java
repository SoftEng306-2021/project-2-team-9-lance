package se306p2.domain.interfaces.repositories;

import java.util.Set;

/**
 * Interface for the User Repository
 */
public interface IUserRepository {

    /**
     * Get Current User Id
     */
    String getCurrentUserId();

    /**
     * Signin Anonymously using firebase
     * @return list of favourites
     */
    String signInAnonymously();

    /**
     * Favourites
     * @return list of favourites
     */
    Set<String> favourites();

    /**
     * Add to favourites
     * @param productId
     * @return list of favourites
     */
    Set<String> favourite(String productId);

}
