package hust.mssv20200547.pttkhtaims.models;

import hust.mssv20200547.pttkhtaims.subsystem.bank.IInvoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Invoice implements IInvoice {
    private int orderId;
    private final long priceNoVat;
    private final long priceWithVat;
    private final long deliveryFee;
    private final long totalFee;

    public Invoice(long priceNoVat, long priceWithVat, long deliveryFee, long totalFee) {
        this.priceNoVat = priceNoVat;
        this.priceWithVat = priceWithVat;
        this.deliveryFee = deliveryFee;
        this.totalFee = totalFee;
    }

    public Invoice(long priceNoVat, long deliveryFee) {
        this.priceNoVat = priceNoVat;
        this.priceWithVat = priceNoVat * 10/100;
        this.deliveryFee = deliveryFee;
        this.totalFee = priceWithVat + deliveryFee;
    }
}
