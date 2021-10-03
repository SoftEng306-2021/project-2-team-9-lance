package se306p2.domain.usecase;

public class GetProductsByFilterUseCase {
    List<Product> getProductsByFilter(String categoryID, String brandID, BigDecimal min, BigDecimal max);
}
