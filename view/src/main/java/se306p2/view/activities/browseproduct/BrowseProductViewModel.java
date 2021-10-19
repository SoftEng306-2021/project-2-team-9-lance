package se306p2.view.activities.browseproduct;

import android.util.Pair;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import se306p2.domain.interfaces.entity.IBrand;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetBrandsUseCase;
import se306p2.domain.interfaces.usecase.IGetMaxPriceUseCase;
import se306p2.domain.interfaces.usecase.IGetMinPriceUseCase;
import se306p2.domain.interfaces.usecase.IGetProductsByFilterUseCase;
import se306p2.domain.interfaces.usecase.ISearchAndFilterProductsUseCase;
import se306p2.domain.interfaces.usecase.ISearchProductsUseCase;
import se306p2.domain.usecase.GetBrandsUseCase;
import se306p2.domain.usecase.GetMaxPriceUseCase;
import se306p2.domain.usecase.GetMinPriceUseCase;
import se306p2.domain.usecase.GetProductsByFilterUseCase;
import se306p2.domain.usecase.SearchAndFilterProductsUseCase;
import se306p2.domain.usecase.SearchProductsUseCase;

public class BrowseProductViewModel extends ViewModel {
    private CompositeDisposable disposables = new CompositeDisposable();

    private IGetMaxPriceUseCase getMaxPriceUseCase = new GetMaxPriceUseCase();
    private IGetMinPriceUseCase getMinPriceUseCase = new GetMinPriceUseCase();
    private IGetProductsByFilterUseCase getProductsByFilterUseCase = new GetProductsByFilterUseCase();
    private IGetBrandsUseCase getBrandsUseCase = new GetBrandsUseCase();
    private ISearchProductsUseCase searchProductsUseCase = new SearchProductsUseCase();
    private ISearchAndFilterProductsUseCase searchAndFilterProductsUseCase = new SearchAndFilterProductsUseCase();

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
    private String searchTerm;
    private List<IBrand> brands = new ArrayList<>();

    private MutableLiveData<List<IProduct>> products = new MutableLiveData<>();

    private final List<Integer[]> PRICE_BRACKETS = Arrays.asList(
            new Integer[]{0, 15},
            new Integer[]{15, 50},
            new Integer[]{50, 100},
            new Integer[]{100, 999}
    );

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void init() {
        loadPriceBrackets();
        if (searchTerm != null) {
            // Load Search
            loadProductsBySearchTerm();
        } else {
            // Load Category
            loadBrands();
        }
    }

    public void loadProductsBySearchTerm() {
        Single<Pair<List<IProduct>, List<IBrand>>> productsSingle = searchProductsUseCase.searchProducts(this.searchTerm);
        this.disposables.add(productsSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(pair -> {
                            products.postValue(pair.first);
                            brands = pair.second;
                            observableBrandsList.clear();
                            observableBrandsList.add("All");
                            observableBrandsList.addAll(brands
                                    .stream()
                                    .map(brand -> brand.getName())
                                    .collect(Collectors.toList()));

                            observableBrandIndexSelected.set(0);
                        },
                        e -> e.printStackTrace()));
    }

    private void loadProductsBySearchAndFilter() {
        int selectedPriceBracketIndex = observablePriceBracketIndexSelected.get() - 1; //-1 is required to account for the "All" option at the start of the list
        Integer[] selectedPriceBracket = PRICE_BRACKETS.get(selectedPriceBracketIndex != -1 ? selectedPriceBracketIndex : 0);

        String brandId = brands == null || brands.size() == 0 || observableBrandIndexSelected.get() == 0 ? null :
                brands.get(observableBrandIndexSelected.get() - 1).getId();
        Single<List<IProduct>> productsSingle = searchAndFilterProductsUseCase.searchAndFilterProducts(this.searchTerm,
                brandId,
                new BigDecimal(selectedPriceBracketIndex != -1 ? selectedPriceBracket[0] : 0),
                new BigDecimal(selectedPriceBracketIndex != -1 ? selectedPriceBracket[1] : 1000));
        this.disposables.add(productsSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(productList -> products.postValue(productList),
                        e -> e.printStackTrace()));
    }

    public void loadProductsByFilter() {
        if (searchTerm != null) {
            // Load Search
            loadProductsBySearchAndFilter();
            return;
        }

        int selectedPriceBracketIndex = observablePriceBracketIndexSelected.get() - 1; //-1 is required to account for the "All" option at the start of the list
        Integer[] selectedPriceBracket = PRICE_BRACKETS.get(selectedPriceBracketIndex != -1 ? selectedPriceBracketIndex : 0);

        String brandId = brands == null || brands.size() == 0 || observableBrandIndexSelected.get() == 0 ? null :
                brands.get(observableBrandIndexSelected.get() - 1).getId();
        Single<List<IProduct>> productsSingle = getProductsByFilterUseCase.getProductsByFilter(
                categoryId,
                brandId, //-1 is required to account for the "All" option at the start of the list
                new BigDecimal(selectedPriceBracketIndex != -1 ? selectedPriceBracket[0] : 0),
                new BigDecimal(selectedPriceBracketIndex != -1 ? selectedPriceBracket[1] : 1000)
        );
        this.disposables.add(productsSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(productList -> products.postValue(productList),
                        e -> e.printStackTrace()));
    }

    private void loadBrands() {
        Single<List<IBrand>> brandsSingle = getBrandsUseCase.getBrands(categoryId);
        this.disposables.add(brandsSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(brandList -> {
                            brands = brandList;
                            observableBrandsList.clear();
                            observableBrandsList.add("All");
                            observableBrandsList.addAll(brandList
                                    .stream()
                                    .map(brand -> brand.getName())
                                    .collect(Collectors.toList()));

                            observableBrandIndexSelected.set(0);

                            loadProductsByFilter();
                        },
                        e -> e.printStackTrace()));
    }

    private void loadPriceBrackets() {
        observablePriceBracketsList.clear();
        observablePriceBracketsList.add("All");
        observablePriceBracketsList.addAll(
                PRICE_BRACKETS.stream().map(bracket ->
                        bracket[1] == null ?
                                "$" + bracket[0] + "+" :
                                "$" + bracket[0] + " - $" + bracket[1]
                ).collect(Collectors.toList()));
    }

    public void clearFilter() {
        observableBrandIndexSelected.set(0);
        observablePriceBracketIndexSelected.set(0);
        if (searchTerm != null) {
            // Load Search
            loadProductsBySearchTerm();
        } else {
            // Load Category
            loadProductsByFilter();
        }
    }

    public LiveData<List<IProduct>> getProducts() {
        return products;
    }

    public void dispose() {
        this.disposables.dispose();
    }

}