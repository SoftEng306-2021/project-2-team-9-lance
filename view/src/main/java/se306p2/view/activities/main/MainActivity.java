package se306p2.view.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import se306p2.view.R;
import se306p2.view.activities.landingpage.LandingPageActivity;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout splashLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        splashLayout = (RelativeLayout) findViewById(R.id.splash_layout);

        MainViewModel model = new ViewModelProvider(this).get(MainViewModel.class);
        model.getUser().observe(this, user -> {
            Intent intent = new Intent(MainActivity.this, LandingPageActivity.class);
            startActivity(intent);

        });
    }

}
