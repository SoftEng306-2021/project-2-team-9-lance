package se306p2.view.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.usecase.GetCurrentUserIdUseCase;
import se306p2.domain.usecase.SignInAnonymouslyUseCase;
import se306p2.model.repository.CategoryRepository;
import se306p2.model.repository.DefaultRepository;
import se306p2.model.repository.ProductRepository;
import se306p2.model.repository.UserRepository;
import se306p2.view.R;
import se306p2.view.activities.landingpage.LandingPageActivity;


public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        RelativeLayout splashLayout = (RelativeLayout) findViewById(R.id.splash_layout);

        DefaultRepository.init(this);
        RepositoryRouter.init(null, CategoryRepository.getInstance(), ProductRepository.getInstance(), null, UserRepository.getInstance());
        String testGetUserId = new GetCurrentUserIdUseCase().getCurrentUserId();
        String testSignIn = new SignInAnonymouslyUseCase().signInAnonymously();
        System.out.println(testGetUserId);
        System.out.println(testSignIn);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.initData();

        viewModel.getUser().observe(this, user -> {
            toLandingPage();
        });

    }

    private void toLandingPage() {
        //
        Intent intent = new Intent(this, LandingPageActivity.class);
        startActivity(intent);
    }

}
