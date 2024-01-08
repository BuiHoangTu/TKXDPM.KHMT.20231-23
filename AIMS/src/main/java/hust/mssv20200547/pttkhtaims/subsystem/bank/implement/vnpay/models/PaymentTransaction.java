package hust.mssv20200547.pttkhtaims.subsystem.bank.implement.vnpay.models;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @param createdAt private Integer orderID;
 */
public record PaymentTransaction(
        String errorCode,
        String transactionId,
        String transactionContent,
        int amount,
        LocalDateTime createdAt
) implements hust.mssv20200547.pttkhtaims.subsystem.bank.IPaymentTransaction {
    @Override
    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getTransactionContent() {
        return transactionContent;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
