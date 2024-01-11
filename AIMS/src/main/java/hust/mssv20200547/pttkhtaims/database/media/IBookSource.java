package hust.mssv20200547.pttkhtaims.database.media;

import hust.mssv20200547.pttkhtaims.models.Book;
import hust.mssv20200547.pttkhtaims.models.Media;

public interface IBookSource {
    Book getMediaDetail(Media media);
}
