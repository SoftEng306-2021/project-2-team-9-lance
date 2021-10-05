package se306p2.view.common.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import se306p2.domain.interfaces.entity.IProduct;
import se306p2.view.R;
import se306p2.view.activities.landingpage.adapters.CategoryItemRecyclerViewAdapter;

public class ProductItemRecyclerViewAdapter extends RecyclerView.Adapter<ProductItemRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "ProductItemRecyclerViewAdapter";

    private Context context;
    List<IProduct> products;

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

        IProduct product = products.get(position);

        Glide.with(context)
                .asBitmap()
                .load(product.getDefaultImageURI())
                .into(holder.productImage);

        holder.productBrand.setText("Waiting for Lance");
        holder.productName.setText(product.getName());
        holder.productPriceDollar.setText("00");
        holder.productPriceCent.setText("00");
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
