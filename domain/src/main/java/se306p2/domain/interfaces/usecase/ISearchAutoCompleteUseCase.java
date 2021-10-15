package se306p2.domain.interfaces.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface ISearchAutoCompleteUseCase {
    Single<List<String>> searchAutoComplete(String searchTerm);
}
