package se306p2.domain.interfaces.usecase;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

/**
 * Interface for the Search Auto Complete Use Case
 */
public interface ISearchAutoCompleteUseCase {
    Single<List<String>> searchAutoComplete(String searchTerm);
}
