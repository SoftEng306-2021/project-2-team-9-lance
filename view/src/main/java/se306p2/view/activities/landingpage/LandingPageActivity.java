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
import java.util.List;

import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.view.R;
import se306p2.view.activities.landingpage.adapters.CategoryItemRecyclerViewAdapter;
import se306p2.view.common.adapters.ProductItemRecyclerViewAdapter;
import se306p2.view.common.placeholders.PlaceholderGenerator;
import se306p2.view.common.placeholders.placeholderEntities.PlaceholderCategory;

public class LandingPageActivity extends AppCompatActivity {
    private static final String TAG = "LandingPageActivity";


    private List<ICategory> categories = new ArrayList<>();
    private List<IProduct> featuredList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page_view);

        categories.addAll(PlaceholderGenerator.getCategories());
        featuredList.addAll(PlaceholderGenerator.getProducts());

        initCategoryListRecyclerView();
        initFeaturedListRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    private void initCategoryListRecyclerView() {
        Log.d(TAG, "initCategoryListRecyclerView entered");


        RecyclerView recyclerView = findViewById(R.id.category_list);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);

        CategoryItemRecyclerViewAdapter adapter = new CategoryItemRecyclerViewAdapter(this, categories);
        recyclerView.setAdapter(adapter);
    }

    private void initFeaturedListRecyclerView() {
        Log.d(TAG, "initFeaturedListRecyclerView entered");

        RecyclerView recyclerView = findViewById(R.id.featured_list);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        ProductItemRecyclerViewAdapter adapter = new ProductItemRecyclerViewAdapter(this, featuredList);
        recyclerView.setAdapter(adapter);
    }
}
