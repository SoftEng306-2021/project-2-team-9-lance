package se306p2.view.activities.browseproduct;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Observable;

import se306p2.domain.interfaces.entity.IBrand;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.usecase.IGetBrandsUseCase;
import se306p2.domain.interfaces.usecase.IGetMaxPriceUseCase;
import se306p2.domain.interfaces.usecase.IGetMinPriceUseCase;
import se306p2.domain.interfaces.usecase.IGetProductsByFilterUseCase;
import se306p2.domain.usecase.GetBrandsUseCase;
import se306p2.domain.usecase.GetMaxPriceUseCase;
import se306p2.domain.usecase.GetMinPriceUseCase;
import se306p2.domain.usecase.GetProductsByFilterUseCase;

public class BrowseProductViewModel extends BaseObservable {

    IGetMaxPriceUseCase getMaxPriceUseCase = new GetMaxPriceUseCase();
    IGetMinPriceUseCase getMinPriceUseCase = new GetMinPriceUseCase();
    IGetProductsByFilterUseCase getProductsByFilterUseCase = new GetProductsByFilterUseCase();
    IGetBrandsUseCase getBrandsUseCase = new GetBrandsUseCase();

    private String categoryId;

    private List<IBrand> availableBrands;
    String brandIdSelected;

    List<IProduct> products;

    private final ObservableArrayList<String> observableBrandsList = new ObservableArrayList<>();
    private final ObservableInt observableBrandIndexSelected = new ObservableInt();


    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    public void loadPageData() {
        loadBrands();

        for (int i = 0; i < availableBrands.size(); i++) {
            observableBrandsList.add(availableBrands.get(i).getName());
        }

        observableBrandIndexSelected.set(0);


//        loadProducts();
    }



    public List<IBrand> getBrands() {
        return availableBrands;
    }


    public String[] getPriceBrackets() {
        //TODO replace
        return new String[]{"Price range1, price range2, price range3"};
    }


    public void setBrandSelected(int position) {

    }

    public void loadProducts(String categoryId, String brandIdSelected) {
        //load produx

    }

    private void loadBrands() {
        availableBrands = getBrandsUseCase.getBrands(categoryId);
    }

}
