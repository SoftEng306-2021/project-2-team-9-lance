package se306p2.domain.interfaces.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.ICategory;

/**
 * Interface for the Category Repository
 */
public interface ICategoryRepository {

    /**
     * Get all categories
     * @return list of category
     */
    List<ICategory> getCategories();
    /**
     * Get a category by id
     * @param id
     * @return category
     */
    ICategory getCategoryById(String id);
    /**
     * Get Max Price of all products in a category
     * @param name
     * @return price
     */
    BigDecimal getMaxPrice(String categoryId);
    /**
     * Get Min Price of all products in a category
     * @param name
     * @return price
     */
    BigDecimal getMinPrice(String categoryId);

}
