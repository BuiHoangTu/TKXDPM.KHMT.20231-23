package hust.mssv20200547.pttkhtaims.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class LongPlayRecord extends Media{
    private String director;
    private LocalTime runtime;
    private String studio;
    private LocalDate publicationDate;
    private String discFormat;
    private String language;
    private String subtitles;
    private String genre;

    public LongPlayRecord(
            Long id,
            String title,
            long value,
            long price,
            String director,
            LocalTime runtime,
            String studio,
            LocalDate publicationDate,
            String discFormat,
            String language,
            String subtitles,
            String genre) {
        super(id, title, value, price);
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.publicationDate = publicationDate;
        this.discFormat = discFormat;
        this.language = language;
        this.subtitles = subtitles;
        this.genre = genre;
    }
}
