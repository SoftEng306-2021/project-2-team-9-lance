package se306p2.view.activities.landingpage;

import android.content.Intent;
import android.util.Log;

import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetCategoryDetailsUseCase;
import se306p2.domain.interfaces.usecase.IGetFavouritesUseCase;
import se306p2.domain.interfaces.usecase.IGetFeaturedProductsUseCase;
import se306p2.domain.interfaces.usecase.ISearchAutoCompleteUseCase;
import se306p2.domain.interfaces.usecase.ISearchProductsUseCase;
import se306p2.domain.usecase.GetCategoryDetailsUseCase;
import se306p2.domain.usecase.GetFavouritesUseCase;
import se306p2.domain.usecase.GetFeaturedProductsUseCase;
import se306p2.domain.usecase.SearchAutoCompleteUseCase;
import se306p2.domain.usecase.SearchProductsUseCase;
import se306p2.view.activities.browseproduct.BrowseProductActivity;

public class LandingPageViewModel extends ViewModel {
    private static final String TAG = "LandingPageActivity";
    private CompositeDisposable disposables = new CompositeDisposable();

    private IGetFeaturedProductsUseCase getFeaturedProductsUseCase;
    private IGetCategoryDetailsUseCase getCategoryDetailsUseCase;
    private ISearchAutoCompleteUseCase searchAutoCompleteUseCase;
    private ISearchProductsUseCase searchProductsUseCase;
    private IGetFavouritesUseCase getFavouritesUseCase;


    private MutableLiveData<List<ICategory>> categories = new MutableLiveData<List<ICategory>>();
    private MutableLiveData<List<IProduct>> featuredProducts = new MutableLiveData<>();
    private MutableLiveData<List<String>> autoCompleteStrings = new MutableLiveData<>();
    private MutableLiveData<List<IProduct>> searchResults = new MutableLiveData<>();
    MutableLiveData<List<String>> autoCompleteOptions = new MutableLiveData<>();

    /**
     * Two way binding with text field in the search bar
     */
    //@Bindable
    private MutableLiveData<String> searchTerm;

    public LandingPageViewModel() {
        this.getFeaturedProductsUseCase = new GetFeaturedProductsUseCase();
        this.getCategoryDetailsUseCase = new GetCategoryDetailsUseCase();
        this.searchAutoCompleteUseCase = new SearchAutoCompleteUseCase();
        this.searchProductsUseCase = new SearchProductsUseCase();
        this.getFavouritesUseCase = new GetFavouritesUseCase();
    }

    public LandingPageViewModel(
            IGetFeaturedProductsUseCase getFeaturedProductsUseCase,
            IGetCategoryDetailsUseCase getCategoryDetailsUseCase,
            ISearchAutoCompleteUseCase searchAutoCompleteUseCase,
            ISearchProductsUseCase searchProductsUseCase,
            IGetFavouritesUseCase getFavouritesUseCase
    ) {
        this.getFeaturedProductsUseCase = getFeaturedProductsUseCase;
        this.getCategoryDetailsUseCase = getCategoryDetailsUseCase;
        this.searchAutoCompleteUseCase = searchAutoCompleteUseCase;
        this.searchProductsUseCase = searchProductsUseCase;
        this.getFavouritesUseCase = getFavouritesUseCase;
    }


    public void init() {
        Log.d(TAG, "init entered");

        loadCategories();
        loadFeaturedProducts();
    }

    private void loadCategories() {
        Single<List<ICategory>> categoriesSingle = getCategoryDetailsUseCase.getCategoryDetails();
        this.disposables.add(categoriesSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(categoryList -> categories.postValue(categoryList),
                        e -> e.printStackTrace()));
    }

    private void loadFeaturedProducts() {
        Single<List<IProduct>> productsSingle = getFeaturedProductsUseCase.getFeaturedProducts();
        this.disposables.add(productsSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(productList -> featuredProducts.postValue(productList),
                        e -> e.printStackTrace()));
    }

    public void search(String str) {

        System.out.println("======================== searching " + str);
        Single<List<String>> autocompleteSingle = searchAutoCompleteUseCase.searchAutoComplete(str);
        this.disposables.add(autocompleteSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(autocompleteList -> {
                    autoCompleteOptions.postValue(autocompleteList);
                    System.out.println("==========RESULTS==============RESULTS========= " + autocompleteList);
                }));

    }

    public LiveData<List<String>> getAutocompleteOptions() { return autoCompleteOptions; };

    public LiveData<List<ICategory>> getCategories() {
        return categories;
    }

    public LiveData<List<IProduct>> getFeaturedProducts() {
        return featuredProducts;
    }

    //TODO move this to Search activity potentially
//    private void loadAutocompleteStrings() {
//        Single<List<String>>  autocompleteSingle = searchAutoCompleteUseCase.searchAutoComplete(searchTerm)
//    }
    public void dispose() {
        this.disposables.dispose();
    }

}
