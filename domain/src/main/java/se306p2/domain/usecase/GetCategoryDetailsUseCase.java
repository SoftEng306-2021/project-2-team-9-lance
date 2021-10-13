package se306p2.domain.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import se306p2.domain.RepositoryRouter;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.usecase.IGetCategoryDetailsUseCase;

public class GetCategoryDetailsUseCase implements IGetCategoryDetailsUseCase {
    public Single<List<ICategory>> getCategoryDetails() {
        return Single.create(emitter -> {
            Thread thread = new Thread(() -> {
                try {
                    List<ICategory> categories = RepositoryRouter.getCategoryRepository().getCategories();
                    if (categories == null) {
                        emitter.onError(new NullPointerException());
                        return;
                    }
                    emitter.onSuccess(categories);
                } catch (Exception e) {
                    emitter.onError(e);
                }
            });
            thread.start();
        });
    }
}
