package se306p2.model.interfaces;

import java.util.Set;

public interface IUserRepository {

    IUserRepository getInstance();
    String getCurrentUserID();
    String signInAnonymously();
    Set<String> favourites();
    Set<String> favourite(String productID);

}