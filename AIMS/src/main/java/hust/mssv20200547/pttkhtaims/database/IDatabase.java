package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.Media;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

// Sử dụng interface để giảm sự phụ thuộc giữa class bậc cao và thấp
public interface IDatabase {
    Map<Media, Long> get(Collection<Media> medias) throws SQLException;

    <M extends Media> M getMediaDetail(M media) throws SQLException;

    Map<Media, Long> searchMedias(String searchType, String searchValue, int quantity) throws SQLException;
}
