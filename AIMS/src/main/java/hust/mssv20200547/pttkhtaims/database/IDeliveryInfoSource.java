package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.DeliveryInfo;

import java.sql.SQLException;

public interface IDeliveryInfoSource {
    int saveDeliveryInfo(DeliveryInfo deliveryInfo) throws SQLException;
}
