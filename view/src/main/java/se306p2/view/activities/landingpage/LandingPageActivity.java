package se306p2.view.activities.landingpage;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.view.R;
import se306p2.view.activities.landingpage.adapters.CategoryItemRecyclerViewAdapter;
import se306p2.view.common.adapters.ProductItemRecyclerViewAdapter;
import se306p2.view.common.placeholders.PlaceholderCategory;
import se306p2.view.common.placeholders.PlaceholderProduct;

public class LandingPageActivity extends AppCompatActivity {
    private static final String TAG = "LandingPageActivity";


    private List<ICategory> categories = new ArrayList<>();
    private List<IProduct> featuredList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page_view);

        createCategoryPlaceholders();
        createFeaturedProductsPlaceholders();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    private void createCategoryPlaceholders() {
        Log.d(TAG, "creating placeholders for LandingPageActivity");

        for (int i = 0; i < 10; i++) {
            categories.add(new PlaceholderCategory(
                    "0",
                    "Fragrance",
                    "https://user-images.githubusercontent.com/62003343/135788922-64ae3d11-c011-446f-864d-9205fbcff6dc.png"
            ));
        }
    }

    private void createFeaturedProductsPlaceholders() {
        featuredList.add(
                new PlaceholderProduct(
                        "0",
                        "0",
                        "This is some product name that this product has",
                        "0",
                        "Some Brand",
                        "slogan",
                        "details",
                        "usage",
                        "link",
                        null,
                        null,
                        new BigDecimal(23.45),
                        new Double(4.22),
                        2910,
                        "https://user-images.githubusercontent.com/62003343/135811244-9494631d-fb16-4a9f-83a6-41bc87983f0d.png"
                        ));

        featuredList.add(
                new PlaceholderProduct(
                        "0",
                        "0",
                        "Another product with another name is here",
                        "0",
                        "Another Brand",
                        "slogan",
                        "details",
                        "usage",
                        "link",
                        null,
                        null,
                        new BigDecimal(23.45),
                        new Double(4.22),
                        2910,
                        "https://user-images.githubusercontent.com/62003343/135811263-6723c835-6d25-4d48-a0bd-0bf4f71c6493.png"
                ));

        featuredList.add(
                new PlaceholderProduct(
                        "0",
                        "0",
                        "Yet another product here for a placeholder",
                        "0",
                        "Placeholder",
                        "slogan",
                        "details",
                        "usage",
                        "link",
                        null,
                        null,
                        new BigDecimal(23.45),
                        new Double(4.22),
                        2910,
                        "https://user-images.githubusercontent.com/62003343/135811286-fc1b7160-e61d-4675-b5c5-6d29f24ccae8.png"
                ));

        initFeaturedListRecyclerView();
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
    }
}
