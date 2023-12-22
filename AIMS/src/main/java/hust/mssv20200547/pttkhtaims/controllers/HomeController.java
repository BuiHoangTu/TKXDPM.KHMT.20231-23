package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.database.IDatabase;
import hust.mssv20200547.pttkhtaims.database.MySqlAims;
import hust.mssv20200547.pttkhtaims.models.Media;
import hust.mssv20200547.pttkhtaims.views.MediaInHomeView;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Control coupling với MediaInStoreController 
 */

/**
 * Procedural Cohesion do các hàm searchMedias, setMedias nên để ở class Media
 */
public class HomeController implements Initializable {
    private static final IDatabase MYSQL = new MySqlAims();

    @FXML
    private RadioMenuItem radioMenuItemTitle;
    @FXML
    private ToggleGroup searchType;
    @FXML
    private RadioMenuItem radioMenuItemCategory;
    @FXML
    private ImageView aimsImage;
    @FXML
    private SplitMenuButton splitMenuBtnSearch;
    @FXML
    private ImageView cartImage;
    @FXML
    private Label numMediaInCart;
    @FXML
    private HBox hBoxMedia;
    @FXML
    private VBox vBoxMedia1;
    @FXML
    private VBox vBoxMedia2;
    @FXML
    private VBox vBoxMedia3;
    @FXML
    private VBox vBoxMedia4;
    @FXML
    private TextField textFieldSearch;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.numMediaInCart.setText(String.valueOf(0));

        this.cartImage.addEventHandler(EventType.ROOT, event -> {

        });
    }

    /**
     * Can thiệp vào luồng dữ liệu của Media In Store Controller   
     * @param medias
     * @throws IOException
     */
    public void setMedias(Map<Media, Long> medias) throws IOException {
        var mediaEntries = medias.entrySet().stream().toList();
        var vBoxes = new VBoxNext(vBoxMedia1, vBoxMedia2, vBoxMedia3, vBoxMedia4);
        vBoxes.clearAll();

        for (var mediaEntry : mediaEntries) {
            var mediaView = new MediaInHomeView();
            MediaInHomeController mediaController = mediaView.getController();
            mediaController.setMedia(mediaEntry);
            vBoxes.next().getChildren().add(mediaView.getRoot());
        }
    }

    @FXML
    private void searchMedias(ActionEvent ignoredEvent) throws SQLException, IOException {
        var selected = this.searchType.getSelectedToggle();

        if (selected == this.radioMenuItemTitle) {
            this.setMedias(MYSQL.searchMedias("title", this.textFieldSearch.getText(), 20));
        }

        if (selected == this.radioMenuItemCategory) {
            this.setMedias(MYSQL.searchMedias("category", this.textFieldSearch.getText(), 20));
        }
    }


    private static class VBoxNext {
        private final VBox[] vBoxes;
        private final int count;
        private int index;

        private VBoxNext(VBox... vBoxes) {
            this.vBoxes = vBoxes;
            this.index = -1;
            this.count = vBoxes.length;
        }

        public VBox next() {
            this.index = (this.index + 1) % count;
            return vBoxes[index];
        }

        public void clearAll() {
            for (var each : this.vBoxes) {
                each.getChildren().clear();
            }
        }


    }
}
