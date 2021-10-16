package se306p2.view.activities.browseproduct;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
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
    private CompositeDisposable disposables = new CompositeDisposable();

    private IGetMaxPriceUseCase getMaxPriceUseCase = new GetMaxPriceUseCase();
    private IGetMinPriceUseCase getMinPriceUseCase = new GetMinPriceUseCase();
    private IGetProductsByFilterUseCase getProductsByFilterUseCase = new GetProductsByFilterUseCase();
    private IGetBrandsUseCase getBrandsUseCase = new GetBrandsUseCase();


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


    private String categoryId;
    private List<IBrand> brands = new ArrayList<>();

    private MutableLiveData<List<IProduct>> products = new MutableLiveData<>();

    private final List<Integer[]> PRICE_BRACKETS = Arrays.asList(
            new Integer[]{0, 15},
            new Integer[]{15, 50},
            new Integer[]{50, 100},
            new Integer[]{100, null}
            );

    public void init(String categoryId) {
        this.categoryId = categoryId;

        loadBrands();
        loadPriceBrackets();
        loadProducts();
    }

    public void loadProducts() {
        System.out.println("+++++++++++++++" + observablePriceBracketIndexSelected.get() + " " + observableBrandIndexSelected.get());

        int selectedPriceBracketIndex = observablePriceBracketIndexSelected.get() - 1 < 0 ? 0 : observablePriceBracketIndexSelected.get() - 1; //-1 is required to account for the "All" option at the start of the list
        Integer[] selectedPriceBracket = PRICE_BRACKETS.get(selectedPriceBracketIndex);

        //TODO **DO NOT DELETE**
        //TODO uncomment the following when backend has data.
//        Single<List<IProduct>> productsSingle = getProductsByFilterUseCase.getProductsByFilter(
//                categoryId,
//                brands.get(observableBrandIndexSelected.get() - 1 < 0 ? 0 : observableBrandIndexSelected.get() - 1).getId(), //-1 is required to account for the "All" option at the start of the list
//                new BigDecimal(selectedPriceBracket[0]),
//                new BigDecimal(selectedPriceBracket[1])
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
//                observableBrandsList.add("All);
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
        observableBrandsList.add("All");
        observableBrandsList.addAll(
                placeholderBrands
                        .stream()
                        .map(brand -> brand.getName())
                        .collect(Collectors.toList()));
    }

    private void loadPriceBrackets() {
        observablePriceBracketsList.clear();
        observablePriceBracketsList.add("All");
        observablePriceBracketsList.addAll(
                PRICE_BRACKETS.stream().map(bracket -> {
                            return bracket[1] == null ?
                                    "$" + bracket[0] + "+" :
                                    "$" + bracket[0] + " - $" + bracket[1];
                        }
                ).collect(Collectors.toList()));
    }

    public void clearFilter() {
        observableBrandIndexSelected.set(0);
        observablePriceBracketIndexSelected.set(0);
        loadProducts();
    }

    public LiveData<List<IProduct>> getProducts() {
        return products;
    }

    public void dispose() {
        this.disposables.dispose();
    }

}