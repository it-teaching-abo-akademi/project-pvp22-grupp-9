package com.example.pos_system_version_xx.GET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


// TEST CLASS FOR DOING GET REQUESTS TO PRODUCT CATALOG
public class GET_REQUEST_TEST_CLASS {

    private static final String barcode = "12345";
    private static final String keyword = "fruit";
    private static final String name = "bananas";
    private static final String FIND_BARCODE = "http://localhost:9003/rest/findByBarCode/";

    private static final String FIND_KEYWORD = "http://localhost:9003/rest/findByKeyword/";

    private static final String FIND_NAME = "http://localhost:9003/rest/findByName/";

    public static void main(String[] args) throws IOException {
        getRequest(FIND_BARCODE, barcode);
        getRequest(FIND_KEYWORD, keyword);
        getRequest(FIND_NAME, name);
    }

    private static void getRequest(String URL, String argument) throws IOException {
        URL url = new URL(URL + argument);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
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
