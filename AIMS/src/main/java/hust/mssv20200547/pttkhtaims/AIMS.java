package hust.mssv20200547.pttkhtaims;

import hust.mssv20200547.pttkhtaims.models.Cart;
import hust.mssv20200547.pttkhtaims.views.HomeView;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class AIMS extends Application {
    public static final Cart cart = new Cart();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaximized(true);

        // splash with fading
        StackPane root = FXMLLoader.load(Objects.requireNonNull(AIMS.class.getResource("/fxml/splash-screen-view.fxml")));
        Scene rootScene = new Scene(root);
        primaryStage.setScene(rootScene);
        primaryStage.show();
        // appear
        FadeTransition fadeAppear = new FadeTransition(Duration.seconds(2), root);
        fadeAppear.setFromValue(0);
        fadeAppear.setToValue(1);
        fadeAppear.setCycleCount(1);
        // disappear
        FadeTransition fadeDisappear = new FadeTransition(Duration.seconds(1), root);
        fadeDisappear.setFromValue(1);
        fadeDisappear.setToValue(0);
        fadeDisappear.setCycleCount(1);
        fadeAppear.setOnFinished((event) -> fadeDisappear.play());

        // home
        var home = new HomeView();
        fadeAppear.setOnFinished((event -> home.apply(primaryStage)));

        // app run
        fadeAppear.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
