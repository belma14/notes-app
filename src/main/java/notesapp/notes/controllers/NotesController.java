package notesapp.notes.controllers;

import notesapp.notes.models.Category;
import notesapp.notes.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/index")
    public String index(Model model) {
        List<Category> categories = this.categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "index";
    }
}
