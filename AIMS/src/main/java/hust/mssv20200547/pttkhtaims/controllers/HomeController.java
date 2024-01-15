package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.models.Media;
import hust.mssv20200547.pttkhtaims.services.IStoreService;
import hust.mssv20200547.pttkhtaims.services.StoreService;
import hust.mssv20200547.pttkhtaims.views.CartView;
import hust.mssv20200547.pttkhtaims.views.MediaInSquareView;
import hust.mssv20200547.pttkhtaims.views.OrderManageView;
import hust.mssv20200547.pttkhtaims.views.HomeView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private static final int MAX_MEDIAS_VIEW = 20;

    private final IStoreService storeService = new StoreService();

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
    @FXML
    private  Button viewOrderButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.numMediaInCart.setText(String.valueOf(0));

        this.setMedias(this.storeService.recommendedMedias(MAX_MEDIAS_VIEW));
    }

    private void setMedias(Map<Media, Long> medias) {
        var mediaEntries = medias.entrySet().stream().toList();
        var vBoxes = new VBoxNext(vBoxMedia1, vBoxMedia2, vBoxMedia3, vBoxMedia4);
        vBoxes.clearAll();

        for (var mediaEntry : mediaEntries) {
            try {
                var mediaView = new MediaInSquareView();
                MediaInSquareController mediaController = mediaView.getController();
                mediaController.setMedia(mediaEntry);
                vBoxes.next().getChildren().add(mediaView.getRoot());
            } catch (IOException e) {
                continue;
            }
        }
    }

    @FXML
    private void searchMedias(ActionEvent ignoredEvent) {
        var selected = this.searchType.getSelectedToggle();

        if (selected == this.radioMenuItemTitle) {
            this.setMedias(storeService.searchMediaTitleInStore(this.textFieldSearch.getText(), MAX_MEDIAS_VIEW));
        }

        if (selected == this.radioMenuItemCategory) {
            this.setMedias(storeService.searchMediaCategoryInStore(this.textFieldSearch.getText(), MAX_MEDIAS_VIEW));
        }
    }

    @FXML
    private void openManageOrder(ActionEvent event) throws Exception{
        // Thêm code xử lý khi nút được nhấp
        var home = new OrderManageView();
        home.switchToOrderManageView();
    }

    public void openCart(MouseEvent ignoredMouseEvent) throws IOException {
        var cartView = new CartView();

        cartView.apply((Stage) cartImage.getScene().getWindow());

        cartView.getController().setCart(AIMS.cart);
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
