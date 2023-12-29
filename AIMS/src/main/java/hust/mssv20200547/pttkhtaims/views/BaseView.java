package hust.mssv20200547.pttkhtaims.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public abstract class BaseView {
    protected Scene scene;
    protected final Parent root;
    protected final FXMLLoader loader;

    /**
     * prepare a new scene
     * @throws IOException if getSceneURL return null
     * @throws NullPointerException if cant find fxml file
     */
    public BaseView(URL sceneUrl) throws IOException {
        this.loader = new FXMLLoader(sceneUrl);
        this.root = loader.load();
    }

    /**
     * Apply the scene into the stage
     * @param stage stage that need to change scene
     */
    public void apply(Stage stage) {
        var max = stage.isMaximized();
        var min = stage.isIconified();

        stage.setScene(this.scene);

        if (max) {
            stage.setMaximized(true);
        }
        if (min) {
            stage.setIconified(true);
        }
    }

    public Object getController() {
        return this.loader.getController();
    }

    public Scene getScene() {
        if (scene == null) scene = new Scene(this.root);
        return scene;
    }

    public Parent getRoot() {
        return root;
    }
}
