package hust.mssv20200547.pttkhtaims.controllers.medias;

import hust.mssv20200547.pttkhtaims.controllers.MediaInSquareController;
import hust.mssv20200547.pttkhtaims.models.Book;
import hust.mssv20200547.pttkhtaims.models.CD;
import hust.mssv20200547.pttkhtaims.models.Media;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Map;

public class CdController extends MediaInSquareController {

    @FXML
    protected Label labelArtists;
    @FXML
    protected Label labelRecordLabel;
    @FXML
    protected Label labelTrackList;
    @FXML
    protected Label labelPublicationDate;
    @FXML
    protected Label labelGenres;

    @Override
    public void setMedia(Map.Entry<Media, Long> mediaEntry) {
        super.setMedia(mediaEntry);

        if (!(mediaEntry.getKey() instanceof CD cd)) return;

        this.labelArtists.setText(cd.getArtist());
        this.labelRecordLabel.setText(cd.getRecordLabel());
        this.labelTrackList.setText(cd.getTrackList().toString());
        this.labelPublicationDate.setText(cd.getPublicationDate().toString());
        this.labelGenres.setText(cd.getGenre());

    }
}