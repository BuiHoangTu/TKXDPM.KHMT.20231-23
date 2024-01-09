package hust.mssv20200547.pttkhtaims.interfaces.order;

public class RejectStatusStrategy implements IOrderStatusStrategy {
    public String getStatusDescription() {
        return "Đã hủy";
    }
}