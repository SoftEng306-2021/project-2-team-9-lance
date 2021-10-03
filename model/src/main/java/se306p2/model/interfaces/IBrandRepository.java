package se306p2.model.interfaces;

import java.util.List;

import se306p2.domain.interfaces.entity.IBrand;

public interface IBrandRepository {

    IBrandRepository getInstance();
    List<IBrand> getBrands();
    List<IBrand> getBrands(String categoryID);
}
