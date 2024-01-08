package hust.mssv20200547.pttkhtaims.subsystem.bank.exceptions.pay;

/**
 * Error 09 10 11 12 13 24 51 65 79
 */
public class TransactionFailedException extends PaymentException {

    public TransactionFailedException() {
        super("ERROR: Giao dịch thất bại!");
    }

}
