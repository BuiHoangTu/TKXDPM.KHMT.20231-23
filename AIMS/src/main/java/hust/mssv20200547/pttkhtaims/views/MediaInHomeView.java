package hust.mssv20200547.pttkhtaims.views;

import java.io.IOException;
import java.net.URL;

public class MediaInHomeView extends BaseView{
    private static final URL PATH = MediaInHomeView.class.getResource("/hust/mssv20200547/pttkhtaims/media-in-home.fxml");

    /**
     * prepare a new scene
     *
     * @throws IOException          if getSceneURL return null
     * @throws NullPointerException if cant find fxml file
     */
    public MediaInHomeView() throws IOException {
        super();
    }

    @Override
    protected URL getSceneURL() throws NullPointerException {
        return PATH;
    }
}
