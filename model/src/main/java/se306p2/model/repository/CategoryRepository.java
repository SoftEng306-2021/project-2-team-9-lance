package se306p2.model.repository;

import com.google.android.gms.tasks.Tasks;
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
            instance = new CategoryRepository(firestore);
        }
        return instance;
    }

    /**
     * @see ICategoryRepository#getCategories()
     */
    public List<ICategory> getCategories() {
        List<ICategory> categories = new ArrayList<>();
        try {
            QuerySnapshot snapshot = Tasks.await(db.collection("category").orderBy("order").get());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                categories.add(CategoryTransformer.unpack(ds.getId(), ds.getData()));
            }
            return categories;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @see ICategoryRepository#getCategoryById(String categoryId)
     */
    public ICategory getCategoryById(String categoryId) {
        try {
            DocumentSnapshot snapshot = Tasks.await(db.collection("category").document(categoryId).get());

            if (!snapshot.exists()) {
                return null;
            }

            return CategoryTransformer.unpack(snapshot.getId(), snapshot.getData());
        } catch (ExecutionException | InterruptedException e) {
            return null;
        }
    }

    /**
     * @see ICategoryRepository#getMaxPrice(String categoryId)
     */
    public BigDecimal getMaxPrice(String categoryId) {
        try {
            DocumentReference docRef = db.collection("category").document(categoryId);

            QuerySnapshot snapshot = Tasks.await(db.collection("product").whereEqualTo("category", docRef).orderBy("price", Query.Direction.DESCENDING).limit(1).get());

            if (snapshot.size() != 1) {
                return null;
            }

            return new BigDecimal((double) snapshot.getDocuments().get(0).get("price"));
        } catch (ExecutionException | InterruptedException | NullPointerException e) {
            return null;
        }
    }

    /**
     * @see ICategoryRepository#getMinPrice(String categoryId)
     */
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
