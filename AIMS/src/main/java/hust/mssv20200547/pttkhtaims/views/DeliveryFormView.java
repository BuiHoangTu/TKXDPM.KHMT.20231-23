package hust.mssv20200547.pttkhtaims.views;

import hust.mssv20200547.pttkhtaims.controllers.DeliveryFormController;

import java.io.IOException;
import java.net.URL;

public class DeliveryFormView extends BaseView {
    private static final URL PATH = DeliveryFormView.class.getResource("/fxml/delivery-form-view.fxml");

    public DeliveryFormView() throws IOException {
        super(PATH);
    }

    @Override
    public DeliveryFormController getController() {
        return (DeliveryFormController) super.getController();
    }
}
