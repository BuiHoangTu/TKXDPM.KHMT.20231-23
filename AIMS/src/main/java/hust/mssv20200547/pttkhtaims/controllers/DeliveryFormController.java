package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.models.DeliveryInfo;
import hust.mssv20200547.pttkhtaims.models.Invoice;
import hust.mssv20200547.pttkhtaims.models.Order;
import hust.mssv20200547.pttkhtaims.services.IPlaceOrderService;
import hust.mssv20200547.pttkhtaims.services.PlaceOrderService;
import hust.mssv20200547.pttkhtaims.subsystem.bank.vnpay.VnPay;
import hust.mssv20200547.pttkhtaims.views.BaseView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
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

    private IPlaceOrderService placeOrderService = new PlaceOrderService();

    @FXML
    private void goBackPage() {
        view.apply((Stage) radioFastDelivery.getScene().getWindow());
    }
    @FXML
    private void updateDeliveryMethodInfo() {
        // TODO: save to invoice,
        // TODO: switch to invoice screen first
        var receiver = this.tfReceiver.getText();
        var phone = this.tfPhoneNumber.getText();
        var email = this.tfEmail.getText();
        var city = this.tfCity.getText();
        var detailAddr = this.tfDetailAddress.getText();
        var ins = this.tfInstruction.getText();

        if (! placeOrderService.validateName(receiver)) return;
        if (! placeOrderService.validatePhoneNumber(phone)) return;

        DeliveryInfo deliveryInfo = new DeliveryInfo(receiver, phone, email, city, detailAddr, ins);
        long totalPrice = AIMS.cart.totalPrice();
        Order order = new Order(AIMS.cart, deliveryInfo);

        // TODO: save db

        long deliveryFee = placeOrderService.calculateDeliveryFee(order);
        Invoice invoice = new Invoice(totalPrice, deliveryFee);
        // TODO: save to db
        // TODO: replace below with get generatedId
        invoice.setOrderId(20);
        // TODO: put this in suitable class
        var res = new VnPay().makePaymentTransaction(invoice, "Pay for AIMS");
        // TODO: go to suitable class
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
                var res = placeOrderService.validateName(this.tfPhoneNumber.getText());
                errorNumber.setVisible(!res);
            }
        });
    }
}
