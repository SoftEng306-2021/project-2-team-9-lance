package se306p2.view.activities.productdetail;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.app.ActionBar;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.view.R;
import se306p2.view.activities.productdetail.adapters.BenefitItemRecyclerViewAdapter;
import se306p2.view.activities.productdetail.adapters.ScreenSlidePagerAdapter;
import se306p2.view.common.SearchFragment;
import se306p2.view.common.adapters.ProductItemRecyclerViewAdapter;
import se306p2.view.common.helper.DisplayDataFormatter;

import android.os.Bundle;


import se306p2.view.common.placeholders.PlaceholderGenerator;

public class ProductDetailActivity extends AppCompatActivity {

    private String productId;

    private ProductDetailViewModel viewModel;

    private Menu optionsMenu;
    private TextView brandName, productName, priceDollars, priceCents;
    private TextView slogan, description;
    private TextView usage;
    private TextView ingredients;
    private RadioGroup radioGroup;
    private TextView ratingValue, numRatings;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_view);

        Intent intent = getIntent();
        productId = intent.getStringExtra("productId");

        viewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);
        viewModel.init(productId);

        setUpAnimationEnvironment();

        initProductInfo();
        initDetails();
        initBenefits();
        initUsage();
        initIngredients();
        initProductVersions();
        initImages();
        initImageCountDots();
        initFavourite();
        initRating();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_menu, menu);


        optionsMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_heart:
                viewModel.toggleFavourite();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    };


    private void setUpAnimationEnvironment() {
        LinearLayoutCompat rootLinearLayout = (LinearLayoutCompat)findViewById(R.id.product_details_container);
        LayoutTransition layoutTransition = rootLinearLayout.getLayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
    }

    private void initFavourite() {
        viewModel.getIsFavourited().observe(this, observedFavourited -> {
            if (observedFavourited) {
                optionsMenu.findItem(R.id.nav_heart).setIcon(R.drawable.ic_heart_filled);
            } else {
                optionsMenu.findItem(R.id.nav_heart).setIcon(R.drawable.ic_heart_empty);
            }
        });
    }

    private void initProductInfo() {
        brandName = (TextView) findViewById(R.id.product_details_brand);
        productName = (TextView) findViewById(R.id.product_details_name);
        priceDollars = (TextView) findViewById(R.id.product_details_cents);
        priceCents = (TextView) findViewById(R.id.product_details_cents);

        viewModel.getProduct().observe(this, observedProduct -> {
            brandName.setText(observedProduct.getBrandName());
            productName.setText(observedProduct.getName());

            String[] formattedPrice = DisplayDataFormatter.formatPriceData(observedProduct.getPrice());

            priceDollars.setText(formattedPrice[0]);
            priceCents.setText(formattedPrice[1]);
        });
    }

    private void initDetails() {
        slogan = (TextView) findViewById(R.id.product_details_slogan);
        description = (TextView) findViewById(R.id.product_details_description);

        viewModel.getProduct().observe(this, observedProduct -> {
            slogan.setText(observedProduct.getSlogan());
            description.setText(observedProduct.getDetails());
        });

        LinearLayoutCompat detailsTitle = (LinearLayoutCompat) findViewById(R.id.product_details_details_title);
        LinearLayoutCompat detailsContent = (LinearLayoutCompat) findViewById(R.id.product_details_details_content);

        ImageView chevronIcon = (ImageView)findViewById(R.id.product_details_details_chevron);

        hideSection(detailsContent, chevronIcon);

        detailsTitle.setOnClickListener(e -> {
                toggleShowSection(detailsContent, chevronIcon);
        });
    }

    private void initBenefits() {
        LinearLayoutCompat benefitsTitle = (LinearLayoutCompat) findViewById(R.id.product_details_benefits_title);
        LinearLayoutCompat benefitsContent = (LinearLayoutCompat) findViewById(R.id.product_details_benefits_content);

        ImageView chevronIcon = (ImageView)findViewById(R.id.product_details_benefits_chevron);

        hideSection(benefitsContent, chevronIcon);

        benefitsTitle.setOnClickListener(e -> {
                toggleShowSection(benefitsContent, chevronIcon);
        });


        RecyclerView recyclerView = findViewById(R.id.product_details_benefits);

        viewModel.getBenefits().observe(this, observedBenefits -> {
            BenefitItemRecyclerViewAdapter adapter = new BenefitItemRecyclerViewAdapter(this, observedBenefits);

            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
            layoutManager.setFlexDirection(FlexDirection.ROW);
            layoutManager.setJustifyContent(JustifyContent.FLEX_START);
            layoutManager.setFlexWrap(FlexWrap.WRAP);
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(adapter);
        });

    }

    private void initUsage() {
        usage = (TextView) findViewById(R.id.product_details_usage);

        viewModel.getProduct().observe(this, observedProduct -> {
            usage.setText(observedProduct.getUsage() );
        });

        LinearLayoutCompat usageTitle = (LinearLayoutCompat) findViewById(R.id.product_details_usage_title);
        LinearLayoutCompat usageContent = (LinearLayoutCompat) findViewById(R.id.product_details_usage_content);

        ImageView chevronIcon = (ImageView)findViewById(R.id.product_details_usage_chevron);

        hideSection(usageContent, chevronIcon);

        usageTitle.setOnClickListener(e -> {
            toggleShowSection(usageContent, chevronIcon);
        });
    }

    private void initIngredients() {
        ingredients = (TextView) findViewById(R.id.product_details_ingredients);

        viewModel.getProduct().observe(this, observedProduct -> {
            ingredients.setText(observedProduct.getIngredients());
        });

        LinearLayoutCompat ingredientsTitle = (LinearLayoutCompat) findViewById(R.id.product_details_ingredients_title);
        LinearLayoutCompat ingredientsContent = (LinearLayoutCompat) findViewById(R.id.product_details_ingredients_content);

        ImageView chevronIcon = (ImageView)findViewById(R.id.product_details_ingredients_chevron);

        hideSection(ingredientsContent, chevronIcon);

        ingredientsTitle.setOnClickListener(e -> {
                toggleShowSection(ingredientsContent, chevronIcon);
        });
    }

    private void initProductVersions() {
        radioGroup = (RadioGroup) findViewById(R.id.product_details_radio_group);
        radioGroup.removeAllViews();

        viewModel.getProductVersions().observe(this, observedProductVersions -> {

            if (observedProductVersions.size() >  1) {
              for (int i = 0; i < observedProductVersions.size(); i++) {
                  IProductVersion ver = observedProductVersions.get(i);
                  RadioButton button = createRadioButton(ver, i);

                  radioGroup.addView(button);
              }
              radioGroup.getLayoutParams().height = RadioGroup.LayoutParams.WRAP_CONTENT;
            }

            }
        );

        viewModel.getCurrentProductVersion().observe(this, observedVersion -> {
            System.out.println("!!!!!!!!!!!!!!!!! Version changed to " + observedVersion.getId());
        });

        viewModel.getCurrentProductPosition().observe(this, observedPosition -> {
            System.out.println("!!!!!!!!!!!!!!!!! Position changed to " + observedPosition);

            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                View buttonView = radioGroup.getChildAt(i);
                if (buttonView instanceof RadioButton) {
                    if (observedPosition == i) {
                        ((RadioButton) buttonView).setChecked(true);
                    } else {
                        ((RadioButton) buttonView).setChecked(false);
                    }
                }
            }
        });
    }

    private void initImages() {
        viewPager = (ViewPager2) findViewById(R.id.image_slider_viewpager);

        viewModel.getCurrentProductVersion().observe(this, observedVersion -> {
            ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(this, observedVersion);
            viewPager.setAdapter(pagerAdapter);
        });

    }

    private void initImageCountDots() {
        LinearLayoutCompat dotsContainer = findViewById(R.id.dots_container);
        dotsContainer.removeAllViews();

        viewModel.getCurrentProductVersion().observe(this, observedVersion -> {
            dotsContainer.removeAllViews();
            for (int i = 0; i < observedVersion.getImageURI().size(); i++) {
                ImageView iv = new ImageView(getApplicationContext());
                iv.setImageDrawable(getDrawable(R.drawable.circle));
                LinearLayoutCompat.LayoutParams lp =  new LinearLayoutCompat.LayoutParams(
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                lp.setMargins(8, 0, 8, 0);
                iv.setLayoutParams(lp);

                dotsContainer.addView(iv);
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0; i < dotsContainer.getChildCount(); i++) {
                    ImageView child = (ImageView) dotsContainer.getChildAt(i);
                    if (i == position) {
                        child.setImageDrawable(getDrawable(R.drawable.dark_circle));
                    } else {
                        child.setImageDrawable(getDrawable(R.drawable.circle));
                    }
                }
            }
        });
    }

    private void initRating() {
        ratingValue = findViewById(R.id.product_details_rating);
        numRatings = findViewById(R.id.product_details_num_reviews);

        viewModel.getRating().observe(this, observedRating -> {
            DecimalFormat df = new DecimalFormat("#.#");
            ratingValue.setText(df.format(observedRating.getRating()));
            numRatings.setText("(" + Integer.toString(observedRating.getNum()) + ")");
        });


    }


    private RadioButton createRadioButton(IProductVersion productVersion, int index) {
        RadioButton button = new RadioButton(this);

        button.setScaleX(new Float(1.5));
        button.setScaleY(new Float(1.5));

        button.setText(null);

        button.setPadding(0, 0, 3, 0);

        button.setButtonTintList(ColorStateList.valueOf(Color.parseColor(productVersion.getHexColor())));

        button.setOnClickListener(e -> {
            viewModel.setCurrentVersion(productVersion, index);
        });

        return button;
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
