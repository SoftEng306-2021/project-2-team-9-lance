package se306p2.view.activities.main;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.repositories.IBrandRepository;
import se306p2.domain.interfaces.usecase.IGetCurrentUserIdUseCase;
import se306p2.domain.interfaces.usecase.ISignInAnonymouslyUseCase;
import se306p2.domain.usecase.GetCurrentUserIdUseCase;
import se306p2.domain.usecase.SignInAnonymouslyUseCase;
import se306p2.model.repository.BrandRepository;
import se306p2.model.repository.CategoryRepository;
import se306p2.model.repository.DefaultRepository;
import se306p2.model.repository.ProductRepository;
import se306p2.model.repository.RatingRepository;
import se306p2.model.repository.UserRepository;

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    private IGetCurrentUserIdUseCase getCurrentUserIdUseCase;
    private ISignInAnonymouslyUseCase signInAnonymouslyUseCase;

    private MutableLiveData<String> userId; //this gets observed by MainActivityView

    private CompositeDisposable disposables = new CompositeDisposable();

    public MainViewModel() {
        this.getCurrentUserIdUseCase = new GetCurrentUserIdUseCase();
        this.signInAnonymouslyUseCase = new SignInAnonymouslyUseCase();
    }

    public MainViewModel(IGetCurrentUserIdUseCase getCurrentUserIdUseCase, ISignInAnonymouslyUseCase signInAnonymouslyUseCase) {
        this.getCurrentUserIdUseCase = getCurrentUserIdUseCase;
        this.signInAnonymouslyUseCase = signInAnonymouslyUseCase;
    }

    public void initData(@NonNull Context context) {

        DefaultRepository.init(context);
        RepositoryRouter.init(
                BrandRepository.getInstance(),
                CategoryRepository.getInstance(),
                ProductRepository.getInstance(),
                RatingRepository.getInstance(),
                UserRepository.getInstance()
        );
    }

    public void dispose() {
        this.disposables.dispose();
    }

    public LiveData<String> getUser() {
        if (this.userId == null) {
            this.userId = new MutableLiveData<>();
            signInAnonymously();
        }
        return this.userId;
    }

    private void signInAnonymously() {
        Single<String> currentUserId = getCurrentUserIdUseCase.getCurrentUserId();
        this.disposables.add(currentUserId.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(id -> userId.postValue(id),
                        e -> {
                            Single<String> signedInUserId = signInAnonymouslyUseCase.signInAnonymously();
                            disposables.add(signedInUserId.
                                    subscribeOn(Schedulers.io()).
                                    observeOn(AndroidSchedulers.mainThread()).
                                    subscribe(id -> userId.postValue(id), e2 -> {
                                        e.printStackTrace();
                                        e2.printStackTrace();
                                    }));
                        }));
    }
}
