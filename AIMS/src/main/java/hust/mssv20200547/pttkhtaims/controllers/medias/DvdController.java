package hust.mssv20200547.pttkhtaims.controllers.medias;

import hust.mssv20200547.pttkhtaims.controllers.MediaInSquareController;
import hust.mssv20200547.pttkhtaims.models.DigitalVideoDisc;
import hust.mssv20200547.pttkhtaims.models.Media;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Map;

public class DvdController extends MediaInSquareController {

    @FXML
    protected Label labelSubtitles;
    @FXML
    protected Label labelDiscFormat;
    @FXML
    protected Label labelDirector;
    @FXML
    protected Label labelRunTime;
    @FXML
    protected Label labelLanguage;
    @FXML
    protected Label labelStudio;
    @FXML
    protected Label labelGenres;
    @FXML
    protected Label labelPublicationDate;

    @Override
    public void setMedia(Map.Entry<Media, Long> mediaEntry) {
        super.setMedia(mediaEntry);

        if (!(mediaEntry.getKey() instanceof DigitalVideoDisc digitalVideoDisc)) return;

        this.labelSubtitles.setText(digitalVideoDisc.getSubtitles());
        this.labelDiscFormat.setText(digitalVideoDisc.getDiscFormat());
        this.labelDirector.setText(digitalVideoDisc.getDirector());
        this.labelPublicationDate.setText(digitalVideoDisc.getPublicationDate().toString());
        this.labelLanguage.setText(digitalVideoDisc.getLanguage());
        this.labelRunTime.setText(digitalVideoDisc.getRuntime().toString());
        this.labelGenres.setText(digitalVideoDisc.getGenre());
        this.labelStudio.setText(digitalVideoDisc.getStudio());

    }
}