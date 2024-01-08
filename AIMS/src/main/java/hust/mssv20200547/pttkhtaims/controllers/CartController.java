package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.models.Cart;
import hust.mssv20200547.pttkhtaims.views.DeliveryFormView;
import hust.mssv20200547.pttkhtaims.views.MediaInVerticalView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    @FXML
    private Label pageTitle;
    @FXML
    private ImageView aimsImage;
    @FXML
    private VBox vboxCart;
    @FXML
    private Label labelSubtotal;
    @FXML
    private Label trashlabelSubtotal;
    @FXML
    private Label labelVAT;
    @FXML
    private Label labelAmount;
    // TODO one of these label is not bind to right elems


    @FXML
    private void placeOrder(ActionEvent actionEvent) throws IOException {
        var deliveryForm = new DeliveryFormView();
        deliveryForm.apply((Stage) this.labelAmount.getScene().getWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.labelAmount.setText(String.valueOf(AIMS.cart.totalPrice()));
    }

    public void setCart(Cart cart) throws IOException {
        var medias = this.vboxCart.getChildren();
        medias.clear();

        for (var mediaEntry : cart.entrySet()) {
            var mediaView = new MediaInVerticalView();
            MediaInVerticalController mediaController = mediaView.getController();
            mediaController.setMedia(mediaEntry);
            medias.add(mediaView.getRoot());
        }

        long subtotal = cart.totalPrice();
        this.labelSubtotal.setText(String.valueOf(subtotal));
        this.labelVAT.setText(String.valueOf(subtotal / 10));
        this.labelAmount.setText(String.valueOf(subtotal * 110 / 100));
    }
}
