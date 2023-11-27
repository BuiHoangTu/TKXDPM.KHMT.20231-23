package hust.mssv20200547.pttkhtaims.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayOrderServiceTest {
    private IPayOrderService payOrderService;

    @BeforeEach
    void setUp() {
        this.payOrderService = new PayOrderService();
    }


    @ParameterizedTest
    @CsvFileSource(resources = {"/hust/mssv20200547/pttkhtaims/services/pay-order-service-test/validate-cardholder-name.csv"})
    void validateCardHolderName(String name, boolean expected) {
        var actual = payOrderService.validateCardHolderName(name);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/hust/mssv20200547/pttkhtaims/services/pay-order-service-test/validate-card-number.csv"})
    void validateCardNumber(String number, boolean expected) {
        var actual = payOrderService.validateCardNumber(number);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/hust/mssv20200547/pttkhtaims/services/pay-order-service-test/validate-expiration-date.csv"})
    void validateCardExpirationDate(String expirationCode, boolean expected) {
        var actual = payOrderService.validateCardExpirationDate(expirationCode);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/hust/mssv20200547/pttkhtaims/services/pay-order-service-test/validate-security-code.csv"})
    void validateCardSecurityCode(String securityCode, boolean expected) {
        var actual = payOrderService.validateCardSecurityCode(securityCode);
        assertEquals(expected, actual);
    }
}
