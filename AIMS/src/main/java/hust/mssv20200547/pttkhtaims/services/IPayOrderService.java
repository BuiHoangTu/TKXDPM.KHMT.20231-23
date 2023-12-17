package hust.mssv20200547.pttkhtaims.services;

// Sử dụng interface để giảm sự phụ thuộc giữa class bậc cao và thấp

public interface IPayOrderService {
    boolean validateCardHolderName(String cardHolderName);
    boolean validateCardNumber(String cardNumber);
    boolean validateCardExpirationDate(String expirationDate);
    boolean validateCardSecurityCode(String securityCode);

}
