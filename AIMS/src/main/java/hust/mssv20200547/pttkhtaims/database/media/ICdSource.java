package hust.mssv20200547.pttkhtaims.database.media;

import hust.mssv20200547.pttkhtaims.models.CD;
import hust.mssv20200547.pttkhtaims.models.Media;

public interface ICdSource {
    CD getMediaDetail(Media media);
}
