package se306p2.domain.interfaces.repositories;

import java.util.Set;

public interface IUserRepository {

    String getCurrentUserId();
    String signInAnonymously();
    Set<String> favourites();
    Set<String> favourite(String productId);

}
