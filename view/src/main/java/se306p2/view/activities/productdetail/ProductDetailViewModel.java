package se306p2.view.activities.productdetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.domain.interfaces.usecase.IGetFavouritesUseCase;
import se306p2.domain.interfaces.usecase.IGetProductUseCase;
import se306p2.domain.interfaces.usecase.IGetProductVersionsUseCase;
import se306p2.domain.usecase.GetFavouritesUseCase;
import se306p2.domain.usecase.GetProductUseCase;
import se306p2.domain.usecase.GetProductVersionsUseCase;

public class ProductDetailViewModel extends ViewModel {
    private static final String TAG = "ProductDetailViewModel";
    private CompositeDisposable disposables = new CompositeDisposable();

    private IGetProductUseCase getProductUseCase;
    private IGetProductVersionsUseCase getProductVersionsUseCase;
    private IGetFavouritesUseCase getFavouritesUseCase;

    private String productId;

    private MutableLiveData<IProduct> product = new MutableLiveData<>();
    private MutableLiveData<List<IProductVersion>> productVersions = new MutableLiveData<>();

    public ProductDetailViewModel() {
        this.getProductUseCase = new GetProductUseCase();
        this.getProductVersionsUseCase = new GetProductVersionsUseCase();
        this.getFavouritesUseCase = new GetFavouritesUseCase();
    }

    public ProductDetailViewModel(
            IGetProductUseCase getProductUseCase,
            IGetProductVersionsUseCase getProductVersionsUseCase,
            IGetFavouritesUseCase getFavouritesUseCase
    ) {
        this.getProductUseCase = getProductUseCase;
        this.getProductVersionsUseCase = getProductVersionsUseCase;
        this.getFavouritesUseCase = getFavouritesUseCase;
    }

    public void init(String productId) {
        this.productId = productId;

        loadProduct();
    }

    private void loadProduct() {
        Single<IProduct> productSingle = getProductUseCase.getProduct(productId);
        this.disposables.add(productSingle.subscribeWith(new DisposableSingleObserver<IProduct>() {
            @Override
            public void onSuccess(IProduct retrievedProduct) {
                product.postValue(retrievedProduct);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                //Handle error
            }
        }));
    }

    public IProduct getProduct() {
        return product.getValue();
    }


    public void dispose() {
        this.disposables.dispose();
    }

}
