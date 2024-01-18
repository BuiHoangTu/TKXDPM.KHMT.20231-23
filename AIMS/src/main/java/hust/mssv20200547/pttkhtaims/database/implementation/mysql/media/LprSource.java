package hust.mssv20200547.pttkhtaims.database.implementation.mysql.media;

import hust.mssv20200547.pttkhtaims.database.media.ILprSource;
import hust.mssv20200547.pttkhtaims.models.LongPlayRecord;
import hust.mssv20200547.pttkhtaims.models.Media;

import java.sql.SQLException;

public class LprSource extends MediaSourceMySql implements ILprSource {
    @Override
    public LongPlayRecord getMediaDetail(Media media) {
        try {
            var mysql = openConnection();
            var preparedStatement = mysql.prepareStatement(
                    "Select discFormat, director, runTime, studio, language, subtitles, publicationDate, genres " +
                            "From long_play_record " +
                            "Where id = ?"
            );
            preparedStatement.setLong(1, media.getId());

            var lpr = new LongPlayRecord(media);

            var rsIte = new ResultSetColIterator(preparedStatement.executeQuery());
            if (rsIte.next()) {
                lpr.setDiscFormat(rsIte.getString());
                lpr.setDirector(rsIte.getString());
                lpr.setRuntime(rsIte.getTime().toLocalTime());
                lpr.setStudio(rsIte.getString());
                lpr.setLanguage(rsIte.getString());
                lpr.setSubtitles(rsIte.getString());
                lpr.setPublicationDate(rsIte.getDate().toLocalDate());
                lpr.setGenre(rsIte.getString());
            }
            return lpr;

        } catch (SQLException e) {
            return null;
        }
    }
}
