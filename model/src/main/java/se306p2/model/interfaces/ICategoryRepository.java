package se306p2.model.interfaces;

import java.math.BigDecimal;
import java.util.List;

import se306p2.domain.interfaces.entity.ICategory;

public interface ICategoryRepository {

    ICategoryRepository getInstance();
    List<ICategory> getCategories();
    ICategory getCategoryByID(String id);
    BigDecimal getMaxPrice(String categoryID);
    BigDecimal getMinPrice(String categoryID);
}
