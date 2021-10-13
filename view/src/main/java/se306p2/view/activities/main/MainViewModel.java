package se306p2.view.activities.main;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.repositories.IBrandRepository;
import se306p2.domain.interfaces.usecase.IGetCurrentUserIdUseCase;
import se306p2.domain.interfaces.usecase.ISignInAnonymouslyUseCase;
import se306p2.domain.usecase.GetCurrentUserIdUseCase;
import se306p2.domain.usecase.SignInAnonymouslyUseCase;
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
        Log.d(TAG, "++++++++++++initData entered");

        DefaultRepository.init(context);
        RepositoryRouter.init(
                        null,
                        CategoryRepository.getInstance(),
                        ProductRepository.getInstance(),
                        null,
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
        this.disposables.add(currentUserId.subscribeWith(new DisposableSingleObserver<String>() {
            @Override
            public void onSuccess(String id) {
                userId.postValue(id);
            }

            @Override
            public void onError(Throwable e) {
                signInAnonymouslyUseCase.signInAnonymously().doOnSuccess(id -> {
                    userId.postValue(id);
                });
            }
        }));
    }
}
