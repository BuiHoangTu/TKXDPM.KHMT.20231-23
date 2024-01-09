package hust.mssv20200547.pttkhtaims.interfaces.order;

public class OrderStatusStrategyFactory
{
    public static IOrderStatusStrategy CreateStrategy(int orderStatusId)
    {
        switch (orderStatusId)
        {
            case 0:
                return new NotProcessStatusStrategy();
            case 4:
                return new RejectStatusStrategy();
            case 5:
                return new AcceptStatusStrategy();
            default:
                throw new IllegalArgumentException("Invalid order status ID");
        }
    }
}