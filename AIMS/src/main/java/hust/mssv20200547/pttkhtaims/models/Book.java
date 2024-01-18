package hust.mssv20200547.pttkhtaims.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Book extends Media{
    public static final String TYPE = "BOOK";

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
        super(id, title, value, price, TYPE);
        this.genre = genre;
        this.authors = authors;
        this.cover = cover;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.language = language;
        this.numberOfPages = numberOfPages;
    }

    public Book(Media media) {
        this(
                media.getId(),
                media.getTitle(),
                media.getValue(),
                media.getPrice(),
                null,
                null,
                null,
                null,
                null,
                null,
                0
        );
    }
}
