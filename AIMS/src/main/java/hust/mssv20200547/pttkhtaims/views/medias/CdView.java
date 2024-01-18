package hust.mssv20200547.pttkhtaims.views.medias;

import hust.mssv20200547.pttkhtaims.controllers.medias.BookController;
import hust.mssv20200547.pttkhtaims.controllers.medias.CdController;
import hust.mssv20200547.pttkhtaims.views.BaseView;

import java.io.IOException;
import java.net.URL;

public class CdView extends DetailView {
    private static final URL PATH = CdView.class.getResource("/fxml/media-detail-view/cd-view.fxml");

    /**
     * prepare a new scene
     *
     */
    public CdView() {
        super(PATH);
    }

    @Override
    public CdController getController() {
        return (CdController) super.getController();
    }
}
