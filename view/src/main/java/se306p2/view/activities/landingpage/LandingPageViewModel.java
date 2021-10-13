package se306p2.view.activities.landingpage;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetCategoryDetailsUseCase;
import se306p2.domain.interfaces.usecase.IGetFavouritesUseCase;
import se306p2.domain.interfaces.usecase.IGetFeaturedProductsUseCase;
import se306p2.domain.interfaces.usecase.ISearchAutoCompleteUseCase;
import se306p2.domain.interfaces.usecase.ISearchProductsUseCase;
import se306p2.domain.usecase.GetFeaturedProductsUseCase;

public class LandingPageViewModel extends ViewModel {
    IGetFeaturedProductsUseCase getFeaturedProductsUseCase;
    IGetCategoryDetailsUseCase getCategoryDetailsUseCase;
    ISearchAutoCompleteUseCase searchAutoCompleteUseCase;
    ISearchProductsUseCase searchProductsUseCase;
    IGetFavouritesUseCase getFavouritesUseCase;

    private MutableLiveData<List<ICategory>> categories;
    private MutableLiveData<List<IProduct>> featuredProducts;
    private MutableLiveData<List<String>> autoCompleteStrings;
    private MutableLiveData<List<IProduct>> searchResults;

    public LandingPageViewModel() {
//        this.getFeaturedProductsUseCase = new GetFeaturedProductsUseCase();
    }


}
