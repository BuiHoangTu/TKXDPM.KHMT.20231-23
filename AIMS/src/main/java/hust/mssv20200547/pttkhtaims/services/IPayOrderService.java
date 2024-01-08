package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.subsystem.bank.IPaymentTransaction;

import java.sql.SQLException;

public interface IPayOrderService {
    boolean validateCardHolderName(String cardHolderName);
    boolean validateCardNumber(String cardNumber);
    boolean validateCardExpirationDate(String expirationDate);
    boolean validateCardSecurityCode(String securityCode);

    void savePaymentTransaction(IPaymentTransaction paymentTransaction) throws SQLException;
}
