package se306p2.view.activities.productdetail.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.view.R;

public class ScreenSlidePagerAdapter extends RecyclerView.Adapter<ScreenSlidePagerAdapter.ViewHolder> {

    private Context context;
    private IProductVersion version;

    public ScreenSlidePagerAdapter(Context context, IProductVersion version) {
        this.context = context;
        this.version = version;
    }

    @NonNull
    @Override
    public ScreenSlidePagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image_item, parent, false);
        return new ScreenSlidePagerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScreenSlidePagerAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(version.getImageURI().get(position))
                .into(holder.singleImage);
        if (version.getHexColor() != null && !version.getHexColor().isEmpty()){
            holder.singleImage.setBackgroundColor(Color.parseColor(version.getHexColor()));
        }
    }

    @Override
    public int getItemCount() {
        return version.getImageURI().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView singleImage;

        public ViewHolder(View itemView) {
            super(itemView);
           singleImage = itemView.findViewById(R.id.slider_single_image);
        }
    }
}