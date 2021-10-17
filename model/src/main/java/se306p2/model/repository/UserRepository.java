package se306p2.model.repository;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import se306p2.domain.interfaces.repositories.IUserRepository;

public class UserRepository implements IUserRepository {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private static UserRepository instance;

    private UserRepository(FirebaseFirestore firestore, FirebaseAuth auth) {
        this.db = firestore;
        this.mAuth = auth;
    }

    public static IUserRepository getInstance() {
        if (instance == null) {
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            instance = new UserRepository(firestore, auth);
        }
        return instance;
    }

    public String getCurrentUserId() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            return null;
        }
        return currentUser.getUid();
    }

    public String signInAnonymously() {
        try {
            AuthResult result = Tasks.await(mAuth.signInAnonymously());
            return result.getUser().getUid();
        } catch (ExecutionException | InterruptedException | IllegalStateException e) {

            e.printStackTrace();
            return null;
        }
    }

    public Set<String> favourites() {
        String userId = getCurrentUserId();
        if (userId == null) {
            return null;
        }

        try {
            DocumentSnapshot snapshot = Tasks.await(db.collection("user").document(userId).get());
            List<DocumentReference> favouriteList = (List<DocumentReference>) snapshot.get("favourites");

            return new HashSet<>(favouriteList.stream().map(s -> s.getId()).collect(Collectors.toList()));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<String> favourite(String productId) {
        String userId = getCurrentUserId();
        if (userId == null) {
            return null;
        }

        try {
            DocumentSnapshot snapshot = Tasks.await(db.collection("user").document(userId).get());
            List<DocumentReference> favouriteList = (List<DocumentReference>) snapshot.get("favourites");

            Set<DocumentReference> hashSet = new HashSet<>(favouriteList);

            DocumentReference productRef = db.collection("product").document(productId);
            if (hashSet.contains(productRef)) {
                hashSet.add(productRef);
            }else {
                hashSet.remove(productRef);
            }

            Tasks.await(db.collection("user")
                    .document(userId)
                    .update("favourites", hashSet.toArray())
            );
            return favourites();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
