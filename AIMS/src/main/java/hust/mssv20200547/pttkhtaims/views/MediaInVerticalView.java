package hust.mssv20200547.pttkhtaims.views;

import hust.mssv20200547.pttkhtaims.controllers.MediaInVerticalController;

import java.io.IOException;
import java.net.URL;

public class MediaInVerticalView extends BaseView{
    private static final URL PATH = MediaInVerticalView.class.getResource("/fxml/media-in-vertical-view.fxml");

    public MediaInVerticalView() {
        super(PATH);
    }

    @Override
    public MediaInVerticalController getController() {
        return (MediaInVerticalController) super.getController();
    }
}
