package hust.mssv20200547.pttkhtaims.database.mysql;

import hust.mssv20200547.pttkhtaims.database.ISql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MysqlBase implements ISql {
    private static Connection CONNECTION;

    private static void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost:3306/personalaims", "personal_aims", "personal_aims");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Connection getConnection() {
        boolean connectNotAvailable;
        try {
            connectNotAvailable = CONNECTION == null || CONNECTION.isClosed();
        } catch (SQLException e) {
            connectNotAvailable = true;
        }

        if (connectNotAvailable) createConnection();
        return CONNECTION;
    }

    public Connection openConnection() {
        return getConnection();
    }
}
