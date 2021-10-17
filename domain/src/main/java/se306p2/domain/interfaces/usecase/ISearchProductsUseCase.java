package se306p2.domain.interfaces.usecase;

import android.util.Pair;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.entity.IBrand;
import se306p2.domain.interfaces.entity.IProduct;

public interface ISearchProductsUseCase {
    Single<Pair<List<IProduct>, List<IBrand>>> searchProducts(String term);
}
