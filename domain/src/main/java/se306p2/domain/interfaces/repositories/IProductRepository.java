package se306p2.domain.interfaces.repositories;

import android.util.Pair;

import java.math.BigDecimal;
import java.util.List;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.IBrand;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.entity.IProductVersion;

/**
 * Interface for the Product Repository
 */
public interface IProductRepository {

    /**
     * Get all products
     * @return list of products
     */
    List<IProduct> getProducts();

    /**
     * Get product by id
     * @param id
     * @return product
     */
    IProduct getProductById(String id);

    /**
     * Get products in a category
     * @param id
     * @return list of products
     */
    List<IProduct> getProductsByCategoryId(String id);

    /**
     * Get products in a category
     * @param category
     * @return list of products
     */
    List<IProduct> getProductsByCategory(ICategory category);

    /**
     * Get products by searching only
     * @param searchTerm
     * @return search results
     */
    Pair<List<IProduct>, List<IBrand>> getProductsBySearch(String searchTerm);

    /**
     * Get products by search and filtering
     * @param searchTerm
     * @param brandId
     * @param min
     * @param max
     * @return search results
     */
    List<IProduct> getProductsBySearchAndFilter(String searchTerm, String brandId, BigDecimal min, BigDecimal max);

    /**
     * Get products by filtering
     * @param categoryId
     * @param brandId
     * @param min
     * @param max
     * @return filtered products
     */
    List<IProduct> getProductsByFilter(String categoryId, String brandId, BigDecimal min, BigDecimal max);

    /**
     * Get product versions of a single product
     * @param productId
     * @return list of product versions
     */
    List<IProductVersion> getProductVersions(String productId);

    /**
     * Get benefits list of a single product
     * @param productId
     * @return list of benefits
     */
    List<IBenefit> getBenefits(String productId);

    /**
     * Get a list of featured products
     * @return list of featured products
     */
    List<IProduct> getFeaturedProducts();

    /**
     * Get a list of auto complete results from search term
     * @param searchTerm
     * @return list of auto complete results
     */
    List<String> searchAutoComplete(String searchTerm);


}
