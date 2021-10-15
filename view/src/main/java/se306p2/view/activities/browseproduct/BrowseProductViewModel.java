package se306p2.view.activities.browseproduct;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import se306p2.domain.interfaces.entity.IBrand;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetBrandsUseCase;
import se306p2.domain.interfaces.usecase.IGetMaxPriceUseCase;
import se306p2.domain.interfaces.usecase.IGetMinPriceUseCase;
import se306p2.domain.interfaces.usecase.IGetProductsByFilterUseCase;
import se306p2.domain.usecase.GetBrandsUseCase;
import se306p2.domain.usecase.GetMaxPriceUseCase;
import se306p2.domain.usecase.GetMinPriceUseCase;
import se306p2.domain.usecase.GetProductsByFilterUseCase;
import se306p2.model.entities.Brand;
import se306p2.view.common.placeholders.PlaceholderGenerator;

public class BrowseProductViewModel extends ViewModel {
    /**
     * Two way bound with the price bracket spinner in browse_products_view.xml
     * observablePriceBracketsList determines the list of options inside the spinner
     * observablePriceBracketIndexSelected determines the currently selected item in the spinner.
     * No manual onChange updating is required to or from the UI.
     * When observablePriceBracketsList is changed, the list of options in the spinner in the UI
     * is updated automatically.
     * Likewise, when the user has selected an option, observablePriceBracketIndexSelected is
     * updated automatically to reflect the user's selection.
     */
    public final ObservableArrayList<String> observablePriceBracketsList = new ObservableArrayList<>();
    public final ObservableInt observablePriceBracketIndexSelected = new ObservableInt();
    public final ObservableArrayList<String> observableBrandsList = new ObservableArrayList<>();
    public final ObservableInt observableBrandIndexSelected = new ObservableInt();

    IGetMaxPriceUseCase getMaxPriceUseCase = new GetMaxPriceUseCase();
    IGetMinPriceUseCase getMinPriceUseCase = new GetMinPriceUseCase();
    IGetProductsByFilterUseCase getProductsByFilterUseCase = new GetProductsByFilterUseCase();
    IGetBrandsUseCase getBrandsUseCase = new GetBrandsUseCase();


    private CompositeDisposable disposables = new CompositeDisposable();
    private String categoryId;
    private List<IBrand> brands = new ArrayList<>();

    private MutableLiveData<List<IProduct>> products = new MutableLiveData<>();

    public void init(String categoryId) {
        this.categoryId = categoryId;

        loadBrands();
        loadPriceBrackets();
        loadProducts();
    }

    public ObservableArrayList<String> getObservableBrandsList() {
        return observableBrandsList;
    }


    public void loadProducts() {

        //TODO **DO NOT DELETE**
        //TODO uncomment the following when backend has data.
//        Single<List<IProduct>> productsSingle = getProductsByFilterUseCase.getProductsByFilter(
//                categoryId,
//                brands.get(observableBrandIndexSelected.get()).getId(),
//                null, //TODO update this with logic when decisions on this functionality has been finalised
//                null //TODO update this with logic when decisions on this functionality has been finalised
//                );
//        this.disposables.add(productsSingle.subscribeWith(new DisposableSingleObserver<List<IProduct>>() {
//            @Override
//            public void onSuccess(List<IProduct> productList) {
//                products.postValue(productList);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//                // Handle error
//            }
//        }));

        //TODO delete the following when backend has data.
        products.postValue(PlaceholderGenerator.getProducts());
    }

    private void loadBrands() {

        //TODO **DO NOT DELETE**
        //TODO uncomment the following when backend has data.
//        Single<List<IBrand>> brandsSingle = getBrandsUseCase.getBrands(categoryId);
//        this.disposables.add(brandsSingle.subscribeWith(new DisposableSingleObserver<List<IBrand>>() {
//            @Override
//            public void onSuccess(List<IBrand> brandList) {
//                brands.clear();
//                brands.addAll(brandList);
//                observableBrandsList.clear();
//                observableBrandsList.addAll(
//                        brandList
//                                .stream()
//                                .map(brand -> brand.getName())
//                                .collect(Collectors.toList())
//                );
//                observableBrandIndexSelected.set(0);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//                // Handle error
//            }
//        }));

        //TODO delete the following when backend has data.
        List<IBrand> placeholderBrands = PlaceholderGenerator.getBrands();
        brands.clear();
        brands.addAll(placeholderBrands);
        observableBrandsList.clear();
        observableBrandsList.addAll(
                placeholderBrands
                        .stream()
                        .map(brand -> brand.getName())
                        .collect(Collectors.toList()));
    }

    private void loadPriceBrackets() {
        //TODO delete the following when backend has data.
        observablePriceBracketsList.clear();
        observablePriceBracketsList.addAll(
                Arrays.asList(
                        "Backend",
                        "Give",
                        "Me",
                        "Prices",
                        "Pls"
                )
        );
    }

    public void dispose() {
        this.disposables.dispose();
    }

}