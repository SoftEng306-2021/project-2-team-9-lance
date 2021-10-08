package se306p2.view.activities.browseproduct;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import se306p2.domain.interfaces.entity.IProduct;
import se306p2.view.R;
import se306p2.view.common.adapters.ProductItemRecyclerViewAdapter;
import se306p2.view.common.placeholders.PlaceholderGenerator;

public class BrowseProductActivity extends AppCompatActivity {
    private static final String TAG = "BrowseProductActivity";

    private List<IProduct> productList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_products_view);

        Spinner priceSpinner = (Spinner) findViewById(R.id.priceFilter);
        String[] prices = {"$0 - $49","$50 - $149","$150 - $299","$299+"};
        ArrayAdapter<CharSequence> priceAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, prices );
        priceAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        priceSpinner.setAdapter(priceAdapter);

        Spinner brandSpinner = (Spinner) findViewById(R.id.brandFilter);
        String[] years = {"Apple", "Pear", "Watermelon"};
        ArrayAdapter<CharSequence> brandAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, years );
        brandAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        brandSpinner.setAdapter(brandAdapter);

        productList.addAll(PlaceholderGenerator.getProducts(20));

        initProductsRecyclerView();
    }

    private void initProductsRecyclerView() {
        Log.d(TAG, "initFeaturedListRecyclerView entered");

        RecyclerView recyclerView = findViewById(R.id.browse_products_list);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        ProductItemRecyclerViewAdapter adapter = new ProductItemRecyclerViewAdapter(this, productList);
        recyclerView.setAdapter(adapter);
    }

}