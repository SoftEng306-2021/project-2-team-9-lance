package se306p2.view.activities.landingpage.adapters;

import android.content.Context;
import android.content.Intent;
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

import se306p2.domain.interfaces.entity.ICategory;
import se306p2.view.R;
import se306p2.view.activities.browseproduct.BrowseProductActivity;
import se306p2.view.activities.landingpage.LandingPageViewModel;
import se306p2.view.activities.productdetail.ProductDetailActivity;


public class CategoryItemRecyclerViewAdapter extends RecyclerView.Adapter<CategoryItemRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "CategoryItemRecyclerViewAdapter";

    private List<ICategory> categories;
    private Context context;

    public CategoryItemRecyclerViewAdapter(Context context, List<ICategory> categories) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder entered");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBinderViewHolder entered");

        ICategory currentCategory = categories.get(position);

        Glide.with(context)
                .asBitmap()
                .load(currentCategory.getImageURI())
                .into(holder.categoryImage);

        holder.categoryName.setText(currentCategory.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onBinderViewHolder, image clicked");

                Intent intent = new Intent(context, BrowseProductActivity.class);
                intent.putExtra("categoryName", currentCategory.getName());
                intent.putExtra("categoryId", currentCategory.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories != null? categories.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_icon_name);
        }
    }


}
