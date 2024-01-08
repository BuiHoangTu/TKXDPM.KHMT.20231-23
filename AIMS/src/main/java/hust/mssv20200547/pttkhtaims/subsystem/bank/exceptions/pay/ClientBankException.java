package hust.mssv20200547.pttkhtaims.subsystem.bank.exceptions.pay;

/**
 * 75
 */
public class ClientBankException extends PaymentException{
    public ClientBankException() {
        super("ERROR: Ngân hàng thanh toán đang bảo trì");
    }
}
