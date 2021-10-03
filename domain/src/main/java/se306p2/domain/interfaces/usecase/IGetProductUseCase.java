package se306p2.domain.interfaces.usecase;

import se306p2.domain.interfaces.entity.IProduct;

public interface IGetProductUseCase {
    IProduct getProduct(String productID);
}
