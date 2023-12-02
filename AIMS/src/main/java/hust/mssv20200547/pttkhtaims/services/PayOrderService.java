package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.models.Card;

import java.util.regex.Pattern;

public class PayOrderService implements IPayOrderService {

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
}
