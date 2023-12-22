package hust.mssv20200547.pttkhtaims.services;

// Sử dụng interface để giảm sự phụ thuộc giữa class bậc cao và thấp

// Đánh giá tính cohesion 
// Interface IPayOrderService có tính cohesion tốt vì tất cả các phương thức đều liên quan chức năng xác thực thông tin thẻ tín dụng
// . Đây là một điểm tích cực trong việc thiết kế, vì các phương thức cung cấp các dịch vụ liên quan đến một nhiệm vụ cụ thể mà không có sự phân tán chức năng.
public interface IPayOrderService {
    boolean validateCardHolderName(String cardHolderName);
    boolean validateCardNumber(String cardNumber);
    boolean validateCardExpirationDate(String expirationDate);
    boolean validateCardSecurityCode(String securityCode);

}
//  Interface này chia nhỏ các chức năng thành các phương thức riêng biệt, mỗi phương thức thực hiện một nhiệm vụ cụ thể (validate các thông tin về thẻ tín dụng).
//  không chứa quá nhiều phương thức, mỗi phương thức chỉ đảm nhận một chức năng cụ thể, giúp tách rời các trách nhiệm và giảm sự phụ thuộc.
/* Để thỏa mãn tính Single Responsibility Principle,Phân chia các chức năng cụ thể của validate thẻ tín dụng thành các interfaces nhỏ hơn. ví dụ như sau
 public interface ICardHolderValidation {
    boolean validateCardHolderName(String cardHolderName);
}

public interface ICardNumberValidation {
    boolean validateCardNumber(String cardNumber);
}

public interface IExpirationDateValidation {
    boolean validateCardExpirationDate(String expirationDate);
}

public interface ISecurityCodeValidation {
    boolean validateCardSecurityCode(String securityCode);
}

*/