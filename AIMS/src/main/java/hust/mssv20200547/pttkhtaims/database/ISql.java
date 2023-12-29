package hust.mssv20200547.pttkhtaims.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ISql {
    Connection openConnection() throws SQLException;
}
