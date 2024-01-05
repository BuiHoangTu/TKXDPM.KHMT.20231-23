package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.Order;

import java.sql.SQLException;
import java.util.*;

public interface IOrderSource {
    int saveOrder(int paymentInfoId, int deliveryInfoId) throws SQLException;
    void setOrderStatus (int orderId, Order.OrderStatus status) throws SQLException;
    List<Order> getAllOrders() throws SQLException;
    public List<Integer> getListAvailableItem(String ids) throws SQLException;

}
