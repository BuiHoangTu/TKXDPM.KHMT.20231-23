package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.database.IDatabase;
import hust.mssv20200547.pttkhtaims.models.Cart;
import hust.mssv20200547.pttkhtaims.models.Media;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * Cohesion: Temporal: Các thành phần thực hiện cùng thời điểm (khi khách hàng điền thông tin)
 */
public class PlaceOrderService implements IPlaceOrderService {
    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        final String phoneRegex = "^0\\d{9}$";
        final Pattern regex = Pattern.compile(phoneRegex);

        return regex.matcher(phoneNumber).matches();
    }

    @Override
    public Map<Media, Long> validateProductQuantity(IDatabase database, Cart cart) throws SQLException {
        Map<Media, Long> infeasibleProducts = new HashMap<>();

        Map<Media, Long> inDBMedias = database.get(cart.keySet());

        for (var itemEntry : cart.entrySet()) {
            var media = itemEntry.getKey();
            long cartQuantity = itemEntry.getValue();

            Long dbQuantity = inDBMedias.get(media);

            if (dbQuantity == null) dbQuantity = 0L;

            if (dbQuantity < cartQuantity) {
                infeasibleProducts.put(media, dbQuantity);
                System.out.println("Putting " + media + "-" + dbQuantity);
            }
        }

        return infeasibleProducts;
    }

    @Override
    public boolean validateName(String name){
        final String nameRegex = "[a-zA-Z -]+$";
        final Pattern regex = Pattern.compile(nameRegex);

        return regex.matcher(name).matches();
    }

}
/* Single Responsibility Principle (SRP):
Ưu điểm: Mỗi phương thức trong PlaceOrderService thực hiện một chức năng cụ thể liên quan đến việc đặt hàng.
Các phương thức này có thể coi là đang tuân thủ nguyên tắc SRP vì chúng chỉ thực hiện một nhiệm vụ cụ thể.
Dependency Inversion Principle (DIP):
Ưu điểm: Không có sự phụ thuộc trực tiếp vào cài đặt cụ thể.
Xác thực số điện thoại và tên không phụ thuộc vào các lớp cụ thể, nhưng phương thức validateProductQuantity phụ thuộc vào IDatabase cụ thể.

Đề xuất cải thiện:
Tách rời phụ thuộc vào IDatabase: Để tuân thủ nguyên tắc DIP, có thể tách validateProductQuantity thành một interface riêng biệt và triển khai logic trong một lớp cụ thể.

public interface IProductQuantityValidator {
    Map<Media, Long> validateProductQuantity(IDatabase database, Cart cart) throws SQLException;
}

public class ProductQuantityValidator implements IProductQuantityValidator {
    @Override
    public Map<Media, Long> validateProductQuantity(IDatabase database, Cart cart) throws SQLException {
        // Logic for quantity validation
    }
}
 */