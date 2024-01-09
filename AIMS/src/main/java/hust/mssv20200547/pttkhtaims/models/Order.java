package hust.mssv20200547.pttkhtaims.models;

import hust.mssv20200547.pttkhtaims.interfaces.order.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

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
    }

    private int orderId;
    private Map<Media, Long> mediaInOrder = new HashMap<>();
    private DeliveryInfo deliveryInfo;
    private OrderStatus orderStatus;
    private IOrderStatusStrategy orderStatusStrategy;

    public Order(Cart currentCart, DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        mediaInOrder.putAll(currentCart);
    }

    public Order(int orderId, Map<Media, Long> mediaInOrder, DeliveryInfo deliveryInfo, OrderStatus orderStatus, IOrderStatusStrategy orderStatusStrategy) {
        this.orderId = orderId;
        this.mediaInOrder = mediaInOrder;
        this.deliveryInfo = deliveryInfo;
        this.orderStatus = orderStatus;
        this.orderStatusStrategy = orderStatusStrategy;
    }

    public Order(int orderId, Map<Media, Long> mediaInOrder, DeliveryInfo deliveryInfo, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.mediaInOrder = mediaInOrder;
        this.deliveryInfo = deliveryInfo;
        this.orderStatus = orderStatus;
    }
    public String getStatusDescription()
    {
        return orderStatusStrategy.getStatusDescription();
    }

}
