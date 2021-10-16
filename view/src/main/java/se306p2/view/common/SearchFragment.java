package se306p2.view.common;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import se306p2.view.R;

public class SearchFragment extends DialogFragment {

    AutoCompleteTextView autoCompleteTextView;


    ArrayAdapter<String> adapter;
    String[] stop_names;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment, container, false);


        stop_names = new String[]{"usa", "china", "russia", "bangladesh"};

        autoCompleteTextView = rootView.findViewById(R.id.search_auto_complete_view);
        autoCompleteTextView.setDropDownVerticalOffset(45);

        adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, stop_names);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);


        return rootView;
    }



}
