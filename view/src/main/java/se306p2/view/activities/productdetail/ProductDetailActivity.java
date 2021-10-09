package se306p2.view.activities.productdetail;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import se306p2.domain.interfaces.entity.IProduct;
import se306p2.view.R;
import se306p2.view.common.helper.DisplayDataFormatter;
import se306p2.view.common.placeholders.PlaceholderGenerator;

public class ProductDetailActivity extends AppCompatActivity {

    //to be moved to ViewModel
    private IProduct product;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_view);


        getData();

        setUpAnimationEnvironment();

        initProductInfo();
    }

    private void getData() {
        product = PlaceholderGenerator.getProduct();
    }

    private void setUpAnimationEnvironment() {
        LinearLayoutCompat rootLinearLayout = (LinearLayoutCompat)findViewById(R.id.product_details_container);
        LayoutTransition layoutTransition = rootLinearLayout.getLayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
    }

    private void initProductInfo() {
        TextView brandName, productName, slogan, description, priceDollars, priceCents;

        brandName = (TextView) findViewById(R.id.product_details_brand);
        productName = (TextView) findViewById(R.id.product_details_name);
        slogan = (TextView) findViewById(R.id.product_details_slogan);
        description = (TextView) findViewById(R.id.product_details_description);
        priceDollars = (TextView) findViewById(R.id.product_details_cents);
        priceCents = (TextView) findViewById(R.id.product_details_cents);

        brandName.setText(product.getBrandName());
        productName.setText(product.getName());
        slogan.setText(product.getSlogan());
        description.setText(product.getDetails());

        String[] formattedPrice = DisplayDataFormatter.formatPriceData(product.getPrice());

        priceDollars.setText(formattedPrice[0]);
        priceCents.setText(formattedPrice[1]);

        LinearLayoutCompat detailsTitle = (LinearLayoutCompat) findViewById(R.id.product_details_details_title);
        LinearLayoutCompat detailsContent = (LinearLayoutCompat) findViewById(R.id.product_details_details_content);

        ImageView chevronIcon = (ImageView)findViewById(R.id.product_details_details_chevron);

        detailsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleShowSection(detailsContent, chevronIcon);
            }
        });
    }

    private void toggleShowSection(View toggleView, ImageView chevron) {
        if (toggleView.isShown()) {
            toggleView.setVisibility(View.GONE);
            RotateAnimation r = new RotateAnimation(0.0f, 90.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            r.setDuration(300);
            r.setRepeatCount(0);
            r.setFillAfter(true);
            chevron.startAnimation(r);
        } else {
            toggleView.setVisibility(View.VISIBLE);
            RotateAnimation r = new RotateAnimation(90.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            r.setDuration(300);
            r.setRepeatCount(0);
            r.setFillAfter(true);
            chevron.startAnimation(r);
        }
    }

}
