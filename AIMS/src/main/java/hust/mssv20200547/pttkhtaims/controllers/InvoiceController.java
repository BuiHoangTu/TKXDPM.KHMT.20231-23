package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.models.Invoice;
import hust.mssv20200547.pttkhtaims.models.Order;
import hust.mssv20200547.pttkhtaims.services.IPayOrderService;
import hust.mssv20200547.pttkhtaims.services.PayOrderService;
import hust.mssv20200547.pttkhtaims.subsystem.bank.IPaymentTransaction;
import hust.mssv20200547.pttkhtaims.subsystem.bank.exceptions.pay.*;
import hust.mssv20200547.pttkhtaims.subsystem.bank.implement.vnpay.VnPay;
import hust.mssv20200547.pttkhtaims.views.MediaInVerticalView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {
    @FXML
    private Label pageTitle;
    @FXML
    private Label labelSubTotal;
    @FXML
    private Label labelShipFee;
    @FXML
    private Label labelPhone;
    @FXML
    private Label labelTotal;
    @FXML
    private Label labelName;
    @FXML
    private Label labelCity;
    @FXML
    private Label labelAddress;
    @FXML
    private Label labelShipInstruction;
    @FXML
    private ScrollPane vboxIte;
    @FXML
    private VBox vboxItems;

    private final IPayOrderService payOrderService = new PayOrderService();

    private Invoice invoice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setDefaultValues(Order order, Invoice invoice) throws IOException {
        this.invoice = invoice;

        var deliveryInfo = order.getDeliveryInfo();

        this.labelAddress.setText(deliveryInfo.getDetailedAddress());
        this.labelName.setText(deliveryInfo.getReceiver());
        this.labelCity.setText(deliveryInfo.getCityAddress());
        this.labelPhone.setText(deliveryInfo.getPhoneNumber());
        this.labelShipInstruction.setText(deliveryInfo.getInstruction());

        long subTotal = invoice.getPriceNoVat();
        this.labelSubTotal.setText(String.valueOf(subTotal));

        long deliveryFee = invoice.getDeliveryFee();
        this.labelShipFee.setText(String.valueOf(deliveryFee));

        this.labelTotal.setText(String.valueOf(invoice.getTotalFee()));

        // setup view
        List<Node> itemViews = vboxItems.getChildren();
        itemViews.clear();

        for (var mediaEntry : order.getMediaInOrder().entrySet()) {
            var mediaView = new MediaInVerticalView();
            var mediaController = mediaView.getController();
            mediaController.setMedia(mediaEntry);
            itemViews.add(mediaView.getRoot());
        }
    }

    @FXML
    public void confirmInvoice() {
        // wait for result
        var root = this.labelAddress.getScene().getRoot();
        root.setDisable(true);
        try {
            IPaymentTransaction res = new VnPay().makePaymentTransaction(invoice, "Pay for AIMS");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText(res.getTransactionContent());
            alert.showAndWait();

            payOrderService.savePaymentTransaction(res);
        } catch (AnonymousTransactionException | UnrecognizedException | TransactionNotDoneException | ClientBankException | TransactionFailedException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failed");
            alert.setHeaderText("Reason");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (SQLException ignore) {

        } finally {
            root.setDisable(false);
        }


    }
}
