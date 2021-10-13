package se306p2.view.activities.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.repositories.IBrandRepository;
import se306p2.domain.interfaces.usecase.IGetCurrentUserIdUseCase;
import se306p2.domain.interfaces.usecase.ISignInAnonymouslyUseCase;
import se306p2.domain.usecase.GetCurrentUserIdUseCase;
import se306p2.domain.usecase.SignInAnonymouslyUseCase;
import se306p2.model.repository.CategoryRepository;
import se306p2.model.repository.ProductRepository;
import se306p2.model.repository.RatingRepository;
import se306p2.model.repository.UserRepository;

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    IGetCurrentUserIdUseCase getCurrentUserIdUseCase;
    ISignInAnonymouslyUseCase signInAnonymouslyUseCase;

    private MutableLiveData<String> userId; //this gets observed by MainActivityView

    public MainViewModel() {
        this.getCurrentUserIdUseCase = new GetCurrentUserIdUseCase();
        this.signInAnonymouslyUseCase = new SignInAnonymouslyUseCase();
    }

    public MainViewModel(IGetCurrentUserIdUseCase getCurrentUserIdUseCase, ISignInAnonymouslyUseCase signInAnonymouslyUseCase) {
        this.getCurrentUserIdUseCase = getCurrentUserIdUseCase;
        this.signInAnonymouslyUseCase = signInAnonymouslyUseCase;
    }

    public void initData() {
        Log.d(TAG, "++++++++++++initData entered");

        RepositoryRouter.init(
                        null,
                        CategoryRepository.getInstance(),
                        ProductRepository.getInstance(),
                        RatingRepository.getInstance(),
                        UserRepository.getInstance()
        );
    }

    public LiveData<String> getUser() {
        if (this.userId == null) {
            this.userId = new MutableLiveData<>();
            signInAnonymously();
        }
        return this.userId;
    }

    private void signInAnonymously() {
        String currentUserId = getCurrentUserIdUseCase.getCurrentUserId();

        if (currentUserId == null) {
            currentUserId = signInAnonymouslyUseCase.signInAnonymously();
        }

        this.userId.postValue(currentUserId);
    }
}
