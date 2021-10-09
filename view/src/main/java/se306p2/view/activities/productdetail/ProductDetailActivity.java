package se306p2.view.activities.productdetail;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.View;
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

        initProductInfo();
    }

    private void getData() {
        product = PlaceholderGenerator.getProduct();
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

        LinearLayoutCompat rootLinearLayout = (LinearLayoutCompat)findViewById(R.id.product_details_container);

        detailsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutTransition layoutTransition = rootLinearLayout.getLayoutTransition();
                layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
                if (detailsContent.isShown()) {
//                    detailsContent.setAlpha(1.0f);
//
//// Start the animation
//                    detailsContent.animate()
//                            .translationY(-detailsContent.getHeight())
//                            .alpha(0.0f)
//                            .setListener(new AnimatorListenerAdapter() {
//                                @Override
//                                public void onAnimationEnd(Animator animation) {
//                                    super.onAnimationEnd(animation);
//                                    detailsContent.setVisibility(View.GONE);
//                                }
//                            });

                    detailsContent.setVisibility(view.GONE);
                } else {
                    detailsContent.setVisibility(View.VISIBLE);
//                    detailsContent.setAlpha(0.0f);
//
//// Start the animation
//                    detailsContent.animate()
//                            .translationY(0)
//                            .alpha(1.0f)
//                            .setListener(null);
                }
            }
        });


    }

}
