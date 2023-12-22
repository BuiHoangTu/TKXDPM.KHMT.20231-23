package hust.mssv20200547.pttkhtaims.models;

import lombok.Getter;
import lombok.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

@Getter
public class Card {
    // SOLID: The open closed principle: vì class này có thể mở rộng thêm các hàm validate nữa, tuy nhiên khó để chỉnh sửa lại bản thân các hàm
    private static final int CARD_HOLDER_NAME_MAX_LENGTH = 50;
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int SECURITY_CODE_LENGTH = 3;
    private static final String ERR_NAME = "Card holder's name is not all in UPPERCASE of maximum " + CARD_HOLDER_NAME_MAX_LENGTH + " characters";
    private static final String ERR_NUMBER = "Card number is not " + CARD_NUMBER_LENGTH + " digits string";
    private static final String ERR_DATE_FORMAT = "Expiration date format is not in MM/YY";
    private static final String ERR_SECURE_CODE = "Security code is not " + SECURITY_CODE_LENGTH + " digits string";

    public static boolean validateCardHolderName(String cardHolderName) {
        // not longer than CARD_HOLDER_NAME_MAX_LENGTH UPPERCASE and space
        final String pattern = "^[A-Z ]{1," + CARD_HOLDER_NAME_MAX_LENGTH + "}$";
        final Pattern regex = Pattern.compile(pattern);

        return regex.matcher(cardHolderName).matches();
    }

    public static boolean validateCardNumber(String cardNumber) {
        // CARD_NUMBER_LENGTH digits
        final String pattern = "^\\d{" + CARD_NUMBER_LENGTH + "}$";
        final Pattern regex = Pattern.compile(pattern);

        return regex.matcher(cardNumber).matches();
    }

    public static boolean validateCardExpirationDate(String expirationDate) {
        // MM/YY
        final String pattern = "^(0[1-9]|1[0-2])/\\d{2}$";
        final Pattern regex = Pattern.compile(pattern);

        return regex.matcher(expirationDate).matches();
    }

    public static boolean validateCardSecurityCode(String securityCode) {
        // SECURITY_CODE_LENGTH digits
        final String pattern = "^\\d{" + SECURITY_CODE_LENGTH + "}$";
        final Pattern regex = Pattern.compile(pattern);

        return regex.matcher(securityCode).matches();
    }

    public static void validateFormat(
            @NonNull String cardHolderName,
            @NonNull String cardNumber,
            @NonNull String expirationDate,
            @NonNull String securityCode) throws IllegalArgumentException {
        if (!validateCardHolderName(cardHolderName))
            throw new IllegalArgumentException(ERR_NAME);
        if (!validateCardNumber(cardNumber))
            throw new IllegalArgumentException(ERR_NUMBER);
        if (!validateCardExpirationDate(expirationDate))
            throw new IllegalArgumentException(ERR_DATE_FORMAT);
        if (!validateCardSecurityCode(securityCode))
            throw new IllegalArgumentException(ERR_SECURE_CODE);
    }

    @NonNull
    public static Card create(
            @NonNull String cardHolderName,
            @NonNull String cardNumber,
            @NonNull String expirationDate,
            @NonNull String securityCode) throws IllegalArgumentException {
        Card.validateFormat(cardHolderName, cardNumber, expirationDate, securityCode);
        return new Card(cardHolderName, cardNumber.toCharArray(), expirationDate.toCharArray(), securityCode.toCharArray());
    }

    private final String cardHolderName;
    private final char[] cardNumber;
    private final char[] expirationDate;
    private final char[] securityCode;

    private Card(String cardHolderName, char[] cardNumber, char[] expirationDate, char[] securityCode) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }
}
