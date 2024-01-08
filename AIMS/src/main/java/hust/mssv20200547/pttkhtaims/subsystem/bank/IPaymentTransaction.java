package hust.mssv20200547.pttkhtaims.subsystem.bank;

public interface IPaymentTransaction {
    String getTransactionId();

    String getErrorCode();

    String getTransactionContent();

    int getAmount();

    java.time.LocalDateTime getCreatedAt();
}
