package hust.mssv20200547.pttkhtaims.database.mysql;

import hust.mssv20200547.pttkhtaims.database.IOrderSource;
import hust.mssv20200547.pttkhtaims.models.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                            "SET orderStatus = ?" +
                            "where id = ?"
            );

            prepareStm.setInt(1, status.getI());
            prepareStm.setInt(2, orderId);

            prepareStm.executeUpdate();
        }
    }
}
