package hust.mssv20200547.pttkhtaims.subsystem.bank.exceptions.pay;


/**
 * Error 99
 */
public class UnrecognizedException extends PaymentException {
    public UnrecognizedException() {
        super("ERROR: Something went wrowng!");
    }
}
