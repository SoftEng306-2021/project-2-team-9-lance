package se306p2.view.activities.landingpage;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import se306p2.view.R;
import se306p2.view.activities.landingpage.adapters.CategoryItemRecyclerViewAdapter;

public class LandingPageActivity extends AppCompatActivity {
    private static final String TAG = "LandingPageActivity";


    private List<String> categoryNames = new ArrayList<>();
    private List<String> categoryIcons = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page_view);

        createPlaceholders();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    private void createPlaceholders() {
        Log.d(TAG, "creating placeholders for LandingPageActivity");
        categoryIcons.addAll(
                Arrays.asList("https://user-images.githubusercontent.com/62003343/135788922-64ae3d11-c011-446f-864d-9205fbcff6dc.png",
                        "https://user-images.githubusercontent.com/62003343/135788922-64ae3d11-c011-446f-864d-9205fbcff6dc.png",
                        "https://user-images.githubusercontent.com/62003343/135788922-64ae3d11-c011-446f-864d-9205fbcff6dc.png",
                        "https://user-images.githubusercontent.com/62003343/135788922-64ae3d11-c011-446f-864d-9205fbcff6dc.png",
                        "https://user-images.githubusercontent.com/62003343/135788922-64ae3d11-c011-446f-864d-9205fbcff6dc.png",
                        "https://user-images.githubusercontent.com/62003343/135788922-64ae3d11-c011-446f-864d-9205fbcff6dc.png",
                        "https://user-images.githubusercontent.com/62003343/135788922-64ae3d11-c011-446f-864d-9205fbcff6dc.png",
                        "https://user-images.githubusercontent.com/62003343/135788922-64ae3d11-c011-446f-864d-9205fbcff6dc.png"
                ));
        categoryNames.addAll(Arrays.asList("Category1", "Category2", "Category", "Category", "Category", "Category", "Category", "Category"));
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView entered");


        RecyclerView recyclerView = findViewById(R.id.category_list);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);

        CategoryItemRecyclerViewAdapter adapter = new CategoryItemRecyclerViewAdapter(this, categoryNames, categoryIcons);
        recyclerView.setAdapter(adapter);
    }
}
