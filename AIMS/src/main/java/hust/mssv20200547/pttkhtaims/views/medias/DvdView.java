package hust.mssv20200547.pttkhtaims.views.medias;

import hust.mssv20200547.pttkhtaims.controllers.medias.DvdController;
import hust.mssv20200547.pttkhtaims.views.BaseView;

import java.io.IOException;
import java.net.URL;

public class DvdView extends BaseView {
    private static final URL PATH = DvdView.class.getResource("/fxml/media-detail-view/dvd-view.fxml");

    /**
     * prepare a new scene
     *
     * @throws IOException          if getSceneURL return null
     * @throws NullPointerException if cant find fxml file
     */
    public DvdView() throws IOException {
        super(PATH);
    }

    @Override
    public DvdController getController() {
        return (DvdController) super.getController();
    }
}
