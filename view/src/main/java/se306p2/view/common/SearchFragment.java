package se306p2.view.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.usecase.IGetProductVersionsUseCase;
import se306p2.domain.interfaces.usecase.ISearchAutoCompleteUseCase;
import se306p2.domain.usecase.SearchAutoCompleteUseCase;
import se306p2.view.R;
import se306p2.view.activities.browseproduct.BrowseProductActivity;
import se306p2.view.activities.landingpage.LandingPageViewModel;

public class SearchFragment extends DialogFragment {
    private CompositeDisposable disposables = new CompositeDisposable();

    LandingPageViewModel viewModel;

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(LandingPageViewModel.class);


        autoCompleteTextView = rootView.findViewById(R.id.search_auto_complete_view);
        autoCompleteTextView.setDropDownVerticalOffset(45);
        autoCompleteTextView.requestFocus();
        showKeyboard();

        viewModel.getAutocompleteOptions().observe(this, observedAutocomplete -> {
            System.out.println("======================== observedAutoComplete" + observedAutocomplete.toString());
            adapter = new ArrayAdapter<>(
                    getActivity(),
                    R.layout.support_simple_spinner_dropdown_item,
                    observedAutocomplete.toArray(new String[observedAutocomplete.size()]));
            autoCompleteTextView.setThreshold(1);
            autoCompleteTextView.setAdapter(adapter);

        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                System.out.println("========================after text changed");
                viewModel.search(autoCompleteTextView.getText().toString());
            }
        });

        autoCompleteTextView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if ((keyEvent.getAction() == KeyEvent.ACTION_UP) && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    System.out.println("------------------------------- enter pressed");
                    closeKeyboard();

                    Intent intent = new Intent(getActivity(), BrowseProductActivity.class);
                    intent.putExtra("searchTerm", autoCompleteTextView.getText().toString());
                    getActivity().startActivity(intent);

                    return true;
                }
                return false;
            }
        });

        return rootView;
    }

    public void showKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
