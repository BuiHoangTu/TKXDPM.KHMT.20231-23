package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.database.IMediaSource;
import hust.mssv20200547.pttkhtaims.exceptions.service.order.NameFormatException;
import hust.mssv20200547.pttkhtaims.exceptions.service.order.PhoneNumberFormatException;
import hust.mssv20200547.pttkhtaims.models.Cart;
import hust.mssv20200547.pttkhtaims.models.Invoice;
import hust.mssv20200547.pttkhtaims.models.Media;
import hust.mssv20200547.pttkhtaims.models.Order;

import java.sql.SQLException;
import java.util.Map;

public interface IPlaceOrderService {
    /**
     * Check if the phone number is in right format
     * @param phoneNumber the phone number
     * @return true if it is in right format
     * <br/> false if it is not
     */
    boolean validatePhoneNumber(String phoneNumber);

    /**
     * Check if the quantity of products in the database is sufficient
     * for those in the cart
     * @param database contains all products in store
     * @param cart contains all products customer is going to purchase
     * @return map of items that are not sufficient with the maximum
     */
    Map<Media, Long> validateProductQuantity(IMediaSource database, Cart cart) throws SQLException;

    boolean validateName(String name);

    long calculateDeliveryFee(Order o);

    Order createOrder(String receiver, String phone, String email, String city, String detailAddr, String ins) throws SQLException, NameFormatException, PhoneNumberFormatException;

    Invoice createInvoice(long totalPrice, long deliveryFee, int orderId) throws SQLException;
}
