package se306p2.view.common;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.lifecycle.MutableLiveData;

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

public class SearchFragment extends DialogFragment {
    private CompositeDisposable disposables = new CompositeDisposable();

    ISearchAutoCompleteUseCase searchAutoCompleteUseCase = new SearchAutoCompleteUseCase();

    AutoCompleteTextView autoCompleteTextView;


    ArrayAdapter<String> adapter;
    String[] stop_names;

    MutableLiveData<List<String>> autoCompleteOptions = new MutableLiveData<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment, container, false);


        autoCompleteTextView = rootView.findViewById(R.id.search_auto_complete_view);
        autoCompleteTextView.setDropDownVerticalOffset(45);

        autoCompleteOptions.observe(this, observedAutocomplete -> {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + observedAutocomplete.toString());
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
                search(autoCompleteTextView.getText().toString());
            }
        });

        return rootView;
    }

    private void search(String str) {

        System.out.println("+++++++++++++++++++++++++++ searching " + str);
        Single<List<String>> autocompleteSingle = searchAutoCompleteUseCase.searchAutoComplete(str);
        this.disposables.add(autocompleteSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(autocompleteList -> {
                    autoCompleteOptions.postValue(autocompleteList);
                    System.out.println("==========RESULTS==============RESULTS========= " + autocompleteList);
                }));

    }

    public void dispose() {
        this.disposables.dispose();
    }

}
