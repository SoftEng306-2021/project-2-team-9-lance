package se306p2.domain.interfaces.usecase;

import java.util.List;

public interface ISearchAutoCompleteUseCase {
    List<String> searchAutoComplete(String searchTerm);
}
