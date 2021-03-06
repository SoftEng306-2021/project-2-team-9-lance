package se306p2.view.activities.productdetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Set;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.domain.interfaces.entity.IRating;
import se306p2.domain.interfaces.usecase.IAddRatingUseCase;
import se306p2.domain.interfaces.usecase.IFavouritesUseCase;
import se306p2.domain.interfaces.usecase.IGetBenefitsUseCase;
import se306p2.domain.interfaces.usecase.IGetCurrentUserIdUseCase;
import se306p2.domain.interfaces.usecase.IGetFavouritesUseCase;
import se306p2.domain.interfaces.usecase.IGetProductUseCase;
import se306p2.domain.interfaces.usecase.IGetProductVersionsUseCase;
import se306p2.domain.interfaces.usecase.IGetRatedUseCase;
import se306p2.domain.interfaces.usecase.IGetRatingUseCase;
import se306p2.domain.usecase.AddRatingUseCase;
import se306p2.domain.usecase.FavouritesUseCase;
import se306p2.domain.usecase.GetBenefitsUseCase;
import se306p2.domain.usecase.GetCurrentUserIdUseCase;
import se306p2.domain.usecase.GetFavouritesUseCase;
import se306p2.domain.usecase.GetProductUseCase;
import se306p2.domain.usecase.GetProductVersionsUseCase;
import se306p2.domain.usecase.GetRatedUseCase;
import se306p2.domain.usecase.GetRatingUseCase;

public class ProductDetailViewModel extends ViewModel {
    private static final String TAG = "ProductDetailViewModel";
    private CompositeDisposable disposables = new CompositeDisposable();

    private IGetProductUseCase getProductUseCase;
    private IGetProductVersionsUseCase getProductVersionsUseCase;
    private IGetFavouritesUseCase getFavouritesUseCase;
    private IGetBenefitsUseCase getBenefitsUseCase;
    private IGetRatingUseCase getRatingUseCase;
    private IFavouritesUseCase favouritesUseCase;
    private IAddRatingUseCase addRatingUseCase;
    private IGetCurrentUserIdUseCase getCurrentUserIdUseCase;
    private IGetRatedUseCase getRatedUseCase;

    private String productId;
    private String userId;

    private MutableLiveData<IProduct> product = new MutableLiveData<>();
    private MutableLiveData<List<IBenefit>> benefits = new MutableLiveData<>();
    private MutableLiveData<List<IProductVersion>> productVersions = new MutableLiveData<>();
    private MutableLiveData<IProductVersion> currentProductVersion = new MutableLiveData<>();
    private MutableLiveData<Integer> currentProductPosition = new MutableLiveData<>();
    private MutableLiveData<Boolean> favourited = new MutableLiveData<>();
    private MutableLiveData<IRating> rating = new MutableLiveData<>();
    private MutableLiveData<Integer> givenRating = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRated = new MutableLiveData<>();

    private MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public ProductDetailViewModel() {
        this.getProductUseCase = new GetProductUseCase();
        this.getProductVersionsUseCase = new GetProductVersionsUseCase();
        this.getFavouritesUseCase = new GetFavouritesUseCase();
        this.getBenefitsUseCase = new GetBenefitsUseCase();
        this.getRatingUseCase = new GetRatingUseCase();
        this.favouritesUseCase = new FavouritesUseCase();
        this.addRatingUseCase = new AddRatingUseCase();
        this.getCurrentUserIdUseCase = new GetCurrentUserIdUseCase();
        this.getRatedUseCase = new GetRatedUseCase();
    }

    public ProductDetailViewModel(
            IGetProductUseCase getProductUseCase,
            IGetProductVersionsUseCase getProductVersionsUseCase,
            IGetFavouritesUseCase getFavouritesUseCase,
            IGetBenefitsUseCase getBenefitsUseCase,
            IGetRatingUseCase getRatingUseCase,
            IFavouritesUseCase favouritesUseCase,
            IAddRatingUseCase addRatingUseCase,
            IGetCurrentUserIdUseCase getCurrentUserIdUseCase,
            IGetRatedUseCase getRatedUseCase
    ) {
        this.getProductUseCase = getProductUseCase;
        this.getProductVersionsUseCase = getProductVersionsUseCase;
        this.getFavouritesUseCase = getFavouritesUseCase;
        this.getBenefitsUseCase = getBenefitsUseCase;
        this.getRatingUseCase = getRatingUseCase;
        this.favouritesUseCase = favouritesUseCase;
        this.addRatingUseCase = addRatingUseCase;
        this.getCurrentUserIdUseCase = getCurrentUserIdUseCase;
        this.getRatedUseCase = getRatedUseCase;
    }

