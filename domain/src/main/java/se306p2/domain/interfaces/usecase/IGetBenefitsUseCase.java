package se306p2.domain.interfaces.usecase;

import java.util.List;

import se306p2.domain.interfaces.entity.IBenefit;

public interface IGetBenefitsUseCase {
    List<IBenefit> getBenefits(String productID);
}
