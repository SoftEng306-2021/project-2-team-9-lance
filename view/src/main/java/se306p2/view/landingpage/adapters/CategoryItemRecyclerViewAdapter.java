package se306p2.view.landingpage.adapters;

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

import se306p2.view.R;


public class CategoryItemRecyclerViewAdapter extends RecyclerView.Adapter<CategoryItemRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private List<String> categoryNames;
    private List<String> categoryIcons;
    private Context context;

    public CategoryItemRecyclerViewAdapter(List<String> categoryNames, List<String> categoryIcons, Context context) {
        this.categoryNames = categoryNames;
        this.categoryIcons = categoryIcons;
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

        Glide.with(context)
                .asBitmap()
                .load(categoryIcons.get(position))
                .into(holder.categoryImage);

        holder.categoryName.setText(categoryNames.get(position));

        holder.categoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onBinderViewHolder, image clicked");
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryNames.size();
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
