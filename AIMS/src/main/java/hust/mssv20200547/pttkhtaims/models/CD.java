package hust.mssv20200547.pttkhtaims.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CD extends Media{
    private List<String> trackList;
    private String recordLabel;
    private String artist;
    private String genre;
    private LocalDate publicationDate;

    public CD(
            Long id,
            String title,
            long value,
            long price,
            List<String> trackList,
            String recordLabel,
            String artist,
            String genre,
            LocalDate publicationDate) {
        super(id, title, value, price);
        this.trackList = trackList;
        this.recordLabel = recordLabel;
        this.artist = artist;
        this.genre = genre;
        this.publicationDate = publicationDate;
    }
}
