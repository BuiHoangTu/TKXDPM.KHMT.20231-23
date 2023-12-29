package hust.mssv20200547.pttkhtaims.subsystem.bank.vnpay;

import hust.mssv20200547.pttkhtaims.subsystem.bank.IBank;
import hust.mssv20200547.pttkhtaims.subsystem.bank.IInvoice;
import hust.mssv20200547.pttkhtaims.subsystem.bank.models.PaymentTransaction;
import hust.mssv20200547.pttkhtaims.subsystem.bank.vnpay.views.pay.PayView;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class VnPay implements IBank {
    @Override
    public PaymentTransaction makePaymentTransaction(IInvoice invoice, String contents) {
        try {
            String payUrl = this.generatePayOrderUrl(invoice, contents);
            // create entries URL
            new PayView(payUrl);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String generatePayOrderUrl(IInvoice invoice, String contents) throws IOException {
        long vnpAmount = invoice.getTotalFee() * 100 * 1000;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VnPayConfig.VERSION);
        vnp_Params.put("vnp_Command", VnPayConfig.PAY_COMMAND);
        vnp_Params.put("vnp_TmnCode", VnPayConfig.TMN_CODE);
        vnp_Params.put("vnp_Amount", String.valueOf(vnpAmount));
        vnp_Params.put("vnp_CurrCode", VnPayConfig.CURRENCY);

//        vnp_Params.put("vnp_BankCode", "");

        vnp_Params.put("vnp_TxnRef", String.valueOf(invoice.getOrderId()));
        vnp_Params.put("vnp_OrderInfo", contents);
        vnp_Params.put("vnp_OrderType", VnPayConfig.ORDER_TYPE);


        vnp_Params.put("vnp_Locale", VnPayConfig.LANGUAGE);

        vnp_Params.put("vnp_ReturnUrl", VnPayConfig.RETURN_URL);
        vnp_Params.put("vnp_IpAddr", this.getIpAddress());

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone(VnPayConfig.TIME_SECTOR));
        SimpleDateFormat formatter = new SimpleDateFormat(VnPayConfig.TIME_FORMAT);
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        var itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnPayConfig.hmacSHA512(VnPayConfig.SECRET_KEY, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        return VnPayConfig.PAY_URL + "?" + queryUrl;
    }

    private String getIpAddress() throws IOException {
//        URL url = new URL("http://www.realip.info/api/p/realip.php");
//        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()))) {
//            String content = String.join("\n", in.lines().collect(Collectors.toList()));
//            Matcher matcher = pattern.matcher(content);
//            if (matcher.matches()) {
//                return matcher.group("ip");
//            } else {
//                return "No ip found";
//            }
//        }
        return "127.0.0.1:50387";
    }


}
