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

import se306p2.view.R;

public class ScreenSlidePagerAdapter extends RecyclerView.Adapter<ScreenSlidePagerAdapter.ViewHolder> {

    private Context context;
    private List<String> imageList;

    public ScreenSlidePagerAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.imageList = imageList;
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
                .load(imageList.get(position))
                .into(holder.singleImage);
        holder.singleImage.setBackgroundColor(Color.parseColor(imageList.get(position).get));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView singleImage;

        public ViewHolder(View itemView) {
            super(itemView);
           singleImage = itemView.findViewById(R.id.slider_single_image);
        }
    }
}