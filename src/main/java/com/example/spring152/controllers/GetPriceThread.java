package com.example.spring152.controllers;

import com.example.spring152.Print;
import com.example.spring152.models.ItemModel;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class GetPriceThread extends Thread {
    static int curId;
    public static CloseableHttpResponse response;
    public static String p;
    private String cur;
    private String price;

    public GetPriceThread(String price, String cur) {
        this.price = price;
        this.cur = cur;
    }

    @Override
    public void run() {
        super.run();
        if (cur.equals("RUB")) {
            return;
        }
        HttpClient httpclient = HttpClients.createDefault();
        if (cur.equals("USD")) curId = 431;
        if (cur.equals("EUR")) curId = 451;
        HttpGet httpget = new HttpGet("https://www.nbrb.by/api/exrates/rates/"+curId);
        ResponseThread responseThread = new ResponseThread(httpget, httpclient);
        responseThread.start();
        try {
            JSONParser parser = new JSONParser();
            sleep(1400);
            Scanner scanner = new Scanner(ResponseThread.response.getEntity().getContent());
            String s = "";
            while (scanner.hasNext()) {
                s = scanner.nextLine();
                Print.ln(s);
            }
                Object obj = parser.parse(s);
                JSONObject json = (JSONObject) obj;
                ItemModel.res = String.format("%.2f",(Double.parseDouble(price) / Double.parseDouble(json.get("Cur_OfficialRate").toString()))) + " " + cur;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
