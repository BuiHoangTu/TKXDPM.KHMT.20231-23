package hust.mssv20200547.pttkhtaims.database.mysql;

import hust.mssv20200547.pttkhtaims.database.IPaymentInfoSource;
import hust.mssv20200547.pttkhtaims.models.PaymentInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class PaymentInfoSource extends MysqlBase implements IPaymentInfoSource {
    @Override
    public int createHolder() throws SQLException {
        var mysql = getConnection();
        var prepareStm = mysql.prepareStatement(
                "INSERT INTO payment_info() " +
                        "VALUES ()",
                Statement.RETURN_GENERATED_KEYS
        );

        int res = prepareStm.executeUpdate();

        if (res <= 0) throw new SQLException("Insert failed");

        try (ResultSet generatedKeys = prepareStm.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return (generatedKeys.getInt(1));
            } else {
                throw new SQLException("No ID obtained.");
            }
        }
    }

    @Override
    public void savePaymentInfo(PaymentInfo paymentInfo) throws SQLException {
        var mysql = getConnection();
        var prepareStm = mysql.prepareStatement(
                "UPDATE payment_info SET " +
                        "transactionId = ?, " +
                        "cardOwner = ?, " +
                        "balanceChange = ?, " +
                        "transactionMessage = ?, " +
                        "transactionTime = ? " +
                        "where id = ?"
        );

        prepareStm.setString(1, paymentInfo.getTransactionId());
        prepareStm.setString(2, paymentInfo.getCardOwner());
        prepareStm.setDouble(3, paymentInfo.getBalanceChange());
        prepareStm.setString(4, paymentInfo.getMessage());

        Timestamp dateTime = Timestamp.valueOf(paymentInfo.getTransactionTime());
        prepareStm.setTimestamp(5, dateTime);

        prepareStm.executeUpdate();
    }

}
