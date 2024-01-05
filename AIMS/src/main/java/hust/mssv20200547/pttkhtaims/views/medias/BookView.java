package hust.mssv20200547.pttkhtaims.views.medias;

import hust.mssv20200547.pttkhtaims.controllers.medias.BookController;
import hust.mssv20200547.pttkhtaims.views.BaseView;

import java.io.IOException;
import java.net.URL;

public class BookView extends BaseView {
    private static final URL PATH = BookView.class.getResource("/fxml/media-detail-view/book-view.fxml");

    /**
     * prepare a new scene
     *
     * @throws IOException          if getSceneURL return null
     * @throws NullPointerException if cant find fxml file
     */
    public BookView() throws IOException {
        super(PATH);
    }

    @Override
    public BookController getController() {
        return (BookController) super.getController();
    }
}
