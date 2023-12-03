package hust.mssv20200547.pttkhtaims.services;

public interface IPayOrderService { 
	//control coupling
    boolean validateCardHolderName(String cardHolderName);
    boolean validateCardNumber(String cardNumber);
    boolean validateCardExpirationDate(String expirationDate);
    boolean validateCardSecurityCode(String securityCode);

}
