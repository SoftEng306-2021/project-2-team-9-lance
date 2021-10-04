package se306p2.domain.interfaces.usecase;

import java.util.List;

import se306p2.domain.interfaces.entity.ICategory;

public interface IGetCategoryDetailsUseCase {
    List<ICategory> getCategoryDetails();
}
