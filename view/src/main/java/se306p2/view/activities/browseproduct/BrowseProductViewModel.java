package se306p2.view.activities.browseproduct;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.usecase.IGetBrandsUseCase;
import se306p2.domain.interfaces.usecase.IGetMaxPriceUseCase;
import se306p2.domain.interfaces.usecase.IGetMinPriceUseCase;
import se306p2.domain.interfaces.usecase.IGetProductVersionsUseCase;
import se306p2.domain.interfaces.usecase.IGetProductsByFilterUseCase;
import se306p2.domain.usecase.GetBrandsUseCase;
import se306p2.domain.usecase.GetMaxPriceUseCase;
import se306p2.domain.usecase.GetMinPriceUseCase;
import se306p2.domain.usecase.GetProductsByFilterUseCase;

public class BrowseProductViewModel extends ViewModel {

    IGetMaxPriceUseCase getMaxPriceUseCase = new GetMaxPriceUseCase();
    IGetMinPriceUseCase getMinPriceUseCase = new GetMinPriceUseCase();
    IGetProductsByFilterUseCase getProductsByFilterUseCase = new GetProductsByFilterUseCase();
    IGetBrandsUseCase getBrandsUseCase = new GetBrandsUseCase();

    String categoryId;
    


    public BrowseProductViewModel(Application application, ICategory category) {


    }

    public void loadPageData() {
        //load products
    }

    public void loadProducts(String categoryId) {
        //load produx

    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


}
