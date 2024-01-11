package hust.mssv20200547.pttkhtaims.database.media;

import hust.mssv20200547.pttkhtaims.models.DigitalVideoDisc;
import hust.mssv20200547.pttkhtaims.models.Media;

public interface IDvdSource {
    DigitalVideoDisc getMediaDetail(Media media);
}
