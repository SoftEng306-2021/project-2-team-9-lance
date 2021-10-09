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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import org.w3c.dom.Text;

import java.util.List;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.view.R;
import se306p2.view.activities.productdetail.adapters.BenefitItemRecyclerViewAdapter;
import se306p2.view.common.helper.DisplayDataFormatter;

import android.os.Bundle;


import se306p2.view.common.placeholders.PlaceholderGenerator;

public class ProductDetailActivity extends AppCompatActivity {

    private IProduct product;
    private List<IBenefit> benefits;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_view);


        getData();

        setUpAnimationEnvironment();

        initProductInfo();
        initDetails();
        initBenefits();
        initIngredients();
    }

    private void getData() {
        product = PlaceholderGenerator.getProduct();
        benefits = PlaceholderGenerator.getBenefits();
    }

    private void setUpAnimationEnvironment() {
        LinearLayoutCompat rootLinearLayout = (LinearLayoutCompat)findViewById(R.id.product_details_container);
        LayoutTransition layoutTransition = rootLinearLayout.getLayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
    }

    private void initProductInfo() {
        TextView brandName, productName, slogan, priceDollars, priceCents;

        brandName = (TextView) findViewById(R.id.product_details_brand);
        productName = (TextView) findViewById(R.id.product_details_name);
        slogan = (TextView) findViewById(R.id.product_details_slogan);
        priceDollars = (TextView) findViewById(R.id.product_details_cents);
        priceCents = (TextView) findViewById(R.id.product_details_cents);

        brandName.setText(product.getBrandName());
        productName.setText(product.getName());
        slogan.setText(product.getSlogan());

        String[] formattedPrice = DisplayDataFormatter.formatPriceData(product.getPrice());
        priceDollars.setText(formattedPrice[0]);
        priceCents.setText(formattedPrice[1]);
    }

    private void initDetails() {
        TextView description = (TextView) findViewById(R.id.product_details_description);
        description.setText(product.getDetails());

        LinearLayoutCompat detailsTitle = (LinearLayoutCompat) findViewById(R.id.product_details_details_title);
        LinearLayoutCompat detailsContent = (LinearLayoutCompat) findViewById(R.id.product_details_details_content);

        ImageView chevronIcon = (ImageView)findViewById(R.id.product_details_details_chevron);

        hideSection(detailsContent, chevronIcon);

        detailsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleShowSection(detailsContent, chevronIcon);
            }
        });
    }

    private void initBenefits() {
        LinearLayoutCompat benefitsTitle = (LinearLayoutCompat) findViewById(R.id.product_details_benefits_title);
        LinearLayoutCompat benefitsContent = (LinearLayoutCompat) findViewById(R.id.product_details_benefits_content);

        ImageView chevronIcon = (ImageView)findViewById(R.id.product_details_benefits_chevron);

        hideSection(benefitsContent, chevronIcon);

        benefitsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleShowSection(benefitsContent, chevronIcon);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.product_details_benefits);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        recyclerView.setLayoutManager(layoutManager);

        BenefitItemRecyclerViewAdapter adapter = new BenefitItemRecyclerViewAdapter(this, benefits);
        recyclerView.setAdapter(adapter);

    }

    private void initIngredients() {
        TextView ingredients = (TextView) findViewById(R.id.product_details_ingredients);
        ingredients.setText(product.getIngredients());

        LinearLayoutCompat ingredientsTitle = (LinearLayoutCompat) findViewById(R.id.product_details_ingredients_title);
        LinearLayoutCompat ingredientsContent = (LinearLayoutCompat) findViewById(R.id.product_details_ingredients_content);

        ImageView chevronIcon = (ImageView)findViewById(R.id.product_details_ingredients_chevron);

        hideSection(ingredientsContent, chevronIcon);

        ingredientsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleShowSection(ingredientsContent, chevronIcon);
            }
        });
    }

    private void toggleShowSection(View toggleView, ImageView chevron) {
        if (toggleView.isShown()) {
            hideSection(toggleView,  chevron);
        } else {
            showSection( toggleView,  chevron);
        }
    }

    private void hideSection(View toggleView, ImageView chevron) {
        toggleView.setVisibility(View.GONE);
        RotateAnimation r = new RotateAnimation(0.0f, 90.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        r.setDuration(300);
        r.setRepeatCount(0);
        r.setFillAfter(true);
        chevron.startAnimation(r);
    }

    private void showSection(View toggleView, ImageView chevron) {
        toggleView.setVisibility(View.VISIBLE);
        RotateAnimation r = new RotateAnimation(90.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        r.setDuration(300);
        r.setRepeatCount(0);
        r.setFillAfter(true);
        chevron.startAnimation(r);
    }

}
