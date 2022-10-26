package com.example.pos_system_version_xx.GET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class RequestHandler {

    private static final String FIND_BARCODE = "http://localhost:9003/rest/findByBarCode";
    private static final String FIND_KEYWORD = "http://localhost:9003/rest/findByKeyword";
    private static final String FIND_NAME = "http://localhost:9003/rest/findByName";

    private static final String OPEN_CASHBOX = "http://localhost:9001/cashbox/open";
    private static final String CASHBOX_STATUS = "http://localhost:9001/cashbox/status";

    private static final String CARDREADER_WAITFORPAYMENT = "http://localhost:9002/cardreader/waitForPayment";
    private static final String CARDREADER_ABORT = "http://localhost:9002/cardreader/abort";
    private static final String CARDREADER_STATUS = "http://localhost:9002/cardreader/status";
    private static final String CARDREADER_RESULT = "http://localhost:9002/cardreader/result";
    private static final String CARDREADER_RESET = "http://localhost:9002/cardreader/reset";

    private static final String FIND_CUSTOMER_NO = "http://localhost:9004/rest/findByCustomerNo";
    private static final String FIND_BONUS_CARD = "http://localhost:9004/rest/findByBonusCard";

    public static String find(String param, SearchParamType type) throws IOException {
        if (type == SearchParamType.BARCODE) {
            return httpRequest("GET", FIND_BARCODE, param, "");
        } else if (type == SearchParamType.KEYWORD) {
            return httpRequest("GET", FIND_KEYWORD, param, "");
        } else if (type == SearchParamType.NAME) {
            return httpRequest("GET", FIND_NAME, param, "");
        } else {
            // ERROR
            return "";
        }
    }

    public static String handlerFindBarcode(String barcode) throws IOException {
        return httpRequest("GET", FIND_BARCODE, barcode, "");
    }

    public static String handlerFindKeyword(String keyword) throws IOException {
        return httpRequest("GET", FIND_KEYWORD, keyword, "");
    }

    public static String handlerFindName(String name) throws IOException {
        return httpRequest("GET", FIND_NAME, name, "");
    }
    public static String handlerOpenCashbox() throws IOException {
        return httpRequest("POST", OPEN_CASHBOX, "", "");
    }

    public static String handlerCashboxStatus() throws IOException {
        return httpRequest("GET", CASHBOX_STATUS, "", "");
    }

    public static String handlerCardReaderWaitForPayment(double chargedAmount) throws IOException {
        return httpRequest("POST", CARDREADER_WAITFORPAYMENT,  "", "amount=" + chargedAmount);
    }

    public static String handlerCardReaderAbortPayment() throws IOException{
        return httpRequest("POST", CARDREADER_ABORT, "", "");
    }

    public static String handlerCardReaderStatus() throws IOException{
        return httpRequest("GET", CARDREADER_STATUS, "", "");
    }

    public static String handlerCardReaderResult() throws IOException{
        return httpRequest("GET", CARDREADER_RESULT, "" , "");
    }

    public static String handlerCardReaderReset() throws IOException{
        return httpRequest("POST", CARDREADER_RESET, "", "");
    }

    public static String handlerFindCustomerNo(String customerNumber) throws IOException{
        return httpRequest("GET", FIND_CUSTOMER_NO, customerNumber, "");
    }

    public static String handlerFindBonusCard(String bonusCardNumber, String goodThruYear, String goodThruMonth) throws IOException{
        return httpRequest("GET", FIND_BONUS_CARD, bonusCardNumber + "/" + goodThruYear + "/" + goodThruMonth, "");
    }

    private static String httpRequest(String requestType, String URL, String argument, String data) throws IOException {
        java.net.URL url = new URL(URL + "/" + argument);
        System.out.println(url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestType);
        if (!Objects.equals(data, "")) {
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data.getBytes(StandardCharsets.UTF_8));
        }
        int responseCode = connection.getResponseCode();
        System.out.println("Code : " + responseCode);

        StringBuffer response = new StringBuffer();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            return null;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();

        System.out.println(response);
        return response.toString();
    }
}
