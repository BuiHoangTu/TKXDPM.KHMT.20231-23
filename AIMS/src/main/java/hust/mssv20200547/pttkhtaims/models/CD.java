package hust.mssv20200547.pttkhtaims.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CD extends Media{
    public static final String TYPE = "CD";

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
        super(id, title, value, price, TYPE);
        this.trackList = trackList;
        this.recordLabel = recordLabel;
        this.artist = artist;
        this.genre = genre;
        this.publicationDate = publicationDate;
    }

    public CD(Media media) {
        this(
                media.getId(),
                media.getTitle(),
                media.getValue(),
                media.getPrice(),
                null,
                null,
                null,
                null,
                null
        );
    }
}
