package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.database.*;
import hust.mssv20200547.pttkhtaims.database.mysql.*;
import hust.mssv20200547.pttkhtaims.exceptions.service.order.NameFormatException;
import hust.mssv20200547.pttkhtaims.exceptions.service.order.PhoneNumberFormatException;
import hust.mssv20200547.pttkhtaims.models.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PlaceOrderService implements IPlaceOrderService {
    private final IDeliveryInfoSource deliveryInfoSource = new DeliveryInfoSource();
    private final IOrderSource orderSource = new OrderSource();
    private final IPaymentInfoSource paymentInfoSource = new PaymentInfoSource();
    private final IInvoiceSource invoiceSource = new InvoiceSource();
    private final IMediaSource mediaSource = new MediaSourceMySql();

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        final String phoneRegex = "^0\\d{9}$";
        final Pattern regex = Pattern.compile(phoneRegex);

        return regex.matcher(phoneNumber).matches();
    }

    @Override
    public Map<Media, Long> validateProductQuantity(IMediaSource database, Cart cart) throws SQLException {
        Map<Media, Long> infeasibleProducts = new HashMap<>();

        Map<Media, Long> inDBMedias = database.get(cart.keySet());

        for (var itemEntry : cart.entrySet()) {
            var media = itemEntry.getKey();
            long cartQuantity = itemEntry.getValue();

            Long dbQuantity = inDBMedias.get(media);

            if (dbQuantity == null) dbQuantity = 0L;

            if (dbQuantity < cartQuantity) {
                infeasibleProducts.put(media, dbQuantity);
                System.out.println("Putting " + media + "-" + dbQuantity);
            }
        }

        return infeasibleProducts;
    }

    @Override
    public boolean validateName(String name){
        final String nameRegex = "[a-zA-Z -]+$";
        final Pattern regex = Pattern.compile(nameRegex);

        return regex.matcher(name).matches();
    }

    @Override
    public long calculateDeliveryFee(Order o) {
        return 0;
    }

    @Override
    public Order createOrder(
            String receiver,
            String phone,
            String email,
            String city,
            String detailAddr,
            String ins
    ) throws
            SQLException,
            NameFormatException,
            PhoneNumberFormatException
    {
        if (! this.validateName(receiver)) throw new NameFormatException();
        if (! this.validatePhoneNumber(phone)) throw new PhoneNumberFormatException();

        DeliveryInfo deliveryInfo = new DeliveryInfo(receiver, phone, email, city, detailAddr, ins);
        Order order = new Order(AIMS.cart, deliveryInfo);
        AIMS.cart.clear();

        // create in db
        int deliveryId = this.deliveryInfoSource.saveDeliveryInfo(deliveryInfo);
        int paymentId = this.paymentInfoSource.createHolder();
        int orderId = this.orderSource.saveOrder(paymentId, deliveryId, order);
        order.setOrderId(orderId);

        // rm media from store
        mediaSource.reduceMedias(order.getMediaInOrder());

        return order;
    }

    @Override
    public Invoice createInvoice(long totalPrice, long deliveryFee, int orderId) throws SQLException {
        Invoice invoice = new Invoice(totalPrice, deliveryFee);
        invoice.setOrderId(orderId);

        this.invoiceSource.saveInvoice(invoice);

        return invoice;
    }
}
