package notesapp.notes.services;

import notesapp.notes.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getCategories();
    Optional<Category> findById(Long id);
    Category save(Category category);
    Category update(Category category);
    void deleteById(Long id);
}
