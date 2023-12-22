package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.models.Card;

import java.util.regex.Pattern;

public class PayOrderService implements IPayOrderService {

    @Override
    public boolean validateCardHolderName(String cardHolderName) {
        return Card.validateCardHolderName(cardHolderName);
    }

    @Override
    public boolean validateCardNumber(String cardNumber) {
        return Card.validateCardNumber(cardNumber);
    }

    @Override
    public boolean validateCardExpirationDate(String expirationDate) {
        return Card.validateCardExpirationDate(expirationDate);
    }

    @Override
    public boolean validateCardSecurityCode(String securityCode) {
        return Card.validateCardSecurityCode(securityCode);
    }
}
/* Đánh giá tính Cohesion:
Functional Cohesion (Tính liên kết chức năng):
Ưu điểm: Lớp PayOrderService cung cấp các phương thức để xác thực thông tin thẻ tín dụng, mỗi phương thức đều liên quan đến việc kiểm tra một thông tin cụ thể của thẻ.
Các phương thức đều thực hiện cùng một chức năng chính, đó là xác thực thông tin thẻ tín dụng. Tính liên kết chức năng ở mức độ cao, các phương thức không chứa chức năng không liên quan.
Temporal Cohesion (Tính liên kết thời gian):
Không có sự liên kết thời gian trong lớp này vì các phương thức không cần phải được gọi cùng nhau vào cùng một thời điểm.
Đánh giá tổng quan:
Lớp PayOrderService có tính cohesion tốt vì tất cả các phương thức đều liên quan chức năng xác thực thông tin thẻ tín dụng. Mỗi phương thức chỉ thực hiện một nhiệm vụ cụ thể, không có sự phân tán chức năng. Điều này giúp dễ bảo trì và hiểu mã nguồn, cũng như tăng khả năng tái sử dụng các phương thức.
 */

/*
Đánh giá tính SOLID:
Single Responsibility Principle (SRP):
Ưu điểm: Mỗi phương thức trong PayOrderService thực hiện một chức năng cụ thể liên quan đến xác thực thông tin thẻ tín dụng.
Các phương thức này có thể được coi là đang tuân thủ nguyên tắc SRP vì chúng chỉ thực hiện một nhiệm vụ cụ thể.
Dependency Inversion Principle (DIP):
Ưu điểm: Lớp PayOrderService không triển khai trực tiếp logic xác thực mà thay vào đó sử dụng phương thức từ lớp Card, giúp tránh sự phụ thuộc trực tiếp vào cài đặt cụ thể.
Việc sử dụng Card có thể coi là việc phân chia trách nhiệm, nhưng cũng có thể tạo ra một sự ràng buộc mạnh mẽ với lớp Card.
Đề xuất tăng tính SOLID:
Sử dụng các interfaces mới để xác thực: Tạo các interfaces riêng biệt cho từng loại xác thực (như xác thực tên chủ thẻ, số thẻ, ngày hết hạn, mã bảo mật).



public interface ICardHolderNameValidator {
    boolean validateCardHolderName(String cardHolderName);
}// Tương tự cho các interfaces khác

public class CardValidator implements ICardHolderNameValidator, ICardNumberValidator, IExpirationDateValidator, ISecurityCodeValidator {
    @Override
    public boolean validateCardHolderName(String cardHolderName) {
        // Implement logic for card holder name validation
    }

    // Implement other validation methods
}

 */




