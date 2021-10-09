package se306p2.model.repository;

import android.widget.ExpandableListAdapter;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.repositories.ICategoryRepository;
import se306p2.model.transformers.CategoryTransformer;

public class CategoryRepository implements ICategoryRepository {

    private FirebaseFirestore db;
    private static CategoryRepository instance;

    private CategoryRepository(FirebaseFirestore firestore) {
        this.db = firestore;
    }

    public static ICategoryRepository getInstance() {
        if (instance == null) {
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            instance = new UserRepository(firestore);
        }
        return instance;
    }

    public List<ICategory> getCategories() {
        List<ICategory> categories = new ArrayList<>();
        try {
            QuerySnapshot snapshot = Tasks.await(db.collection("category").get());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                categories.add(CategoryTransformer.unpack(ds.getId(), ds.getData()));
            }
            return categories;
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ICategory getCategoryById(String categoryId) {
        try {
            DocumentSnapshot snapshot = Tasks.await(db.collection("category").document(categoryId).get());

            if (!snapshot.exists()) {
                return null;
            }

            return CategoryTransformer.unpack(snapshot.getId(), snapshot.getData());
        } catch (ExecutionException | InterruptedException ex) {
            return null;
        }
    }

    public BigDecimal getMaxPrice(String categoryId) {
        try {
            DocumentReference docRef = db.collection("category").document(categoryId);

            QuerySnapshot snapshot = Tasks.await(db.collection("product").whereEqualTo("category", docRef).orderBy("price", Query.Direction.DESCENDING).limit(1).get());

            if (snapshot.size() != 1) {
                return null;
            }

            return new BigDecimal((double) snapshot.getDocuments().get(0).get("price"));
        } catch (ExecutionException | InterruptedException | NullPointerException ex) {
            return null;
        }
    }

    public BigDecimal getMinPrice(String categoryId) {
        try {
            DocumentReference docRef = db.collection("category").document(categoryId);

            QuerySnapshot snapshot = Tasks.await(db.collection("product").whereEqualTo("category", docRef).orderBy("price", Query.Direction.ASCENDING).limit(1).get());

            if (snapshot.size() != 1) {
                return null;
            }

            return new BigDecimal((double) snapshot.getDocuments().get(0).get("price"));
        } catch (ExecutionException | InterruptedException | NullPointerException e) {
            return null;
        }
    }
}
