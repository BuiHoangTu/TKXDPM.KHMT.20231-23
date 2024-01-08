package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.database.IOrderSource;
import hust.mssv20200547.pttkhtaims.database.IPaymentInfoSource;
import hust.mssv20200547.pttkhtaims.database.mysql.OrderSource;
import hust.mssv20200547.pttkhtaims.database.mysql.PaymentInfoSource;
import hust.mssv20200547.pttkhtaims.models.Card;
import hust.mssv20200547.pttkhtaims.models.Order;
import hust.mssv20200547.pttkhtaims.models.PaymentInfo;
import hust.mssv20200547.pttkhtaims.subsystem.bank.IPaymentTransaction;

import java.sql.SQLException;

public class PayOrderService implements IPayOrderService {
    private final IPaymentInfoSource paymentInfoSource = new PaymentInfoSource();
    private final IOrderSource orderSource = new OrderSource();

    @Override
    public boolean validateCardHolderName(String cardHolderName) {
        return Card.validateCardHolderName(cardHolderName);
    }

    @Override
    public boolean validateCardNumber(String cardNumber) {
        return Card.validateCardNumber(cardNumber);
    }

    @Override
    public boolean validateCardExpirationDate(String expirationDate) {
        return Card.validateCardExpirationDate(expirationDate);
    }

    @Override
    public boolean validateCardSecurityCode(String securityCode) {
        return Card.validateCardSecurityCode(securityCode);
    }

    @Override
    public void savePaymentTransaction(IPaymentTransaction paymentTransaction) throws SQLException {
        PaymentInfo paymentInfo = new PaymentInfo(
                paymentTransaction.getTransactionId(),
                null,
                paymentTransaction.getAmount(),
                paymentTransaction.getTransactionContent(),
                paymentTransaction.getCreatedAt()
        );

        paymentInfoSource.savePaymentInfo(paymentInfo);
        orderSource.setOrderStatus(Integer.parseInt(paymentTransaction.getTransactionId()), Order.OrderStatus.PAID_SHIPPING);
    }
}
