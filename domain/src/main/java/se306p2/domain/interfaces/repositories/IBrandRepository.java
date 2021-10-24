package se306p2.domain.interfaces.repositories;

import java.util.List;

import se306p2.domain.interfaces.entity.IBrand;

/**
 * Interface for the Brand repository.
 */
public interface IBrandRepository {

    /**
     * Get all brands.
     * @return list of brands
     */
    List<IBrand> getBrands();

    /**
     * Get a brands available from products in a category.
     * @param categoryId
     * @return list of brands
     */
    List<IBrand> getBrands(String categoryId);

}
