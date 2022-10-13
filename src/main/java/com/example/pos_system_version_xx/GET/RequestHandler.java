package com.example.pos_system_version_xx.GET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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

    public static void handlerFindBarcode(String barcode) throws IOException {
        httpRequest("GET", FIND_BARCODE, barcode, "", false);
    }

    public static void handlerFindKeyword(String keyword) throws IOException {
        httpRequest("GET", FIND_KEYWORD, keyword, "",false);
    }

    public static void handlerFindName(String name) throws IOException {
        httpRequest("GET", FIND_NAME, name, "",false);
    }
    public static void handlerOpenCashbox() throws IOException {
        httpRequest("POST", OPEN_CASHBOX, "", "", false);
    }

    public static void handlerCashboxStatus() throws IOException {
        httpRequest("GET", CASHBOX_STATUS, "", "", false);
    }

    public static void handlerCardReaderWaitForPayment(double chargedAmount) throws IOException {
        httpRequest("POST", CARDREADER_WAITFORPAYMENT,  "", "amount=" + chargedAmount, true);
    }

    public static void handlerCardReaderAbortPayment()throws IOException{
        httpRequest("POST", CARDREADER_ABORT, "", "", true);
    }

    public static void handlerCardReaderStatus() throws IOException{
        httpRequest("POST", CARDREADER_STATUS, "", "", true);
    }

    public static void handlerCardReaderResult() throws IOException{
        httpRequest("GET", CARDREADER_RESULT, "" , "", true);
    }

    public static void handlerCardReaderReset() throws IOException{
        httpRequest("POST", CARDREADER_RESET, "", "", true);
    }

    public static void handlerFindCustomerNo(String customerNumber) throws IOException{
        httpRequest("GET", FIND_CUSTOMER_NO, "", "number: " + customerNumber, true);
    }

    public static void handlerFindBonusCard(String bonusCardNumber) throws IOException{
        httpRequest("GET", FIND_BONUS_CARD, "", "Card number: " + bonusCardNumber, true);
    }

    public static void main(String[] args) throws IOException {
        //handlerFindBarcode("12345");
        //handlerFindKeyword("fruit");
        //handlerFindName("Bananas");
        //handlerOpenCashbox();
        //handlerCashboxStatus();
        //handlerCardReaderWaitForPayment(1234567890);
        //handlerCardReaderAbort();
        //handlerCardReaderStatus();
        //handlerCardReaderResult(); // can only be called after payment, returns xml for payment card as response
        //handlerCardReaderReset();
        //handlerFindCustomerNo("1");
        //handlerFindBonusCard("1234567890", "2999", "6");
        //handlerCardReaderAbortPayment();
        //handlerCardReaderResult();
        //handlerCardReaderReset();
    }

    private static String httpRequest(String requestType, String URL, String argument, String data, boolean doOutput) throws IOException {
        java.net.URL url = new URL(URL + "/" + argument);
        System.out.println(url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestType);
        if (doOutput) {
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

        //System.out.println(response);
        return response.toString();
    }
}
