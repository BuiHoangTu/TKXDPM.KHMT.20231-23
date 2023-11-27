package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MediaInHomeController implements Initializable {
    private Media m;
    @FXML
    private ImageView imageView;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelPrice;
    @FXML
    private Label labelStoreQuantity;
    @FXML
    private Spinner<Integer> spinnerBuyingQuantity;
    @FXML
    private Button buttonAddToCart;
    private SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spinnerBuyingQuantity.setEditable(true);
        this.spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0);
        spinnerBuyingQuantity.setValueFactory(this.spinnerValueFactory);
    }

    public void setMedia(Map.Entry<Media, Long> mediaEntry) {
        this.m = mediaEntry.getKey();
        this.labelTitle.setText(m.getTitle());
        this.labelPrice.setText(String.valueOf(m.getPrice()));
        this.labelStoreQuantity.setText(String.valueOf(mediaEntry.getValue()));

        this.spinnerValueFactory.setMax(mediaEntry.getValue().intValue());

        if (m instanceof Book) {
            this.imageView.setImage(new Image("/assets/images/book-icon.png"));
        } else if (m instanceof CD) {
            this.imageView.setImage(new Image("/assets/images/cd-icon.png"));
        } else if (m instanceof DigitalVideoDisc) {
            this.imageView.setImage(new Image("/assets/images/dvd-icon.png"));
        }else if (m instanceof LongPlayRecord) {
            this.imageView.setImage(new Image("/assets/images/lpr-icon.png"));
        }

    }

    @FXML
    private void addToCart(ActionEvent actionEvent) {
        int quantityBuy = this.spinnerBuyingQuantity.getValue();
        int quantityLeft = Integer.parseInt(this.labelStoreQuantity.getText()) - quantityBuy;
        this.labelStoreQuantity.setText(String.valueOf(quantityLeft));
        this.spinnerValueFactory.setMax(quantityLeft);
        this.spinnerValueFactory.setValue(0);

        AIMS.cart.put(m, (long) quantityBuy);
    }
}
