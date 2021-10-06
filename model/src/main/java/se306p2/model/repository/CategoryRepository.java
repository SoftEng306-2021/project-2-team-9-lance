package se306p2.model.repository;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.repositories.ICategoryRepository;
import se306p2.model.transformers.CategoryTransformer;

public class CategoryRepository implements ICategoryRepository {

    private FirebaseFirestore db;
    private CategoryRepository instance;

    @Override
    public ICategoryRepository getInstance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ICategory> getCategories() {
        QuerySnapshot snapshot = db.collection("category").get().getResult();

        List<ICategory> categories = new ArrayList<>();

        for(DocumentSnapshot ds : snapshot.getDocuments()) {
            categories.add(CategoryTransformer.unpack(ds.getId(),ds.getData()));
        }
        return categories;
    }

    @Override
    public ICategory getCategoryByID(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal getMaxPrice(String categoryID) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal getMinPrice(String categoryID) {
        throw new UnsupportedOperationException();
    }
}
