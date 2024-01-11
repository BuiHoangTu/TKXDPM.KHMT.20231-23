package hust.mssv20200547.pttkhtaims.views;

import hust.mssv20200547.pttkhtaims.controllers.HomeController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class HomeView extends BaseView {
    private static final URL PATH = HomeView.class.getResource("/fxml/home-screen-view.fxml");

    /**
     * Constructor
     *
     */
    public HomeView() {
        super(PATH);
    }

    @Override
    public HomeController getController() {
        return (HomeController) super.getController();
    }
}
