package hust.mssv20200547.pttkhtaims.views.medias;

import hust.mssv20200547.pttkhtaims.controllers.MediaInSquareController;
import hust.mssv20200547.pttkhtaims.views.BaseView;

import java.io.IOException;
import java.net.URL;

public abstract class DetailView extends BaseView {
    /**
     * prepare a new scene
     *
     * @param sceneUrl
     */
    public DetailView(URL sceneUrl) {
        super(sceneUrl);
    }

    @Override
    public MediaInSquareController getController() {
        return (MediaInSquareController) super.getController();
    }
}
