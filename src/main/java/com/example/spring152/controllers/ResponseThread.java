package com.example.spring152.controllers;

import com.example.spring152.Print;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

public class ResponseThread extends Thread {
    public static HttpResponse response;
    private HttpGet httpget;
    private HttpClient httpclient;

    @Override
    public void run() {
        super.run();
        try {
            Print.ln("respTh start");
           response = httpclient.execute(httpget);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseThread(HttpGet hhtpget, HttpClient httpclient) {
        this.httpget = hhtpget;
        this.httpclient = httpclient;
    }
}
