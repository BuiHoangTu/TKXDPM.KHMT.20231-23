package hust.mssv20200547.pttkhtaims.subsystem.bank;

import hust.mssv20200547.pttkhtaims.subsystem.bank.models.PaymentTransaction;

public interface IBank {
    PaymentTransaction makePaymentTransaction(IInvoice invoice, String contents);
}
