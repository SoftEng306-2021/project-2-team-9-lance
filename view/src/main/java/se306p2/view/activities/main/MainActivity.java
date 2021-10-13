package se306p2.view.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import se306p2.domain.RepositoryRouter;
import se306p2.domain.usecase.GetCurrentUserIdUseCase;
import se306p2.domain.usecase.SignInAnonymouslyUseCase;
import se306p2.model.repository.BrandRepository;
import se306p2.model.repository.CategoryRepository;
import se306p2.model.repository.ProductRepository;
import se306p2.model.repository.UserRepository;
import se306p2.view.R;
import se306p2.view.activities.landingpage.LandingPageActivity;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout splashLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        splashLayout = (RelativeLayout) findViewById(R.id.splash_layout);

        splashLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LandingPageActivity.class);
                startActivity(intent);
            }
        });

        RepositoryRouter.init(BrandRepository.getInstance(), CategoryRepository.getInstance(), ProductRepository.getInstance(), null, UserRepository.getInstance());
        String testGetUserId = new GetCurrentUserIdUseCase().getCurrentUserId();
        String testSignIn = new SignInAnonymouslyUseCase().signInAnonymously();
        System.out.println(testGetUserId);
        System.out.println(testSignIn);
    }


}
