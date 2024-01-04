package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.Media;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public interface IMediaSource {
    Map<Media, Long> get(Collection<Media> medias) throws SQLException;

    <M extends Media> M getMediaDetail(M media) throws SQLException;

    Map<Media, Long> searchMediaTitleInStore(String title, int limit) throws SQLException;

    Map<Media, Long> searchMediaCategoryInStore(String category, int limit) throws SQLException;

    void reduceMedias(Map<Media, Long> medias) throws SQLException;
}
