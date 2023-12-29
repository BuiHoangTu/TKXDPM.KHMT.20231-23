package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.Media;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public interface IMediaSource {
    Map<Media, Long> get(Collection<Media> medias) throws SQLException;

    <M extends Media> M getMediaDetail(M media) throws SQLException;

    Map<Media, Long> searchMedias(String searchType, String searchValue, int quantity) throws SQLException;
}
