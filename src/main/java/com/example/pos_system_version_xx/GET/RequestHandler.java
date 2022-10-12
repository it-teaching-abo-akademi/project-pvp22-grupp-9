package com.example.pos_system_version_xx.GET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestHandler {

    private static final String FIND_BARCODE = "http://localhost:9003/rest/findByBarCode/";
    private static final String FIND_KEYWORD = "http://localhost:9003/rest/findByKeyword/";
    private static final String FIND_NAME = "http://localhost:9003/rest/findByName/";

    private static final String OPEN_CASHBOX = "http://localhost:9001/cashbox/open";
    private static final String CASHBOX_STATUS = "http://localhost:9001/cashbox/status";

    private static final String CARDREADER_WAITFORPAYMENT = "http://localhost:9002/cardreader/waitForPayment";
    private static final String CARDREADER_ABORT = "http://localhost:9002/cardreader/abort";
    private static final String CARDREADER_STATUS = "http://localhost:9002/cardreader/status";
    private static final String CARDREADER_RESULT = "http://localhost:9002/cardreader/result";
    private static final String CARDREADER_RESET = "http://localhost:9002/cardreader/reset";

    private static final String FIND_CUSTOMER_NO = "http://localhost:9004/rest/findByCustomerNo";
    private static final String FIND_BONUS_CARD = "http://localhost:9004/rest/findByBonusCard";

    public void handlerOpenCashbox() throws IOException {
        getRequest("POST", OPEN_CASHBOX, "");
    }

    public static void handlerCashboxStatus() throws IOException {
        getRequest("GET", CASHBOX_STATUS, "");
    }

    public static void handlerCardReaderWaitForPayment(double chargedAmount) throws IOException {
        getRequest("POST", CARDREADER_WAITFORPAYMENT,  " --data \"amount=" + Double.toString(chargedAmount) + "\"");
    }

    public static void main(String[] args) throws IOException {
        handlerCashboxStatus();
    }

    private static void getRequest(String requestType, String URL, String argument) throws IOException {
        java.net.URL url = new URL(URL + "/" + argument);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestType);
        int responseCode = connection.getResponseCode();
        System.out.println("Code : " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response);
        }
    }
}
