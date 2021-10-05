package se306p2.model.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Set;

import se306p2.domain.interfaces.repositories.IUserRepository;

public class UserRepository implements IUserRepository {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private UserRepository instance;


    @Override
    public IUserRepository getInstance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getCurrentUserID() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String signInAnonymously() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> favourites() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> favourite(String productID) {
        throw new UnsupportedOperationException();
    }
}
