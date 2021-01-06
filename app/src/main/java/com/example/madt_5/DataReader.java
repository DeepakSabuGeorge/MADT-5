package com.example.madt_5;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.madt_5.Constants.FLOATRATES_API_URL;

public class DataReader {
    public static List<String> getValuesFromApi(String apiCode) throws IOException {
        InputStream apiContentStream = null;
        List<String> result = new ArrayList<>();
        try {
            switch (apiCode) {
                case FLOATRATES_API_URL:
                    apiContentStream = downloadUrlContent(FLOATRATES_API_URL);
                    result.addAll(XmlParser.getCurrencyRatesBaseUsd(apiContentStream));
                    break;
                default:
            }
        }
        finally {
            if (apiContentStream != null) {
                apiContentStream.close();
            }
        }
        return result;
    }

    //GET Request to the web page

    private static InputStream downloadUrlContent(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
}