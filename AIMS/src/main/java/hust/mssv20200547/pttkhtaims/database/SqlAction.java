package hust.mssv20200547.pttkhtaims.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlAction<C extends Connection, R> {
    R call(C connection) throws SQLException;
}
