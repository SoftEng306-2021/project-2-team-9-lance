package se306p2.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import se306p2.view.R;
import se306p2.view.landingpage.LandingPageActivity;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout splashLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        splashLayout = (RelativeLayout) findViewById(R.id.splash_layout);

        splashLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LandingPageActivity.class);
                startActivity(intent);
            }
        });
    }


}
