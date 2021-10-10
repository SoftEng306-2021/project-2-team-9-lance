package se306p2.view.activities.productdetail.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.view.R;

public class BenefitItemRecyclerViewAdapter extends RecyclerView.Adapter<BenefitItemRecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<IBenefit> benefits;

    public BenefitItemRecyclerViewAdapter(Context context, List<IBenefit> benefits) {
        this.context = context;
        this.benefits = benefits;
    }
    @NonNull
    @Override
    public BenefitItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.benefit_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BenefitItemRecyclerViewAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(benefits.get(position).getImageURI())
                .into(holder.benefitImage);

        holder.benefitName.setText(benefits.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return benefits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView benefitImage;
        TextView benefitName;

        public ViewHolder(View itemView) {
            super(itemView);
            benefitImage = itemView.findViewById(R.id.benefit_image);
            benefitName = itemView.findViewById(R.id.benefit_name);
        }
    }
}
