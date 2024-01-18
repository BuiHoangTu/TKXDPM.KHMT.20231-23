package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.database.implementation.mysql.media.BookSource;
import hust.mssv20200547.pttkhtaims.database.implementation.mysql.media.CdSource;
import hust.mssv20200547.pttkhtaims.database.implementation.mysql.media.DvdSource;
import hust.mssv20200547.pttkhtaims.database.implementation.mysql.media.LprSource;
import hust.mssv20200547.pttkhtaims.database.media.IMediaSource;
import hust.mssv20200547.pttkhtaims.models.*;
import hust.mssv20200547.pttkhtaims.views.medias.*;
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

import java.net.URL;
import java.sql.SQLException;
import java.util.AbstractMap;
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

    private IMediaSource mediaSource;
    private DetailView detailView;

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

        switch (m.getType()) {
            case Book.TYPE -> {
                this.imageView.setImage(new Image("/assets/images/book-icon.png"));
                this.mediaSource = new BookSource();
                this.detailView = new BookView();
            }
            case CD.TYPE -> {
                this.imageView.setImage(new Image("/assets/images/cd-icon.png"));
                this.mediaSource = new CdSource();
                this.detailView = new CdView();

            }
            case DigitalVideoDisc.TYPE -> {
                this.imageView.setImage(new Image("/assets/images/dvd-icon.png"));
                this.mediaSource = new DvdSource();
                this.detailView = new DvdView();

            }
            case LongPlayRecord.TYPE -> {
                this.imageView.setImage(new Image("/assets/images/lpr-icon.png"));
                this.mediaSource = new LprSource();
                this.detailView = new LrpView();
            }
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
    private void goToMediaDetail(MouseEvent ignoredMouseEvent) {
        var m = entry.getKey();
        // load others properties
        try {
            m = mediaSource.getMediaDetail(m);
        } catch (SQLException ignored) {
        }

        detailView.getController().setMedia(new AbstractMap.SimpleEntry<>(m, entry.getValue()));

        detailView.apply((Stage) labelTitle.getScene().getWindow());
    }
}
