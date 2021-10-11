package se306p2.view.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.Observer;

import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import se306p2.domain.interfaces.usecase.IGetCurrentUserIdUseCase;
import se306p2.domain.interfaces.usecase.ISignInAnonymouslyUseCase;
import se306p2.domain.usecase.GetCurrentUserIdUseCase;
import se306p2.domain.usecase.SignInAnonymouslyUseCase;
import se306p2.view.activities.main.MainViewModel;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainViewModelTest {

    IGetCurrentUserIdUseCase getCurrentUserIdUseCase;
    ISignInAnonymouslyUseCase signInAnonymouslyUseCase;

    private MainViewModel viewModel;

    @Mock
    Observer<String> observer;

    @BeforeAll
    public void setUp() {
        getCurrentUserIdUseCase = Mockito.mock(GetCurrentUserIdUseCase.class);
        signInAnonymouslyUseCase = Mockito.mock(SignInAnonymouslyUseCase.class);

        viewModel = new MainViewModel(getCurrentUserIdUseCase, signInAnonymouslyUseCase);

        viewModel.getUser().observeForever(observer);
    }

    @Test
    public void testUserNotExist() {
        when(getCurrentUserIdUseCase.getCurrentUserId()).thenReturn(null);

        String expectedId = "expectedId";
        when(signInAnonymouslyUseCase.signInAnonymously()).thenReturn(expectedId);

        assertNotNull(viewModel.getUser());
        assertEquals(expectedId, viewModel.getUser());
        assertTrue(viewModel.getUser().hasObservers());

        verify(signInAnonymouslyUseCase, times(1)).signInAnonymously();
    }

    @Test
    public void testUserExisting() {
        String expectedId = "expectedId";
        when(getCurrentUserIdUseCase.getCurrentUserId()).thenReturn(expectedId);

        assertNotNull(viewModel.getUser());
        assertEquals(expectedId, viewModel.getUser());

        verify(signInAnonymouslyUseCase, never()).signInAnonymously();
    }

}