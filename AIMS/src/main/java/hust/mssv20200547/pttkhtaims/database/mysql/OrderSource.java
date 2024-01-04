package hust.mssv20200547.pttkhtaims.database.mysql;

import hust.mssv20200547.pttkhtaims.database.IOrderSource;
import hust.mssv20200547.pttkhtaims.models.Order;
import hust.mssv20200547.pttkhtaims.models.Media;
import hust.mssv20200547.pttkhtaims.models.Book;
import hust.mssv20200547.pttkhtaims.models.DeliveryInfo;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class OrderSource extends MysqlBase implements IOrderSource {
    @Override
    public int saveOrder(int paymentInfoId, int deliveryInfoId) throws SQLException {
        try (var mysql = openConnection()) {
            var prepareStm = mysql.prepareStatement(
                    "INSERT INTO aims_order(paymentInfoId, deliveryInfoId, orderStatus) " +
                            "values (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            prepareStm.setInt(1, paymentInfoId);
            prepareStm.setLong(2, deliveryInfoId);
            // not paid
            prepareStm.setLong(3, 1);

            int affectedRow = prepareStm.executeUpdate();

            if (affectedRow <= 0) {
                throw new SQLException("Insert failed");
            }

            try (ResultSet generatedKeys = prepareStm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return (generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("No ID obtained.");
                }
            }
        }
    }

    @Override
    public void setOrderStatus(int orderId, Order.OrderStatus status) throws SQLException {
        try (var mysql = openConnection()) {
            var prepareStm = mysql.prepareStatement(
                    "UPDATE aims_order " +
                            "SET orderStatus = ? " +
                            "where id = ?"
            );

            prepareStm.setInt(1, status.getI());
            prepareStm.setInt(2, orderId);

            prepareStm.executeUpdate();
        }

    }
    @Override
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();

        try (var mysql = openConnection();
             var statement = mysql.createStatement()) {

            var query = "SELECT ai.id, ai.orderStatus, di.cityAddress, di.detailedAddress, di.email, di.phoneNumber, di.receiver " +
                    "FROM aims_order AS ai " +
                    "LEFT JOIN delivery_info AS di ON ai.deliveryInfoId = di.id";

            var resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int orderId = resultSet.getInt("id");
                String city = resultSet.getString("cityAddress");
                String detail = resultSet.getString("detailedAddress");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phoneNumber");
                String receiver = resultSet.getString("receiver");

                var deliveryInfo = new DeliveryInfo(receiver, phone, email, city, detail);
                int orderStatusValue = resultSet.getInt("orderStatus");
                Order.OrderStatus orderStatus = Order.OrderStatus.fromInt(orderStatusValue);

                var mediaInOrder = loadMediaInOrder(orderId);

                Order order = new Order(orderId, mediaInOrder, deliveryInfo, orderStatus);
                orders.add(order);
            }
        }

        return orders;
    }

    private Map<Media, Long> loadMediaInOrder(int orderId) throws SQLException {
        Map<Media, Long> mediaInOrder = new HashMap<>();

        try (var mysql = openConnection();
             var statement = mysql.prepareStatement("SELECT m.id, m.`value`, m.price, m.title, mio.quantity FROM medias_in_order AS mio " +
                     "LEFT JOIN media AS m ON m.id = mio.media_id " +
                     "WHERE order_id = ?")) {

            statement.setInt(1, orderId);
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long mediaId = resultSet.getInt("id");
                long value = resultSet.getLong("value");
                long price = resultSet.getLong("price");
                String title = resultSet.getString("title");
                long quantity = resultSet.getLong("quantity");

                var media = new Book(mediaId, title, value, price, "genre_value", "authors_value", "cover_value", "publisher_value", LocalDate.now(), "language_value", 100);
                // Add media and quantity to the map
                mediaInOrder.put(media, quantity);
            }
        }

        return mediaInOrder;
    }

    public List<Integer> getListAvailableItem(String ids) throws SQLException {
        String query = "SELECT * FROM media WHERE FIND_IN_SET(id, ?) > 0";
        List<Integer> resultList = new ArrayList<>();

        try (var mysql = openConnection();
             var statement = mysql.prepareStatement(query)) {
            statement.setString(1, ids);
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("quantity");
                resultList.add(id);

            }
        }
        return resultList;
    }
}
