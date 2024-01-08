package hust.mssv20200547.pttkhtaims.subsystem.bank.exceptions.pay;

/**
 * This exception is thrown if pay status is not success
 */
public abstract class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }
}
