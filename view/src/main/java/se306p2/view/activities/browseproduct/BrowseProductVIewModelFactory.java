package se306p2.view.activities.browseproduct;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import se306p2.domain.interfaces.entity.ICategory;

public class BrowseProductVIewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private ICategory category;

    public BrowseProductVIewModelFactory(Application application, ICategory category) {
        this.application = application;
        this.category = category;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new BrowseProductViewModel(application, category);
    }
}
