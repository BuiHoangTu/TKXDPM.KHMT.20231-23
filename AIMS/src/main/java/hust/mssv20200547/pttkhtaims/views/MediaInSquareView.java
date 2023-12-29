package hust.mssv20200547.pttkhtaims.views;

import hust.mssv20200547.pttkhtaims.controllers.MediaInSquareController;

import java.io.IOException;
import java.net.URL;

public class MediaInSquareView extends BaseView{
    private static final URL PATH = MediaInSquareView.class.getResource("/fxml/media-in-square-view.fxml");

    /**
     * prepare a new scene
     *
     * @throws IOException          if getSceneURL return null
     * @throws NullPointerException if cant find fxml file
     */
    public MediaInSquareView() throws IOException {
        super(PATH);
    }

    @Override
    public MediaInSquareController getController() {
        return (MediaInSquareController) super.getController();
    }
}
