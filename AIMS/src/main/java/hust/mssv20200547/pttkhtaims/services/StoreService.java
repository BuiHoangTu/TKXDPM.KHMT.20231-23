package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.database.IMediaSource;
import hust.mssv20200547.pttkhtaims.database.implementation.mysql.MediaSourceMySql;
import hust.mssv20200547.pttkhtaims.models.Media;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class StoreService implements IStoreService {
    private final IMediaSource mediaSource = new MediaSourceMySql();

    @Override
    public Map<Media, Long> recommendedMedias(int limit) {
        try {
            return mediaSource.searchMediaTitleInStore("", limit);
        } catch (SQLException e) {
            return Collections.emptyMap();
        }
    }

    @Override
    public Map<Media, Long> searchMediaTitleInStore(String title, int limit) {
        try {
            return mediaSource.searchMediaTitleInStore(title, limit);
        } catch (SQLException e) {
            return Collections.emptyMap();
        }
    }

    @Override
    public Map<Media, Long> searchMediaCategoryInStore(String category, int limit) {
        try {
            return mediaSource.searchMediaCategoryInStore(category, limit);
        } catch (SQLException e) {
            return Collections.emptyMap();
        }
    }
}
