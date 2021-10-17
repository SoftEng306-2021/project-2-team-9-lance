package se306p2.model.repository;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.IBrand;
import se306p2.domain.interfaces.repositories.IBrandRepository;
import se306p2.model.transformers.BrandTransformer;


public class BrandRepository implements IBrandRepository {
    private FirebaseFirestore db;
    private static BrandRepository instance;

    private BrandRepository(FirebaseFirestore firestore) {
        this.db = firestore;
    }


    public static IBrandRepository getInstance() {
        if (instance == null) {
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            instance = new BrandRepository(firestore);
        }
        return instance;
    }

    public List<IBrand> getBrands() {
        List<IBrand> brands = new ArrayList<>();
        try {
            QuerySnapshot snapshot = Tasks.await(db.collection("brand").orderBy("name").get());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                brands.add(BrandTransformer.unpack(ds.getId(), ds.getData()));
            }
            return brands;
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<IBrand> getBrands(String categoryId) {
        List<IBrand> brands = new ArrayList<>();
        try {
            DocumentReference category = db.collection("category").document(categoryId);
            QuerySnapshot snapshot = Tasks.await(db.collection("product").whereEqualTo("category", category).get());

            Set<DocumentReference> uniqueBrands = new LinkedHashSet<>();

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                if (ds.contains("brand")) {
                    uniqueBrands.add((DocumentReference) ds.get("brand"));
                }
            }

            for (DocumentReference brand : uniqueBrands) {
                DocumentSnapshot ds = Tasks.await(brand.get());
                brands.add(BrandTransformer.unpack(ds.getId(), ds.getData()));
            }

            return brands;
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
