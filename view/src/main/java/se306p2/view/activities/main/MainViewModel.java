package se306p2.view.activities.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import se306p2.domain.interfaces.usecase.IGetCurrentUserIdUseCase;
import se306p2.domain.interfaces.usecase.ISignInAnonymouslyUseCase;
import se306p2.domain.usecase.GetCurrentUserIdUseCase;
import se306p2.domain.usecase.SignInAnonymouslyUseCase;

public class MainViewModel extends ViewModel {
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
