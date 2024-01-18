package hust.mssv20200547.pttkhtaims.database.media;

import hust.mssv20200547.pttkhtaims.models.LongPlayRecord;
import hust.mssv20200547.pttkhtaims.models.Media;

public interface ILprSource {
    LongPlayRecord getMediaDetail(Media media);
}
