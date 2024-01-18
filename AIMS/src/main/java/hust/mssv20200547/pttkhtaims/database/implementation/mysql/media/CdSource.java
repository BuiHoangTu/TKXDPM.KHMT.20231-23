package hust.mssv20200547.pttkhtaims.database.implementation.mysql.media;

import hust.mssv20200547.pttkhtaims.database.media.ICdSource;
import hust.mssv20200547.pttkhtaims.models.CD;
import hust.mssv20200547.pttkhtaims.models.Media;

import java.sql.SQLException;
import java.util.List;

public class CdSource extends MediaSourceMySql implements ICdSource {
    @Override
    public CD getMediaDetail(Media media) {
        try {
            var mysql = openConnection();

            var preparedStatement = mysql.prepareStatement(
                    "Select artists, recordLabel, trackList, genres, publicationDate " +
                            "From cd " +
                            "Where id = ?"
            );
            preparedStatement.setLong(1, media.getId());

            var cd = new CD(media);

            var rsIte = new ResultSetColIterator(preparedStatement.executeQuery());
            if (rsIte.next()) {
                cd.setArtist(rsIte.getString());
                cd.setRecordLabel(rsIte.getString());
                cd.setTrackList(List.of(rsIte.getString().split(",")));
                cd.setGenre(rsIte.getString());
                cd.setPublicationDate(rsIte.getDate().toLocalDate());
            }
            return cd;
        } catch (SQLException e) {
            return null;
        }
    }
}
