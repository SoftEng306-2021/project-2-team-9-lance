package se306p2.model.repository;

import com.google.firebase.firestore.FirebaseFirestore;

import java.math.BigDecimal;
import java.util.List;

import se306p2.domain.interfaces.entity.ICategory;
import se306p2.model.interfaces.ICategoryRepository;

public class CategoryRepository implements ICategoryRepository {

    private FirebaseFirestore db;
    private CategoryRepository instance;

    @Override
    public ICategoryRepository getInstance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ICategory> getCategories() {
        throw new UnsupportedOperationException();
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
