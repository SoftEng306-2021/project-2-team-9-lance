package se306p2.view.common.adapters;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import se306p2.domain.interfaces.entity.IProduct;
import se306p2.view.R;
import se306p2.view.activities.landingpage.LandingPageActivity;
import se306p2.view.activities.landingpage.adapters.CategoryItemRecyclerViewAdapter;
import se306p2.view.activities.main.MainActivity;
import se306p2.view.activities.productdetail.ProductDetailActivity;
import se306p2.view.common.helper.DisplayDataFormatter;


public class ProductItemRecyclerViewAdapter extends RecyclerView.Adapter<ProductItemRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "ProductItemRecyclerViewAdapter";
    List<IProduct> products;
    private Context context;

    public ProductItemRecyclerViewAdapter(Context context, List<IProduct> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder entered");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_listitem, parent, false);
        return new ProductItemRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBinderViewHolder entered");

        IProduct currentProduct = products.get(position);

        Glide.with(context)
                .asBitmap()
                .load(currentProduct.getDefaultImageURI())
                .into(holder.productImage);

        holder.productBrand.setText(currentProduct.getBrandName());
        holder.productName.setText(currentProduct.getName());

        String[] formattedPrice = DisplayDataFormatter.formatPriceData(currentProduct.getPrice());

        String dollar = formattedPrice[0];
        String cent = formattedPrice[1];


        holder.productPriceDollar.setText(dollar);
        holder.productPriceCent.setText(cent);

        holder.itemView.setOnClickListener(e -> {
            Log.d(TAG, "onBinderViewHolder, " + currentProduct.getBrandName() + " " + currentProduct.getName() + "clicked");
            Intent intent = new Intent(context, ProductDetailActivity.class);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++productId " + currentProduct.getId());
            intent.putExtra("productId", currentProduct.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productBrand;
        TextView productName;
        TextView productPriceDollar;
        TextView productPriceCent;

        public ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(se306p2.view.R.id.product_listitem_image);
            productBrand = itemView.findViewById(se306p2.view.R.id.product_listitem_brand);
            productName = itemView.findViewById(se306p2.view.R.id.product_listitem_name);
            productPriceDollar = itemView.findViewById(se306p2.view.R.id.product_listitem_price_dollar);
            productPriceCent = itemView.findViewById(se306p2.view.R.id.product_listitem_price_cent);
        }


    }
}
