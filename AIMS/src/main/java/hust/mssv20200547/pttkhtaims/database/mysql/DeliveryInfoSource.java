package hust.mssv20200547.pttkhtaims.database.mysql;

import hust.mssv20200547.pttkhtaims.database.IDeliveryInfoSource;
import hust.mssv20200547.pttkhtaims.models.DeliveryInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeliveryInfoSource extends MysqlBase implements IDeliveryInfoSource {
    @Override
    public int saveDeliveryInfo(DeliveryInfo deliveryInfo) throws SQLException {
        var mysql = getConnection();
        var prepareStm = mysql.prepareStatement(
                "INSERT INTO delivery_info(receiver, phoneNumber, email, cityAddress, detailedAddress) " +
                        "values (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );

        prepareStm.setString(1, deliveryInfo.getReceiver());
        prepareStm.setString(2, deliveryInfo.getPhoneNumber());
        prepareStm.setString(3, deliveryInfo.getEmail());
        prepareStm.setString(4, deliveryInfo.getCityAddress());
        prepareStm.setString(5, deliveryInfo.getDetailedAddress());

        int res = prepareStm.executeUpdate();

        if (res <= 0) throw new SQLException("Insert failed");

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
