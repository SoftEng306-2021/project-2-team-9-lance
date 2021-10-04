package se306p2.domain.interfaces.usecase;

import java.util.List;

import se306p2.domain.interfaces.entity.IProductVersion;

public interface IGetProductVersionsUseCase {
    List<IProductVersion> getProductVersions(String productID);
}
