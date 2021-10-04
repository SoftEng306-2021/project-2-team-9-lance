package se306p2.domain.interfaces.repositories;

import java.math.BigDecimal;
import java.util.List;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.entity.IProductVersion;

public interface IProductRepository {


    List<IProduct> getProducts();
    IProduct getProductByID(String id);
    List<IProduct> getProductsByCategoryID(String id);
    List<IProduct> getProductsByCategory(ICategory category);
    List<IProduct> getProductsBySearch(String searchTerm);
    List<IProduct> getProductsByFilter(String categoryID, String brandID, BigDecimal min,BigDecimal max);
    List<IProductVersion> getProductVersions(String productID);
    List<IBenefit> getBenefits(String productID);
    List<IProduct> getFeaturedProducts();
    List<String> searchAutoComplete(String searchTerm);


}
