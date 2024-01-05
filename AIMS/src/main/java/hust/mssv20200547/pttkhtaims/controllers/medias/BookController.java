package hust.mssv20200547.pttkhtaims.controllers.medias;

import hust.mssv20200547.pttkhtaims.controllers.MediaInSquareController;
import hust.mssv20200547.pttkhtaims.models.Book;
import hust.mssv20200547.pttkhtaims.models.Media;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Map;

public class BookController extends MediaInSquareController {

    @FXML
    protected Label labelAuthors;
    @FXML
    protected Label labelCover;
    @FXML
    protected Label labelPublisher;
    @FXML
    protected Label labelPublicationDate;
    @FXML
    protected Label labelLanguage;
    @FXML
    protected Label labelNumberOfPages;
    @FXML
    protected Label labelGenres;

    @Override
    public void setMedia(Map.Entry<Media, Long> mediaEntry) {
        super.setMedia(mediaEntry);

        if (!(mediaEntry.getKey() instanceof Book book)) return;

        this.labelAuthors.setText(book.getAuthors());
        this.labelCover.setText(book.getCover());
        this.labelPublisher.setText(book.getPublisher());
        this.labelPublicationDate.setText(book.getPublicationDate().toString());
        this.labelLanguage.setText(book.getLanguage());
        this.labelNumberOfPages.setText(book.getLanguage());
        this.labelGenres.setText(book.getGenre());

    }
}
