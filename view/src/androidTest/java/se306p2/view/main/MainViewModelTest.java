package se306p2.view.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.os.Handler;
import android.os.Looper;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.executor.TaskExecutor;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.interfaces.usecase.IGetCurrentUserIdUseCase;
import se306p2.domain.interfaces.usecase.ISignInAnonymouslyUseCase;
import se306p2.domain.usecase.GetCurrentUserIdUseCase;
import se306p2.domain.usecase.SignInAnonymouslyUseCase;
import se306p2.view.activities.main.MainViewModel;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainViewModelTest {
    @Mock
    private IGetCurrentUserIdUseCase getCurrentUserIdUseCase;
    @Mock
    private ISignInAnonymouslyUseCase signInAnonymouslyUseCase;
    @Mock
    private Observer<String> observer;

    private MainViewModel viewModel;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ArchTaskExecutor.getInstance().setDelegate(new TaskExecutor() {
            @Override
            public void executeOnDiskIO(Runnable runnable) {
                runnable.run();
            }

            @Override
            public void postToMainThread(Runnable runnable) {
                runnable.run();
            }

            @Override
            public boolean isMainThread() {
                return true;
            }
        });
    }

    @BeforeEach
    public void setUp() {
        viewModel = new MainViewModel(getCurrentUserIdUseCase, signInAnonymouslyUseCase);
    }

    @AfterAll
    public void wrapUp() {
        ArchTaskExecutor.getInstance().setDelegate(null);
    }

    @Test
    public void testUserNotExist() {
        when(getCurrentUserIdUseCase.getCurrentUserId()).thenReturn(Single.error(new NullPointerException()));

        String expectedId = "expectedId";
        when(signInAnonymouslyUseCase.signInAnonymously()).thenReturn(Single.just(expectedId));

        assertNotNull(viewModel.getUser());
        viewModel.getUser().observeForever(observer);

        verify(signInAnonymouslyUseCase, times(1)).signInAnonymously();
    }

    @Test
    public void testUserExisting() {
        String expectedId = "expectedId";
        when(getCurrentUserIdUseCase.getCurrentUserId()).thenReturn(Single.just(expectedId));

        assertNotNull(viewModel.getUser());
        viewModel.getUser().observeForever(observer);

        assertEquals(expectedId, viewModel.getUser().getValue());

        verify(signInAnonymouslyUseCase, never()).signInAnonymously();
    }

}