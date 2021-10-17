package se306p2.view.activities.browseproduct;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import se306p2.view.R;
import se306p2.view.common.adapters.ProductItemRecyclerViewAdapter;
import se306p2.view.databinding.BrowseProductsViewBinding;


public class BrowseProductActivity extends AppCompatActivity {
    private static final String TAG = "BrowseProductActivity";

    private BrowseProductViewModel viewModel;
    private BrowseProductsViewBinding binding;

    private String categoryId;
    private String categoryName;
    private String searchTerm;

    private float startY;

    private ImageView filterButton;
    private ImageView cancelFilterButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_products_view);

        Intent intent = getIntent();
        categoryId = intent.getStringExtra("categoryId");
        categoryName = intent.getStringExtra("categoryName");
        searchTerm = intent.getStringExtra("searchTerm");

        viewModel = new ViewModelProvider(this).get(BrowseProductViewModel.class);
        System.out.println("BrowseProductActivity categoryId: " + categoryId);
        viewModel.setCategoryId(categoryId);

        System.out.println("BrowseProductActivity searchTerm: " + searchTerm);
        viewModel.setSearchTerm(searchTerm);

        viewModel.init();

        binding = DataBindingUtil.setContentView(this, R.layout.browse_products_view);
        binding.setViewModel(viewModel);


        initProductsRecyclerView();
        initFilterButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }


    private void initProductsRecyclerView() {
        Log.d(TAG, "initFeaturedListRecyclerView entered");

        RecyclerView recyclerView = findViewById(R.id.browse_products_list);


        viewModel.getProducts().observe(this, products -> {
            ProductItemRecyclerViewAdapter adapter = new ProductItemRecyclerViewAdapter(this, products);

            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(manager);

            recyclerView.setAdapter(adapter);
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                animateStickyFilterBar(dx, dy);
            }
        });

    }

    public void initFilterButtons() {
        filterButton = (ImageView) findViewById(R.id.browse_products_filter_icon);
        cancelFilterButton = (ImageView) findViewById(R.id.browse_products_cancel_filter);

        filterButton.setOnClickListener(e -> {
            viewModel.loadProductsByFilter();
        });

        cancelFilterButton.setOnClickListener(e -> {
            viewModel.clearFilter();
        });

     }

    private void animateStickyFilterBar(int dx, int dy) {
        LinearLayoutCompat filtersBar = (LinearLayoutCompat) findViewById(R.id.filtersBar);
        ViewGroup.MarginLayoutParams filtersBarParams = (ViewGroup.MarginLayoutParams) filtersBar.getLayoutParams();

        if (dy > 15) {
            if (filtersBarParams.topMargin >= 15) {
                filtersBarParams.topMargin -= 10;
            }
            if (filtersBarParams.bottomMargin >= 10) {
                filtersBarParams.bottomMargin -= 10;
            }
        }


        if (dy < -15) {
            if (filtersBarParams.topMargin <= 45) {
                filtersBarParams.topMargin += 10;
            }
            if (filtersBarParams.bottomMargin <= 45) {
                filtersBarParams.bottomMargin += 10;
            }
        }

        filtersBar.requestLayout();
    }
}