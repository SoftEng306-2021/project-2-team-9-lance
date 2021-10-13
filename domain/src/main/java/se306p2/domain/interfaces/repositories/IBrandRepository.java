package se306p2.domain.interfaces.repositories;

import java.util.List;

import se306p2.domain.interfaces.entity.IBrand;

public interface IBrandRepository {

    List<IBrand> getBrands();
    List<IBrand> getBrands(String categoryId);

}
