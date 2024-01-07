package hust.mssv20200547.pttkhtaims.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Order {
    public enum OrderStatus {
        NOT_PROCESS(0),
        NOT_PAID(1),
        PAID_SHIPPING(2),
        DELIVERED(3),
        CANCELED(4),
        ACCPEPT(5);

        @Getter
        private final int i;

        OrderStatus(int i) {
            this.i = i;
        }

        public static OrderStatus fromInt(int value) {
            for (OrderStatus status : values()) {
                if (status.i == value) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Invalid OrderStatus value: " + value);
        }

        public String getStatusDescription() {
            switch (this) {
                case NOT_PROCESS:
                    return "Chưa xử lý";
                case NOT_PAID:
                    return "Not Paid";
                case PAID_SHIPPING:
                    return "Paid and Shipping";
                case DELIVERED:
                    return "Delivered";
                case CANCELED:
                    return "Đã hủy";
                case ACCPEPT:
                    return "Đã phê duyệt";
                default:
                    return "Unknown";
            }
        }

    }

    private int orderId;
    private Map<Media, Long> mediaInOrder = new HashMap<>();
    private DeliveryInfo deliveryInfo;
    private OrderStatus orderStatus;

    public Order(Cart currentCart, DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        mediaInOrder.putAll(currentCart);
    }
}
