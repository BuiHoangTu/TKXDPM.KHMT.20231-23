package hust.mssv20200547.pttkhtaims.database.implementation.mysql;

import hust.mssv20200547.pttkhtaims.database.IInvoiceSource;
import hust.mssv20200547.pttkhtaims.models.Invoice;

import java.sql.SQLException;

public class InvoiceSource extends MysqlBase implements IInvoiceSource {
    @Override
    public void saveInvoice(Invoice invoice) throws SQLException {
        try (var mysql = openConnection()) {
            var prepareStm = mysql.prepareStatement(
                    "INSERT INTO invoice(orderid, priceNoVAT, priceWithVAT, deliveryFee, totalFee) " +
                            "values (?, ?, ?, ?, ?)"
            );

            prepareStm.setInt(1, invoice.getOrderId());
            prepareStm.setLong(2, invoice.getPriceNoVat());
            prepareStm.setLong(3, invoice.getPriceWithVat());
            prepareStm.setLong(4, invoice.getDeliveryFee());
            prepareStm.setLong(5, invoice.getTotalFee());

            int res = prepareStm.executeUpdate();

            if (res <= 0) throw new SQLException("Insert failed");
        }
    }
}
