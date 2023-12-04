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
