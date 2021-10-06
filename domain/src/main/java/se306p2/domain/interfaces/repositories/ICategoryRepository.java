package se306p2.domain.interfaces.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.ICategory;

public interface ICategoryRepository {

    ICategoryRepository getInstance();
    List<ICategory> getCategories();
    ICategory getCategoryById(String id);
    BigDecimal getMaxPrice(String categoryId);
    BigDecimal getMinPrice(String categoryId);
}
