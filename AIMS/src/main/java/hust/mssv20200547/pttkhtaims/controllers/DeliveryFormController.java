package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.exceptions.service.order.NameFormatException;
import hust.mssv20200547.pttkhtaims.exceptions.service.order.PhoneNumberFormatException;
import hust.mssv20200547.pttkhtaims.services.IPlaceOrderService;
import hust.mssv20200547.pttkhtaims.services.PlaceOrderService;
import hust.mssv20200547.pttkhtaims.views.BaseView;
import hust.mssv20200547.pttkhtaims.views.InvoiceView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeliveryFormController implements Initializable {
    private BaseView view;

    @FXML
    private TextField tfReceiver;
    @FXML
    private Label errorName;
    @FXML
    private TextField tfPhoneNumber;
    @FXML
    private Label errorNumber;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfDetailAddress;
    @FXML
    private TextField tfInstruction;
    @FXML
    private RadioButton radioNormalDelivery;
    @FXML
    private ToggleGroup radioGroupDeliveryType;
    @FXML
    private RadioButton radioFastDelivery;
    @FXML
    private DatePicker deliveryTime;
    @FXML
    private Label errorProvince;

    private final IPlaceOrderService placeOrderService = new PlaceOrderService();

    @FXML
    private void goBackPage() {
        view.apply((Stage) radioFastDelivery.getScene().getWindow());
    }
    @FXML
    private void updateDeliveryMethodInfo() throws IOException, SQLException {
        var receiver = this.tfReceiver.getText();
        var phone = this.tfPhoneNumber.getText();
        var email = this.tfEmail.getText();
        var city = this.tfCity.getText();
        var detailAddr = this.tfDetailAddress.getText();
        var ins = this.tfInstruction.getText();

        // use order service
        try {
            long totalPrice = AIMS.cart.totalPrice();

            var order = placeOrderService.createOrder(receiver, phone, email, city, detailAddr, ins);
            long deliveryFee = placeOrderService.calculateDeliveryFee(order);

            var invoice = placeOrderService.createInvoice(totalPrice, deliveryFee, order.getOrderId());

            var invoiceView = new InvoiceView();
            invoiceView.getController().setDefaultValues(order, invoice);
            invoiceView.apply((Stage) tfReceiver.getScene().getWindow());

        } catch (NameFormatException | PhoneNumberFormatException ignored) {
            // ignore submit, maybe add pop-up
        }
    }

    public void setPrevPage(BaseView view) {
        this.view = view;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tfReceiver.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                var res = placeOrderService.validateName(this.tfReceiver.getText());
                errorName.setVisible(!res);
            }
        });

        this.tfPhoneNumber.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                var res = placeOrderService.validatePhoneNumber(this.tfPhoneNumber.getText());
                errorNumber.setVisible(!res);
            }
        });
    }
}
