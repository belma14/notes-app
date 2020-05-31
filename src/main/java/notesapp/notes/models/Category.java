package notesapp.notes.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    @Column(name = "title")
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Column(name = "description")
    @Size(min = 10, max = 200, message = "Description must be between 10 and 200 characters")
    private String description;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Note> notes;

    public Category() {
    }

    public Category(long categoryId, String title, String description) {
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Category [id=%d, title=%s, description=%s]", categoryId, title, description);
    }
}
