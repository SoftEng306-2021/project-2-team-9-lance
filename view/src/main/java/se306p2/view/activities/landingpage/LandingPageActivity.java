package se306p2.view.activities.landingpage;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import se306p2.view.R;
import se306p2.view.activities.landingpage.adapters.CategoryItemRecyclerViewAdapter;
import se306p2.view.common.SearchFragment;
import se306p2.view.common.adapters.ProductItemRecyclerViewAdapter;

public class LandingPageActivity extends AppCompatActivity {
    private static final String TAG = "LandingPageActivity";

    private LandingPageViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page_view);

        viewModel = new ViewModelProvider(this).get(LandingPageViewModel.class);

        viewModel.init();

        initCategoryListRecyclerView();
        initFeaturedListRecyclerView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.dispose();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        menu.removeItem(R.id.nav_search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_search:
                System.out.println("+------------------------------------------------------------");
                SearchFragment searchFragment = new SearchFragment();
                searchFragment.show(getSupportFragmentManager(), "SearchFragment");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    };

    private void initCategoryListRecyclerView() {
        Log.d(TAG, "initCategoryListRecyclerView entered");

        RecyclerView categoryRecyclerView = findViewById(R.id.category_list);

        viewModel.getCategories().observe(this, categories -> {
            CategoryItemRecyclerViewAdapter adapter = new CategoryItemRecyclerViewAdapter(this, categories);

            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            categoryRecyclerView.setLayoutManager(manager);

            categoryRecyclerView.setAdapter(adapter);
        });
    }

    private void initFeaturedListRecyclerView() {
        Log.d(TAG, "initFeaturedListRecyclerView entered");

        RecyclerView featuredRecyclerView = findViewById(R.id.featured_list);

        viewModel.getFeaturedProducts().observe(this, featuredProducts -> {
            ProductItemRecyclerViewAdapter adapter = new ProductItemRecyclerViewAdapter(this, featuredProducts);

            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            featuredRecyclerView.setLayoutManager(manager);

            featuredRecyclerView.setAdapter(adapter);
        });
    }

}
