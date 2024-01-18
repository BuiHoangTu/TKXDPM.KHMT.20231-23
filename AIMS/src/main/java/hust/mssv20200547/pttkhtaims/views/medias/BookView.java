package hust.mssv20200547.pttkhtaims.views.medias;

import hust.mssv20200547.pttkhtaims.controllers.medias.BookController;
import hust.mssv20200547.pttkhtaims.views.BaseView;

import java.io.IOException;
import java.net.URL;

public class BookView extends DetailView {
    private static final URL PATH = BookView.class.getResource("/fxml/media-detail-view/book-view.fxml");

    /**
     * prepare a new scene
     *
     */
    public BookView() {
        super(PATH);
    }

    @Override
    public BookController getController() {
        return (BookController) super.getController();
    }
}
