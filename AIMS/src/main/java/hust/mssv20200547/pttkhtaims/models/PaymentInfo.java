package hust.mssv20200547.pttkhtaims.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PaymentInfo {
    private String transactionId;
    private String cardOwner;
    private double balanceChange;
    private String message;
    private LocalDateTime transactionTime;
}
