package hust.mssv20200547.pttkhtaims.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
public class Book extends Media{
    private String genre;
    private String authors;
    private String cover;
    private String publisher;
    private LocalDate publicationDate;
    private String language;
    private long numberOfPages;

    public Book(
            Long id,
            String title,
            long value,
            long price,
            String genre,
            String authors,
            String cover,
            String publisher,
            LocalDate publicationDate,
            String language,
            long numberOfPages) {
        super(id, title, value, price);
        this.genre = genre;
        this.authors = authors;
        this.cover = cover;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.language = language;
        this.numberOfPages = numberOfPages;
    }
}
