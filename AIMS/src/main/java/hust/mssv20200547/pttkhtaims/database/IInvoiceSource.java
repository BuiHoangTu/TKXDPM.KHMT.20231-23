package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.Invoice;

import java.sql.SQLException;

public interface IInvoiceSource {
    void saveInvoice(Invoice invoice) throws SQLException;
}
