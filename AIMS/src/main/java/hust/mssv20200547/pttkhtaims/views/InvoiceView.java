package hust.mssv20200547.pttkhtaims.views;

import hust.mssv20200547.pttkhtaims.controllers.InvoiceController;

import java.net.URL;

public class InvoiceView extends BaseView{
    private static final URL PATH = InvoiceView.class.getResource("/fxml/invoice-screen-view.fxml");
    /**
     * prepare a new scene
     *
     */
    public InvoiceView() {
        super(PATH);
    }

    @Override
    public InvoiceController getController() {
        return (InvoiceController) super.getController();
    }
}
