package hust.mssv20200547.pttkhtaims.views;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.controllers.CartController;

import java.io.IOException;
import java.net.URL;

public class CartView extends BaseView{
    private static final URL PATH = CartView.class.getResource("/fxml/cart-screen-view.fxml");

    /**
     * prepare a new scene
     *
     */
    public CartView() {
        super(PATH);
        this.getController().setCart(AIMS.cart);
    }

    @Override
    public CartController getController() {
        return (CartController) super.getController();
    }
}
