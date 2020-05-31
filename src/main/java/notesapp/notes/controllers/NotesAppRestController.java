package notesapp.notes.controllers;

import notesapp.notes.models.Category;
import notesapp.notes.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes-rest")
public class NotesAppRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(this.categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") long id) {
        Optional<Category> category = this.categoryService.findById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/categories")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping(value = "/categories/{id}")
    ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Long id) {
        Optional<Category> existingCategory = categoryService.findById(id);

        if (existingCategory.isPresent()) {
            Category newCategory = existingCategory.get();
            newCategory.setDescription(category.getDescription());
            newCategory.setTitle(category.getTitle());
            return new ResponseEntity<>(this.categoryService.update(newCategory), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        Optional<Category> category = this.categoryService.findById(id);
        if (category.isPresent()) {
            this.categoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
