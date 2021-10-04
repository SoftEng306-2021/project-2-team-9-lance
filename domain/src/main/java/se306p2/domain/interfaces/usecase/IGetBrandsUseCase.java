package se306p2.domain.interfaces.usecase;

import java.util.List;

import se306p2.domain.interfaces.entity.IBrand;

public interface IGetBrandsUseCase {
    List<IBrand> getBrands(String categoryID);
}
