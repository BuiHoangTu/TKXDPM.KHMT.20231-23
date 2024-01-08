package hust.mssv20200547.pttkhtaims.subsystem.bank.exceptions.pay;

/**
 * User close webview
 */
public class TransactionNotDoneException extends PaymentException {
    public TransactionNotDoneException() {
        super("ERROR: Giao dịch chưa hoàn tất!");
    }
}
