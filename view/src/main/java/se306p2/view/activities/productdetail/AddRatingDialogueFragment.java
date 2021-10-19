package se306p2.view.activities.productdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import se306p2.view.R;

public class AddRatingDialogueFragment extends DialogFragment {

    ProductDetailViewModel viewModel;

    LinearLayoutCompat starsContainer;
    Button doneButton;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_review_fragment, container, false);


        viewModel = new ViewModelProvider(requireActivity()).get(ProductDetailViewModel.class);

        starsContainer = rootView.findViewById(R.id.add_review_stars_container);
        doneButton = rootView.findViewById(R.id.add_review_done);

        renderReviewable();

        doneButton.setOnClickListener(e -> {
            viewModel.sendRating();
            dismiss();
        });


        return rootView;
    }

    private void renderReviewable() {
        System.out.println("================================== able to reach here ?");
        viewModel.getGivenRating().observe(requireActivity(), observedGivenRating -> {
            starsContainer.removeAllViews();
            System.out.println("===================we here");

            for (int i = 0; i < 5; i++) {
                ImageView star = new ImageView(getActivity().getApplicationContext());

                if (!(observedGivenRating == null || observedGivenRating == 0) && i < observedGivenRating) {
                    star.setImageDrawable(getActivity().getDrawable(R.drawable.ic_star_large_filled));
                } else {
                    star.setImageDrawable(getActivity().getDrawable(R.drawable.ic_star_large_outline));
                }

                int finalI = i;
                star.setOnClickListener(e -> {
                    System.out.println("=================== CLICKed!!");

                    viewModel.giveRating(finalI + 1);
                });

                starsContainer.addView(star);
            }
            doneButton.setEnabled(!(observedGivenRating == null || observedGivenRating == 0));

        });
    }

}