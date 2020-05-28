package notesapp.notes.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long noteId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "notesInGenre")
    Set<Genre> genres;
}
