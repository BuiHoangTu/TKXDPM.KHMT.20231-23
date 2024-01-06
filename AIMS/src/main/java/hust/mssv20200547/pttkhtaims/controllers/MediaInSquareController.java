package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.database.IMediaSource;
import hust.mssv20200547.pttkhtaims.database.mysql.MediaSourceMySql;
import hust.mssv20200547.pttkhtaims.models.*;
import hust.mssv20200547.pttkhtaims.views.BaseView;
import hust.mssv20200547.pttkhtaims.views.medias.BookView;
import hust.mssv20200547.pttkhtaims.views.medias.CdView;
import hust.mssv20200547.pttkhtaims.views.medias.DvdView;
import hust.mssv20200547.pttkhtaims.views.medias.LrpView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class MediaInSquareController implements Initializable {
    protected Map.Entry<Media, Long> entry;
    @FXML
    protected ImageView imageView;
    @FXML
    protected Label labelTitle;
    @FXML
    protected Label labelPrice;
    @FXML
    protected Label labelStoreQuantity;
    @FXML
    protected Spinner<Integer> spinnerBuyingQuantity;
    @FXML
    protected Button buttonAddToCart;
    protected SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory;

    private IMediaSource mediaSource = new MediaSourceMySql();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spinnerBuyingQuantity.setEditable(true);
        this.spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0);
        spinnerBuyingQuantity.setValueFactory(this.spinnerValueFactory);
    }

    public void setMedia(Map.Entry<Media, Long> mediaEntry) {
        this.entry = mediaEntry;

        var m = entry.getKey();
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
    protected void addToCart(ActionEvent ignoredActionEvent) {
        int quantityBuy = this.spinnerBuyingQuantity.getValue();
        int quantityLeft = Integer.parseInt(this.labelStoreQuantity.getText()) - quantityBuy;
        this.labelStoreQuantity.setText(String.valueOf(quantityLeft));
        this.spinnerValueFactory.setMax(quantityLeft);
        this.spinnerValueFactory.setValue(0);

        AIMS.cart.put(entry.getKey(), (long) quantityBuy);
    }

    @FXML
    private void goToMediaDetail(MouseEvent ignoredMouseEvent) throws IOException, SQLException {
        var m = entry.getKey();
        // load others properties
        mediaSource.getMediaDetail(m);

        BaseView baseView;
        if (m instanceof Book) {
            var bookView = new BookView();
            bookView.getController().setMedia(entry);
            baseView = bookView;
        } else if (m instanceof CD) {
            var cdView = new CdView();
            cdView.getController().setMedia(entry);
            baseView = cdView;
        } else if (m instanceof DigitalVideoDisc) {
            var dvdView = new DvdView();
            dvdView.getController().setMedia(entry);
            baseView = dvdView;
        } else if (m instanceof LongPlayRecord) {
            var lrpView = new LrpView();
            lrpView.getController().setMedia(entry);
            baseView = lrpView;
        } else return;

        baseView.apply((Stage) labelTitle.getScene().getWindow());
    }
}
