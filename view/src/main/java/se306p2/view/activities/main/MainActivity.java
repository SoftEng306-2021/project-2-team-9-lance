package se306p2.view.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import se306p2.view.R;
import se306p2.view.activities.landingpage.LandingPageActivity;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        RelativeLayout splashLayout = (RelativeLayout) findViewById(R.id.splash_layout);


        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.initData(this);

        viewModel.getUser().observe(this, user -> {
            toLandingPage();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.dispose();
    }

    private void toLandingPage() {
        //
        Intent intent = new Intent(this, LandingPageActivity.class);
        startActivity(intent);
    }

}
