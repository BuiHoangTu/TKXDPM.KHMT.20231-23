package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.database.DatabaseImp;
import hust.mssv20200547.pttkhtaims.database.IDatabase;
import hust.mssv20200547.pttkhtaims.models.Cart;
import hust.mssv20200547.pttkhtaims.models.Media;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import net.joshka.junit.json.params.JsonFileSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaceOrderServiceTest {
    private IPlaceOrderService placeOrderService;
    private IDatabase database;

    @BeforeEach
    void setUp() {
        this.placeOrderService = new PlaceOrderService();
        this.database = new DatabaseImp();
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/hust/mssv20200547/pttkhtaims/services/place-order-service-test/validate-phone.csv"})
    void validatePhoneNumber(String phoneNumber, boolean expectedRes) {
        var res = this.placeOrderService.validatePhoneNumber(phoneNumber);
        assertEquals(expectedRes, res);
    }


    @ParameterizedTest
    @JsonFileSource(resources = {"/hust/mssv20200547/pttkhtaims/services/place-order-service-test/validate-product-quantity.json"})
    void validateProductQuantity(JsonObject test) throws SQLException {
        Cart cart = new Cart();
        JsonArray cartJson = test.getJsonArray("cart");
        for (var item: cartJson.asJsonArray()) {
            var title = item.asJsonObject().getString("title");
            var quantity = item.asJsonObject().getInt("quantity");

            var media = new Media(){};
            media.setTitle(title);
            cart.put(media, (long) quantity);
        }

        var expectedJson = test.getJsonArray("expected");
        List<Media> expected = new ArrayList<>();
        for (var item: expectedJson) {
            String title = item.asJsonObject().getString("title");
            var m = new Media(){};
            m.setTitle(title);
            expected.add(m);
        }

        var res = this.placeOrderService.validateProductQuantity(this.database, cart);
        assertEquals(new HashSet<>(expected), res.keySet());
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/hust/mssv20200547/pttkhtaims/services/place-order-service-test/validate-name.csv"})
    void validateName(String name, boolean expected) {
        var actual = this.placeOrderService.validateName(name);
        assertEquals(expected, actual);
    }

}