    public void init(String productId) {
        this.productId = productId;

        loadUser();
        loadProduct();
        loadBenefits();
        loadProductVersions();
        loadFavourite();
        loadRating();
        givenRating.postValue(0);
    }

    private void loadUser() {
        Single<String> userSingle = getCurrentUserIdUseCase.getCurrentUserId();
        this.disposables.add(userSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(retrievedId -> {
                            userId = retrievedId;

                            loadIsRated();
                        },
                        e -> e.printStackTrace()));
    }

    private void loadProduct() {
        Single<IProduct> productSingle = getProductUseCase.getProduct(productId);
        this.disposables.add(productSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(retrievedProduct -> product.postValue(retrievedProduct),
                        e -> e.printStackTrace()));
    }


    private void loadBenefits() {
        Single<List<IBenefit>> benefitsSingle = getBenefitsUseCase.getBenefits(productId);
        this.disposables.add(benefitsSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(retrievedBenefit -> benefits.postValue(retrievedBenefit),
                        e -> e.printStackTrace()));
    }

    private void loadProductVersions() {
        Single<List<IProductVersion>> productVersionsSingle = getProductVersionsUseCase.getProductVersions(productId);
        this.disposables.add(productVersionsSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(retrievedVersions -> {
                            productVersions.postValue(retrievedVersions);
                            currentProductVersion.postValue(retrievedVersions.get(0));
                            currentProductPosition.postValue(0);
                        },
                        e -> e.printStackTrace()));
    }

    private void loadFavourite() {
        Single<Set<String>> favouritesSingle = getFavouritesUseCase.getFavourites();
        this.disposables.add(favouritesSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(retrievedFavourites -> {
                    if (retrievedFavourites.contains(productId)) {
                        favourited.postValue(true);
                    } else {
                        favourited.postValue(false);
                    }
                }));
    }

    private void loadRating() {
        Single<IRating> ratingSingle = getRatingUseCase.getRating(productId);
        this.disposables.add(ratingSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(retrievedRating -> {
                            rating.postValue(retrievedRating);
                        },
                        e -> e.printStackTrace()));

    }

    public void loadIsRated() {

        Single<Boolean> ratedSingle = getRatedUseCase.rated(productId, userId);
        this.disposables.add(ratedSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(retrievedRated -> {
                            isRated.postValue(retrievedRated);
                        },
                        e -> e.printStackTrace()));
    }

    public void toggleFavourite() {
        Single<Set<String>> toggleFavouritesSingle = favouritesUseCase.favourite(productId);
        this.disposables.add(toggleFavouritesSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(retrievedFavourites -> {
                    if (retrievedFavourites.contains(productId) ^ favourited.getValue()) {
                        favourited.postValue(!favourited.getValue());
                    }
                }));
    }

    public void setCurrentVersion(IProductVersion ver, int index) {
        currentProductVersion.postValue(ver);
        currentProductPosition.postValue(index);
    }

    public void giveRating(int r) {
        givenRating.postValue(r);
    }

    public void sendRating() {

        Single<IRating> addedRatingSingle = addRatingUseCase.addRating(productId, userId, givenRating.getValue());
        this.disposables.add(addedRatingSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(retrievedRating -> {
                                loadRating();
                                loadIsRated();

                                toastMessage.postValue("Your rating has been saved");
                        },
                        e -> {
                            toastMessage.postValue("Something went wrong... Try again later");
                            e.printStackTrace();
                        }
                )
        );

    }


    public LiveData<IProduct> getProduct() {
        return product;
    }

    public LiveData<List<IBenefit>> getBenefits() {
        return benefits;
    }

    public LiveData<List<IProductVersion>> getProductVersions() {
        return productVersions;
    }

    public LiveData<Integer> getCurrentProductPosition() {
        return currentProductPosition;
    }

    public LiveData<Boolean> getIsFavourited() {
        return favourited;
    }

    public LiveData<IRating> getRating() {
        return rating;
    }


    public LiveData<Integer> getGivenRating() {
        return givenRating;
    }

    public LiveData<Boolean> getIsRated() {
        return isRated;
    }

    public LiveData<IProductVersion> getCurrentProductVersion() {
        return currentProductVersion;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void dispose() {
        this.disposables.dispose();
    }

}
