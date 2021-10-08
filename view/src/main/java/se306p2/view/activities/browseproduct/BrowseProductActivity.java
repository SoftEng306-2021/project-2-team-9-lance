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

        Spinner spinner = (Spinner) findViewById(R.id.priceFilter);
        String[] years = {"$0 - $49","$50 - $149","$150 - $299","$299+"};
        ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, years );
        langAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(langAdapter);

    }

}