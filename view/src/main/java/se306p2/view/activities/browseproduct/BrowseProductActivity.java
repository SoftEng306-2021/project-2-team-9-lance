package se306p2.view.activities.browseproduct;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import se306p2.domain.interfaces.entity.IProduct;
import se306p2.view.R;
import se306p2.view.common.adapters.ProductItemRecyclerViewAdapter;
import se306p2.view.common.placeholders.PlaceholderGenerator;
import se306p2.view.databinding.BrowseProductsViewBinding;


public class BrowseProductActivity extends AppCompatActivity {
    private static final String TAG = "BrowseProductActivity";

    private BrowseProductViewModel viewModel;
    private BrowseProductsViewBinding binding;

    private String categoryId;
    private String categoryName;
    private List<IProduct> productList = new ArrayList<>();

    private float startY;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_products_view);

        Intent intent = getIntent();
        categoryId = intent.getStringExtra("categoryId");
        categoryName = intent.getStringExtra("categoryName");

        viewModel = new ViewModelProvider(this).get(BrowseProductViewModel.class);
        viewModel.init(categoryId);

        binding = DataBindingUtil.setContentView(this, R.layout.browse_products_view);
        binding.setViewModel(viewModel);




        productList.addAll(PlaceholderGenerator.getProducts(20));


        initProductsRecyclerView();
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

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        ProductItemRecyclerViewAdapter adapter = new ProductItemRecyclerViewAdapter(this, productList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                animateStickyFilterBar(dx, dy);
            }
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