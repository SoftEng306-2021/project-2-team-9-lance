package se306p2.view.activities.browseproduct;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import se306p2.domain.interfaces.entity.IProduct;
import se306p2.view.R;
import se306p2.view.activities.landingpage.LandingPageViewModel;
import se306p2.view.common.adapters.ProductItemRecyclerViewAdapter;
import se306p2.view.common.helper.FormatConverter;
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

        viewModel = new ViewModelProvider(this).get(BrowseProductViewModel.class);
        viewModel.loadPageData();
//        binding = DataBindingUtil.bind(findViewById(R.id.brandFilter));
        binding = DataBindingUtil.setContentView(this, R.layout.browse_products_view);
        binding.setViewModel(viewModel);



        Intent intent = getIntent();
        categoryId = intent.getStringExtra("categoryId");
        categoryName = intent.getStringExtra("categoryName");

        productList.addAll(PlaceholderGenerator.getProducts(20));

        viewModel.setCategoryId(categoryId);



//        createPriceSpinner();
//        createBrandSpinner();

        initProductsRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

//    private void createPriceSpinner() {
//        Spinner priceSpinner = (Spinner) findViewById(R.id.priceFilter);
//        String[] priceBrackets = viewModel.getPriceBrackets();
//
//        ArrayAdapter<CharSequence> priceAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, priceBrackets);
//        priceAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
//        priceSpinner.setAdapter(priceAdapter);
//
////        priceSpinner.setOnItemSelectedListener(new PriceSpinnerClass());
//    }

    private void createBrandSpinner() {
//        String[] spinnerBrands = FormatConverter.ConvertBrandsToStringArr(viewModel.getBrands());

//        String[] spinnerBrands = {"one", "two", "three"};

//        ObservableArrayList<String> spinnerBrands = viewModel.getObservableBrandsList();
//
//        Spinner brandSpinner = (Spinner) findViewById(R.id.brandFilter);
//
//        ArrayAdapter<CharSequence> brandAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, spinnerBrands);
//        brandAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
//        brandSpinner.setAdapter(brandAdapter);

//        brandSpinner.setOnItemSelectedListener(new BrandSpinnerClass());
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

                System.out.println("==========" + dx + " " + dy);

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
        });

    }

//    class PriceSpinnerClass implements AdapterView.OnItemSelectedListener {
//        public void onItemSelected(AdapterView<?> parent, View v, int position, long id ) {
//            viewModel.setPriceRangeSelected(position);
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> adapterView) {
//            viewModel.setPriceRangeSelected(0);
//        }
//    }
//
//    class BrandSpinnerClass implements AdapterView.OnItemSelectedListener {
//        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
//            viewModel.setBrandSelected(position);
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> adapterView) {
//            viewModel.setBrandSelected(0);
//        }
//    }
}