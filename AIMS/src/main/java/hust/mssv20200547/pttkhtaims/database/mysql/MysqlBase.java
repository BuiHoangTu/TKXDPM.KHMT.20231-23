package hust.mssv20200547.pttkhtaims.database.mysql;

import hust.mssv20200547.pttkhtaims.database.ISql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MysqlBase implements ISql {
    private static Connection CONNECTION;

    private static void establishConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost:3306/personalaims", "personal_aims", "personal_aims");
    }

//    @Override
//    public Connection openConnection() throws SQLException {
//        if (CONNECTION == null || CONNECTION.isClosed()) establishConnection();
//        return CONNECTION;
//    }

    public Connection openConnection() throws SQLException {
        var mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/personalaims", "personal_aims", "personal_aims");

        return mysql;
    }
}
