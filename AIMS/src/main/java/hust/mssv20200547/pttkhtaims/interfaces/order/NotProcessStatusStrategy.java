package hust.mssv20200547.pttkhtaims.interfaces.order;

public class NotProcessStatusStrategy implements IOrderStatusStrategy {
    public String getStatusDescription() {
        return "Chưa xử lý";
    }
}
