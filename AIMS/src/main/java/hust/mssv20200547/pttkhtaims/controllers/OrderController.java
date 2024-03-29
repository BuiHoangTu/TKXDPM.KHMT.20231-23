package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.models.Media;
import hust.mssv20200547.pttkhtaims.models.Order;
import hust.mssv20200547.pttkhtaims.database.IOrderSource;
import hust.mssv20200547.pttkhtaims.database.implementation.mysql.OrderSource;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map;

public class OrderController {
    private static final IOrderSource orderSource = new OrderSource();

    public List<Order> getListOrders(int page, int pageSize) throws  SQLException {
        var listOfOrders = orderSource.getAllOrders(page, pageSize);
        return listOfOrders;
    }

    public void updateOrderStatus(int orderId, Order.OrderStatus status) throws SQLException{
        orderSource.setOrderStatus(orderId, status);
    }

    public String checkAvailable(Map<Media, Long> mediaInOrder) throws SQLException{
        String result = mediaInOrder.keySet()
                .stream()
                .map(Media::getId)
                .map(String::valueOf) // Chuyển đổi Long thành String
                .collect(Collectors.joining(","));

        var listAvailable = orderSource.getListAvailableItem(result);
        List<Media> listMedia = new ArrayList<>(mediaInOrder.keySet());


        for (int i = 0; i < listAvailable.size(); i++) {
            Integer availableQuantity = listAvailable.get(i);
            Long mapQuantity = mediaInOrder.values().toArray(new Long[0])[i];

            // So sánh availableQuantity và mapQuantity
            if (availableQuantity < mapQuantity) {
                return "Sản phẩm: " + listMedia.get(i).getTitle()+ " có số lượng không đủ! Trong kho: " + availableQuantity;
            }
        }
        return "";
    }
}
