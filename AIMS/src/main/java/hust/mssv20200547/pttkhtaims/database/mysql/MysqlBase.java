package hust.mssv20200547.pttkhtaims.database.mysql;

import hust.mssv20200547.pttkhtaims.database.ISql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MysqlBase implements ISql {
    public Connection openConnection() throws SQLException {
        var mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/personalaims", "personal_aims", "personal_aims");

        return mysql;
    }
}
