package hust.mssv20200547.pttkhtaims.database.implementation.mysql.media;

import hust.mssv20200547.pttkhtaims.database.media.IDvdSource;
import hust.mssv20200547.pttkhtaims.models.DigitalVideoDisc;
import hust.mssv20200547.pttkhtaims.models.Media;

import java.sql.SQLException;

public class DvdSource extends MediaSourceMySql implements IDvdSource {
    @Override
    public DigitalVideoDisc getMediaDetail(Media media) {
        try {
            var mysql = openConnection();
            var preparedStatement = mysql.prepareStatement(
                    "Select discFormat, director, runTime, studio, language, subtitles, publicationDate, genres " +
                            "From digital_video_disc " +
                            "Where id = ?"
            );
            preparedStatement.setLong(1, media.getId());

            var dvd = new DigitalVideoDisc(media);

            var rsIte = new ResultSetColIterator(preparedStatement.executeQuery());
            if (rsIte.next()) {
                dvd.setDiscFormat(rsIte.getString());
                dvd.setDirector(rsIte.getString());
                dvd.setRuntime(rsIte.getTime().toLocalTime());
                dvd.setStudio(rsIte.getString());
                dvd.setLanguage(rsIte.getString());
                dvd.setSubtitles(rsIte.getString());
                dvd.setPublicationDate(rsIte.getDate().toLocalDate());
                dvd.setGenre(rsIte.getString());
            }
            return dvd;
        } catch (SQLException e) {
            return null;
        }
    }
}
