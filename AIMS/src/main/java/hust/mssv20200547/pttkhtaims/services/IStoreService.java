package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.models.Media;

import java.util.Map;

public interface IStoreService {
    Map<Media, Long> recommendedMedias(int limit);
    Map<Media, Long> searchMediaTitleInStore(String title, int limit);
    Map<Media, Long> searchMediaCategoryInStore(String category, int limit);

    void addToCartInStore(int quantityOfMedia, int mediaId);
}
