package com.li.en2zh.service;

import com.li.en2zh.config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkService {
    public String sendPostRequest(String urlStr, String word) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("DNT", "1");
        conn.setRequestProperty("User-Agent", Config.USER_AGENT);
        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        conn.setRequestProperty("Accept", "application/json, text/plain, */*");
        conn.setRequestProperty("X-Authorization", Config.API_TOKEN);
        conn.setRequestProperty("Origin", "https://fanyi.caiyunapp.com");
        conn.setRequestProperty("Sec-Fetch-Site", "cross-site");
        conn.setRequestProperty("Sec-Fetch-Mode", "cors");
        conn.setRequestProperty("Sec-Fetch-Dest", "empty");
        conn.setRequestProperty("Referer", "https://fanyi.caiyunapp.com");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");

        conn.setDoOutput(true);
        try (OutputStream wr = conn.getOutputStream()) {
            wr.write(("{\"trans_type\":\"en2zh\",\"source\":\"" + word + "\"}").getBytes("UTF-8"));
        }

        InputStream in = conn.getInputStream();
        Scanner scanner = new Scanner(in).useDelimiter("\\\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
