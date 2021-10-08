package se306p2.view.activities.browseproduct;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import se306p2.view.R;

public class BrowseProductActivity extends AppCompatActivity {

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
    }

}